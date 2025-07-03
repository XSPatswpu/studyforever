from playwright.sync_api import sync_playwright
import pandas as pd
import time

def scrape_year_reports():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        page = browser.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 正在访问: {url}")
        page.goto(url, timeout=60000)
        page.wait_for_selector("div.financial-statements", timeout=20000)

        print("👉 点击 '年报' 标签...")
        page.click("label:has-text('年报')")

        # ✅ 等待页面数据刷新
        print("⏳ 等待页面数据刷新...")
        page.wait_for_selector("div.financial-statements .el-table__header-wrapper", timeout=10000)
        page.wait_for_selector("div.financial-statements .el-table__body tbody tr td", timeout=10000)

        # ✅ 获取列头（含指标列）
        header_rows = page.query_selector_all("div.financial-statements .el-table__header-wrapper thead tr")
        header_cells = header_rows[0].query_selector_all("th .cell")
        date_columns = [cell.inner_text().strip() for cell in header_cells if cell.inner_text().strip()]
        columns = ['指标名称'] + date_columns
        print(f"📊 年报列头: {columns}")

        # ✅ 尝试抓取行数据，轮询等待行数与列头一致
        max_retry = 5
        data = []
        for attempt in range(max_retry):
            rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
            print(f"📄 当前尝试第 {attempt+1} 次，检测到数据行数: {len(rows)}")

            data.clear()
            for idx, row in enumerate(rows):
                cells = row.query_selector_all("td .cell")
                row_data = [cell.inner_text().strip() for cell in cells]
                if len(row_data) == len(columns):
                    data.append(row_data)
                else:
                    print(f"⚠️ 第 {idx+1} 行列数不匹配（{len(row_data)} vs {len(columns)}）")

            if len(data) > 0:
                break  # ✅ 行列匹配成功，退出循环
            else:
                print("⏳ 数据未同步，等待 1 秒后重试...")
                time.sleep(1)

        browser.close()

        if not data:
            print("❌ 最终未能抓到有效数据，请检查页面加载状态")
            return

        df = pd.DataFrame(data, columns=columns)

        # ✅ 只保留“指标名称” + 年报列
        year_cols = [col for col in columns[1:] if col.endswith("12-31")]
        df = df[['指标名称'] + year_cols]

        print(f"\n✅ 年报数据提取完成（共 {len(year_cols)} 年）:")
        print(df)

if __name__ == "__main__":
    scrape_year_reports()
