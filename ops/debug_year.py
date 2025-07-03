from playwright.sync_api import sync_playwright
import time

def inject_vue_report_type_stable():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)  # 可设为 True 静默执行
        page = browser.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 打开页面: {url}")
        page.goto(url, timeout=60000)

        print("⏳ 等待页面渲染完成（radio按钮 + 表格）...")
        page.wait_for_selector("label.el-radio-button:has-text('年报')", timeout=20000)
        page.wait_for_selector("div.financial-statements .el-table__header-wrapper", timeout=20000)

        time.sleep(1.5)  # 多等一点，避免 Vue 还在渲染

        print("👉 注入 JS：尝试访问 Vue 实例并设置 reportType = '年报'")
        try:
            page.evaluate("""
                const group = document.querySelector(".el-radio-group");
                const vueInstance = group && group.__vue__;
                if (vueInstance) {
                    vueInstance.modelValue = '年报';  // Vue 3（v-model）绑定的值
                    vueInstance.$emit && vueInstance.$emit('change', '年报');
                    console.log("✅ Vue modelValue 设置完成");
                } else {
                    console.warn("❌ 没找到 Vue 实例 __vue__");
                }
            """)
        except Exception as e:
            print("⚠️ JS 注入报错:", e)

        print("⏳ 等待页面响应...")
        time.sleep(3)

        html = page.content()
        with open("after_vue_model_set_stable.html", "w", encoding="utf-8") as f:
            f.write(html)

        print("✅ 页面结构已保存为 after_vue_model_set_stable.html")
        browser.close()

if __name__ == "__main__":
    inject_vue_report_type_stable()
