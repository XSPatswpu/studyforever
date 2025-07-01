from playwright.sync_api import sync_playwright
import re

def extract_financial_summary(stock_code, org_id):
    url = f'https://www.cninfo.com.cn/new/disclosure/stock?orgId={org_id}&stockCode={stock_code}#financialStatements'

    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.goto(url, timeout=60000)

        # 等待“财务摘要”加载（这个 class 是财务摘要模块的根）
        page.wait_for_selector('.zx_left_content', timeout=10000)
        content = page.inner_text('.zx_left_content')

        browser.close()

    return content

# 示例调用
stock_code = '000596'
org_id = 'gssz0000596'

text = extract_financial_summary(stock_code, org_id)
print("\n====== 页面内容提取成功 ======\n")
print(text)

# 简单正则抽取字段
match_revenue = re.search(r'营业收入[：: ]*([\d,.]+)', text)
match_profit = re.search(r'净利润[：: ]*([\d,.]+)', text)

if match_revenue:
    print(f"\n✅ 营业收入: {match_revenue.group(1)}")
if match_profit:
    print(f"✅ 净利润: {match_profit.group(1)}")
