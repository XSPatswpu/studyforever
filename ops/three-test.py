from playwright.sync_api import sync_playwright
import re

def extract_financial_summary(stock_code, org_id):
    url = f'https://www.cninfo.com.cn/new/disclosure/stock?orgId={org_id}&stockCode={stock_code}#financialStatements'

    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False, slow_mo=50)  # 方便调试，非无头模式，慢速操作
        page = browser.new_page()
        page.goto(url, timeout=60000)

        # 等待页面加载完毕
        page.wait_for_load_state('networkidle')

        # 点击 “财务报表” tab（根据页面文字）
        try:
            page.click('text=财务报表')
        except Exception as e:
            print("点击财务报表 tab 失败，尝试继续", e)

        # 等待财务报表根节点加载，延长超时时间
        page.wait_for_selector('.zx_left_content', timeout=20000)

        content = page.inner_text('.zx_left_content')

        browser.close()

    return content

# 测试调用
stock_code = '000596'
org_id = 'gssz0000596'

text = extract_financial_summary(stock_code, org_id)
print("\n====== 页面内容提取成功 ======\n")
print(text)

# 用正则提取应收账款和净利润
match_receivable = re.search(r'应收账款[：: ]*([\d,.]+)', text)
match_profit = re.search(r'净利润[：: ]*([\d,.]+)', text)

if match_receivable:
    print(f"\n✅ 应收账款: {match_receivable.group(1)}")
else:
    print("⚠️ 未找到应收账款")

if match_profit:
    print(f"✅ 净利润: {match_profit.group(1)}")
else:
    print("⚠️ 未找到净利润")
