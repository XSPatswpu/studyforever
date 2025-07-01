from playwright.sync_api import sync_playwright

def extract_profit_statement_data(url):
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.goto(url)

        # 等待 jc-main 加载完成
        page.wait_for_selector('.jc-main', timeout=10000)

        # 提取利润表数据
        profit_statement_table = page.query_selector('div[role="radiogroup"] table.el-table__body')
        rows = profit_statement_table.query_selector_all('tr.el-table__row')

        # 解析表格数据
        profit_statement_data = {}
        headers = ['营业总收入', '营业总成本', '营业利润', '利润总额', '所得税', '归属母公司净利润']
        for i, header in enumerate(headers):
            row = rows[i]
            cell = row.query_selector(f'td:nth-child({i + 1}) div.cell')
            profit_statement_data[header] = cell.inner_text().strip()

        browser.close()
        return profit_statement_data

# 示例调用
url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"  # 替换为实际的URL
result = extract_profit_statement_data(url)
for key, value in result.items():
    print(f"{key}: {value}")