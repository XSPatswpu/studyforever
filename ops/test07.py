from playwright.sync_api import sync_playwright, TimeoutError

def extract_profit_statement_data(url):
    print("=== 开始执行数据提取 ===")
    try:
        with sync_playwright() as p:
            print("1. 启动浏览器...")
            browser = p.chromium.launch(headless=False)  # 调试时保持为False
            page = browser.new_page()
            print("2. 导航至目标页面:", url)
            page.goto(url)

            # 等待核心内容加载
            print("3. 等待核心内容加载...")
            page.wait_for_selector('.jc-main', timeout=10000)
            print("✅ 核心内容加载完成")

            # 精确选择利润表表格
            print("4. 查找利润表表格...")
            profit_statement_table = page.query_selector(
                'div.content.cFinancialStatements table.el-table__body'
            )

            if not profit_statement_table:
                raise ValueError("❌ 未找到利润表表格")
            print("✅ 利润表表格定位成功")

            # 提取表头
            print("5. 提取表头信息...")
            headers = []
            for th in page.query_selector_all('td.el-table_4_column_13'):
                header_text = th.inner_text().strip()
                headers.append(header_text)
            print("✅ 表头提取成功:", headers)

            # 提取数据行
            print("6. 提取数据行...")
            data = {}
            for row_idx, row in enumerate(profit_statement_table.query_selector_all('tr.el-table__row')):
                cells = [cell.inner_text().strip() for cell in row.query_selector_all('td div.cell')]
                if not cells:
                    continue

                # 假设第一列为指标名称，第二列为数值（根据实际结构调整）
                key = headers[0] if headers else f"行_{row_idx}"
                value = cells[1] if len(cells)>=2 else "数据缺失"

                data[key] = value
                print(f"✅ 提取第{row_idx+1}行数据: {key} -> {value}")

            print("=== 数据提取完成 ===")
            return data

    except TimeoutError:
        print("⚠️ 页面加载超时，请检查网络或目标URL")
    except ValueError as ve:
        print(f"⚠️ 错误: {ve}")
    except Exception as e:
        print(f"⚠️ 发生未知错误: {str(e)}")
    finally:
        print("=== 清理资源 ===")
        if 'browser' in locals():
            browser.close()

    print("=== 执行结束 ===")
    return {}

# 示例调用
url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"  # 替换为实际URL
result = extract_profit_statement_data(url)
print("\n=== 最终结果 ===")
print(result)