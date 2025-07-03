from playwright.sync_api import sync_playwright
import time

def simulate_click_and_save_html():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        page = browser.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 访问: {url}")
        page.goto(url, timeout=60000)
        page.wait_for_selector("div.financial-statements", timeout=20000)

        print("👉 点击 '年报' 按钮（JS 注入方式）...")
        page.evaluate("""
            () => {
                const radios = document.querySelectorAll('.financial-statements .el-radio-button');
                for (const el of radios) {
                    if (el.textContent.includes('年报')) {
                        el.click();
                        break;
                    }
                }
            }
        """)

        print("🕒 等待页面内容刷新...")
        time.sleep(3)  # 可以调成 5 秒确认充分加载

        # ✅ 保存页面 HTML
        content = page.content()
        with open("output_after_click.html", "w", encoding="utf-8") as f:
            f.write(content)

        print("✅ 页面 HTML 已保存为 output_after_click.html")
        browser.close()

if __name__ == "__main__":
    simulate_click_and_save_html()
