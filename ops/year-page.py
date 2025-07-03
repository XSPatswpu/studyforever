from playwright.sync_api import sync_playwright
import pandas as pd
import time

def scrape_profit_table():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        context = browser.new_context()
        page = context.new_page()

        url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
        print(f"🌐 正在访问: {url}")
        page.goto(url, timeout=60000)
        page.wait_for_selector("div.financial-statements", timeout=20000)

        all_dfs = []
        page_num = 1

        while True:
            print(f"\n📄 正在处理第 {page_num} 页...")

            page.wait_for_selector("div.financial-statements .el-table__header-wrapper", timeout=20000)

            # ✅ 获取列头（只取第一行）
            header_rows = page.query_selector_all("div.financial-statements .el-table__header-wrapper thead tr")
            header_cells = header_rows[0].query_selector_all("th .cell")
            date_columns = [cell.inner_text().strip() for cell in header_cells if cell.inner_text().strip()]
            columns = ['指标名称'] + date_columns
            print(f"✅ 列头: {columns}")

            # ✅ 抓取数据行
            rows = page.query_selector_all("div.financial-statements .el-table__body tbody tr")
            print(f"✅ 数据行数: {len(rows)}")
            data = []
            for idx, row in enumerate(rows):
                cells = row.query_selector_all("td .cell")
                row_data = [cell.inner_text().strip() for cell in cells]
                if len(row_data) == len(columns):
                    data.append(row_data)
                else:
                    print(f"⚠️ 跳过第 {idx+1} 行（列数不匹配 {len(row_data)} vs {len(columns)}）: {row_data}")

            df = pd.DataFrame(data, columns=columns)

            # ✅ 筛选“指标列” + 年报列（12-31）
            year_end_cols = [col for col in columns[1:] if col.endswith('12-31')]
            if year_end_cols:
                final_df = df[['指标名称'] + year_end_cols]
                all_dfs.append(final_df)
                print(f"✅ 当前页保留 {len(year_end_cols)} 年报列: {year_end_cols}")
            else:
                print("⚠️ 当前页无年报列，跳过")

            # ✅ 判断是否还有下一页
            next_btn = page.query_selector("button.btn-next:not([disabled])")
            if next_btn:
                next_btn.click()
                time.sleep(2)
                page_num += 1
            else:
                print("\n🚩 没有下一页了，结束翻页")
                break

        browser.close()

        # ✅ 合并所有页的结果，并去重列
        if all_dfs:
            combined_df = pd.concat(all_dfs, axis=1)
            combined_df = combined_df.loc[:, ~combined_df.columns.duplicated()]
            print(f"\n✅ 所有年报数据合并完成（{combined_df.shape[0]} 行 × {combined_df.shape[1]} 列）:")
            print(combined_df)
        else:
            print("❌ 没有提取到任何年报数据")

if __name__ == "__main__":
    scrape_profit_table()
