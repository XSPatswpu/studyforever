from playwright.sync_api import sync_playwright
import pandas as pd
import time

def scrape_year_reports():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)  # 可设为 True 静默抓取
        page = browser.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 正在访问: {url}")
        page.goto(url, timeout=60000)

        print("👉 点击 '年报' 标签...")
        page.click("label:has-text('年报')")

        print("⏳ 等待页面数据刷新...")
        time.sleep(3)  # 给 Vue 留出时间渲染数据

        # ✅ 获取列头（含指标列）
        header_rows = page.query_selector_all("div.financial-statements .el-table__header-wrapper thead tr")
        header_cells = header_rows[0].query_selector_all("th .cell")
        date_columns = [cell.inner_text().strip() for cell in header_cells if cell.inner_text().strip()]
        columns = ['指标名称'] + date_columns
        print(f"📊 年报列头: {columns}")

        # ✅ 抓取数据行
        rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
        print(f"📄 年报数据行数: {len(rows)}")
        data = []
        for idx, row in enumerate(rows):
            cells = row.query_selector_all("td .cell")
            row_data = [cell.inner_text().strip() for cell in cells]
            if len(row_data) == len(columns):
                data.append(row_data)
            else:
                print(f"⚠️ 跳过第 {idx+1} 行（列数不匹配）: {row_data}")

        browser.close()

        df = pd.DataFrame(data, columns=columns)

        # ✅ 后处理：只保留“指标名称” + 年报列
        year_cols = [col for col in columns[1:] if col.endswith("12-31")]
        df = df[['指标名称'] + year_cols]

        print(f"\n✅ 年报数据提取完成（{len(year_cols)} 年）:")
        print(df)


        # 👉 如需保存 CSV，只需取消下一行注释
        # df.to_csv("年报利润表.csv", index=False, encoding="utf-8-sig")

if __name__ == "__main__":
    scrape_year_reports()
