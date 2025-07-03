from playwright.sync_api import sync_playwright
import pandas as pd

def scrape_profit_table():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        context = browser.new_context()
        page = context.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 正在访问: {url}")
        page.goto(url, timeout=60000)

        page.wait_for_selector("div.financial-statements .el-table__header-wrapper", timeout=20000)

        # ✅ 只取表头的第一行
        header_rows = page.query_selector_all("div.financial-statements .el-table__header-wrapper thead tr")
        header_cells = header_rows[0].query_selector_all("th .cell")
        date_columns = [cell.inner_text().strip() for cell in header_cells if cell.inner_text().strip()]

        print(f"📊 原始列头（不含指标列）: {date_columns}")

        # ✅ 构造完整列头
        columns = ['指标名称'] + date_columns
        print(f"✅ 完整列头: {columns}")

        # ✅ 抓取数据行
        rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
        print(f"📄 数据行数: {len(rows)}")
        data = []
        for idx, row in enumerate(rows):
            cells = row.query_selector_all("td .cell")
            row_data = [cell.inner_text().strip() for cell in cells]
            if len(row_data) == len(columns):
                data.append(row_data)
            else:
                print(f"⚠️ 跳过第 {idx+1} 行（列数不匹配 {len(row_data)} vs {len(columns)}）: {row_data}")

        browser.close()

        # ✅ 构建 DataFrame
        df = pd.DataFrame(data, columns=columns)

        # ✅ 筛选“指标列” + 年报列（12-31）
        year_end_cols = [col for col in columns[1:] if col.endswith('12-31')]
        final_df = df[['指标名称'] + year_end_cols]

        print(f"\n✅ 筛选后仅保留“年报”列（共 {len(year_end_cols)} 年）:")
        print(final_df)

if __name__ == "__main__":
    scrape_profit_table()
