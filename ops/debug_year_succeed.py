from playwright.sync_api import sync_playwright
import pandas as pd
import time

def scrape_year_reports_final():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)  # 设置为 True 可隐藏浏览器
        page = browser.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 打开页面: {url}")
        page.goto(url, timeout=60000)

        print("⏳ 等待页面加载...")
        page.wait_for_selector("label.el-radio-button:has-text('年报')", timeout=20000)
        page.wait_for_selector("div.financial-statements .el-table__header-wrapper", timeout=20000)
        time.sleep(1.5)

        # ✅ 注入 Vue 属性切换为“年报”模式
        print("👉 注入 Vue modelValue = '年报' ...")
        page.evaluate("""
            const group = document.querySelector(".el-radio-group");
            const vueInstance = group && group.__vue__;
            if (vueInstance) {
                vueInstance.modelValue = '年报';
                vueInstance.$emit && vueInstance.$emit('change', '年报');
            } else {
                console.warn("❌ 没找到 Vue 实例 __vue__");
            }
        """)

        print("⏳ 等待 Vue 渲染新表格数据...")
        time.sleep(3)

        # ✅ 获取表头列（去除“指标列”）
        header_row = page.query_selector("div.financial-statements .el-table__header-wrapper thead tr")
        header_cells = header_row.query_selector_all("th .cell")
        date_columns = [cell.inner_text().strip() for cell in header_cells if cell.inner_text().strip()]
        columns = ['指标名称'] + date_columns
        print(f"📊 表头列（共 {len(date_columns)} 年）: {date_columns}")

        # ✅ 提取表格行数据
        rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
        print(f"📄 数据行数: {len(rows)}")

        data = []
        for idx, row in enumerate(rows):
            cells = row.query_selector_all("td .cell")
            row_data = [cell.inner_text().strip() for cell in cells]
            if len(row_data) == len(columns):
                data.append(row_data)
            else:
                print(f"⚠️ 第 {idx+1} 行列数不匹配（{len(row_data)} vs {len(columns)}），跳过")

        browser.close()

        # ✅ 构建 DataFrame 并输出
        df = pd.DataFrame(data, columns=columns)
        print(f"\n✅ 最终抓取完成：{len(df)} 行 × {len(columns)} 列")
        print(df)

        # ✅ 可选导出 CSV 文件
        # df.to_csv("年报利润表.csv", index=False, encoding="utf-8-sig")

if __name__ == "__main__":
    scrape_year_reports_final()
