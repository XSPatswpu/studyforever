from playwright.sync_api import sync_playwright
import pandas as pd
import json
import time
import re
from pypinyin import lazy_pinyin, Style

def to_pinyin_and_abbr(text):
    pinyin_list = lazy_pinyin(text, style=Style.NORMAL)
    abbr = ''.join([s[0] for s in pinyin_list])
    return ''.join(pinyin_list), abbr

def extract_numeric(text):
    match = re.search(r"([\d,.]+)", text)
    return float(match.group(1).replace(',', '')) if match else None

def scrape_to_bulk_json(stock_code: str, stock_name: str, org_id: str, index_name="stock_financials"):
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        page = browser.new_page()

        url = f"https://www.cninfo.com.cn/new/disclosure/stock?orgId={org_id}&stockCode={stock_code}#financialStatements"
        print(f"🌐 打开页面: {url}")
        page.goto(url, timeout=60000)

        print("⏳ 等待页面加载完成...")
        page.wait_for_selector("div.financial-statements", timeout=20000)
        time.sleep(2)

        print("👉 注入 Vue：切换到年报模式...")
        page.evaluate("""
            const group = document.querySelector(".el-radio-group");
            const vueInstance = group && group.__vue__;
            if (vueInstance) {
                vueInstance.modelValue = '年报';
                vueInstance.$emit && vueInstance.$emit('change', '年报');
            }
        """)
        time.sleep(3)

        print("🔍 提取公司数值信息（价格、市盈率、总股本）...")
        current_price_text = page.locator("div.news-header-left div.value").first.inner_text().strip()
        current_price = float(current_price_text)

        pe_text = page.locator("//div[contains(@class, 'right-item')][span[text()='市盈率：']]/span[2]").first.inner_text().strip()
        pe_ratio = float(pe_text)

        total_shares = page.locator("//div[contains(@class, 'right-item')][span[text()='总股本：']]/span[2]").first.inner_text().strip()

        print("📊 抓取利润表数据...")
        header_row = page.query_selector("div.financial-statements .el-table__header-wrapper thead tr")
        header_cells = header_row.query_selector_all("th .cell")
        date_columns = [cell.inner_text().strip() for cell in header_cells if cell.inner_text().strip()]
        columns = ['指标名称'] + date_columns

        rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
        data = []
        for row in rows:
            cells = row.query_selector_all("td .cell")
            row_data = [cell.inner_text().strip() for cell in cells]
            if len(row_data) == len(columns):
                data.append(row_data)

        browser.close()

        df = pd.DataFrame(data, columns=columns)
        year_cols = [col for col in columns[1:] if col.endswith("12-31")]
        df = df[['指标名称'] + year_cols]

        def extract_year_value(row):
            return [
                {"year": col[:4], "value": float(row[col].replace(",", ""))}
                for col in year_cols if row[col]
            ]

        revenue_row = df[df['指标名称'].str.contains("营业总收入")].iloc[0]
        profit_row = df[df['指标名称'].str.contains("归属母公司净利润")].iloc[0]

        revenue = extract_year_value(revenue_row)
        net_profit = extract_year_value(profit_row)
        pinyin, abbr = to_pinyin_and_abbr(stock_name)

        doc = {
            "stock_code": stock_code,
            "stock_name": stock_name,
            "stock_pinyin": pinyin,
            "stock_abbr": abbr,
            "pe_ratio": pe_ratio,
            "current_price": current_price,
            "total_shares": total_shares,
            "financials": {
                "net_profit": net_profit,
                "revenue": revenue
            }
        }

        meta = {
            "index": {
                "_index": index_name,
                "_id": stock_code
            }
        }

        output_file = f"{stock_code}.jsonl"
        with open(output_file, "w", encoding="utf-8") as f:
            f.write(json.dumps(meta, ensure_ascii=False) + "\n")
            f.write(json.dumps(doc, ensure_ascii=False) + "\n")

        print(f"\n✅ 抓取完成，数据已保存为 {output_file}，可用于 Elasticsearch /_bulk")

if __name__ == "__main__":
    scrape_to_bulk_json(
        stock_code="000596",
        stock_name="古井贡酒",
        org_id="gssz0000596"
    )
