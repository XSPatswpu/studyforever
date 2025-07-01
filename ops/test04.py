from playwright.sync_api import sync_playwright

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False)
    page = browser.new_page()
    page.goto("https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements", timeout=60000)

    page.wait_for_load_state("networkidle")
    page.click("text=财务报表")
    page.wait_for_timeout(5000)

    html = page.content()

    with open("rendered_page.html", "w", encoding="utf-8") as f:
        f.write(html)

    print("✅ 已保存页面 HTML 到 rendered_page.html")

    browser.close()
