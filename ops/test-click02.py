from playwright.sync_api import sync_playwright
import time

def simulate_real_click_and_save_html():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        page = browser.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 访问: {url}")
        page.goto(url, timeout=60000)
        page.wait_for_selector("div.financial-statements", timeout=20000)

        # ✅ 使用 Playwright 的原生 click（模拟真实点击 label）
        print("👉 使用 page.click() 模拟点击 '年报'")
        page.click("label:has-text('年报')")

        print("🕒 等待页面刷新...")
        time.sleep(3)  # 可适当延长时间确保刷新完成

        # ✅ 保存页面 HTML
        html = page.content()
        with open("output_after_real_click.html", "w", encoding="utf-8") as f:
            f.write(html)

        print("✅ 页面已保存为 output_after_real_click.html")
        browser.close()

if __name__ == "__main__":
    simulate_real_click_and_save_html()
