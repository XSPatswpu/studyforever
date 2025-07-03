from playwright.sync_api import sync_playwright
import pandas as pd

def scrape_profit_table():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        context = browser.new_context()
        page = context.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        page.goto(url, timeout=60000)

        page.wait_for_selector("div.financial-statements .el-table__header-wrapper", timeout=20000)

        # ✅ 只抓表头中“指标列 + 日期列（通常 5 列）”
        header_cells = page.query_selector_all("div.financial-statements .el-table__header-wrapper th .cell")
        columns = []
        for cell in header_cells:
            text = cell.inner_text().strip()
            if text:  # 非空
                columns.append(text)
        # 保证表头列数为6（指标 + 5 日期）
        columns = columns[:6]

        # ✅ 抓表格行
        rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
        data = []
        for row in rows:
            cells = row.query_selector_all("td .cell")
            row_data = [cell.inner_text().strip() for cell in cells]
            if len(row_data) == len(columns):
                data.append(row_data)
            else:
                print(f"⚠️ 跳过列数不匹配行: {row_data}")

        browser.close()

        df = pd.DataFrame(data, columns=columns)
        print(df)

if __name__ == "__main__":
    scrape_profit_table()
