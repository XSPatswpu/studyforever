from playwright.sync_api import sync_playwright

def extract_stock_data(url):
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.goto(url)

        # 等待 jc-main 加载完成
        page.wait_for_selector('.jc-main', timeout=10000)

        # 提取公司名称和代码
        name_element = page.query_selector('.jc-main .news-header-title .name')
        company_name = name_element.inner_text().strip() if name_element else ''
        company_name = ' '.join(company_name.split())

        # 提取每个财务指标
        def extract_data(label_text):
            selector = f'xpath=//div[contains(@class, "right-item")]'
            items = page.query_selector_all(selector)
            for item in items:
                label_span = item.query_selector('span:nth-child(1)')
                value_span = item.query_selector('span:nth-child(2)')
                if label_span and label_text in label_span.inner_text():
                    return value_span.inner_text().strip()
            return None

        # 提取关键字段
        data = {
            "公司名称": company_name,
            "市盈率": extract_data("市盈率"),
            "市净率": extract_data("市净率"),
            "负债率": extract_data("负债率"),
            "成交量": extract_data("成交量"),
            "成交额": extract_data("成交额"),
            "净利润": extract_data("净利润"),
            "质押率": extract_data("质押率"),
            "换手率": extract_data("换手率"),
            "主营收入": extract_data("主营收入"),
            "货币资金": extract_data("货币资金"),
            "ROE": extract_data("ROE"),
            "商誉": extract_data("商誉"),
            "应收款": extract_data("应收款")
        }

        browser.close()
        return data

# 示例调用
url = "https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596#financialStatements"
result = extract_stock_data(url)
for key, value in result.items():
    print(f"{key}: {value}")
