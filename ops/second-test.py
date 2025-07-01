import requests

def fetch_financial_summary(stock_code, org_id):
    url = "https://webapi.cninfo.com.cn/api/announcement/stock-financial-indicators"

    headers = {
        "User-Agent": "Mozilla/5.0",
        "Referer": "https://www.cninfo.com.cn/",
    }

    params = {
        "scode": stock_code,
        "orgId": org_id,
        "market": "",          # 留空默认深市
    }

    resp = requests.get(url, params=params, headers=headers, timeout=10)
    resp.raise_for_status()

    data = resp.json().get("data", [])
    if not data:
        print("❌ 没有获取到数据")
        return

    latest = data[0]
    print(f"📅 报告期: {latest.get('REPORT_DATE')}")
    print(f"✅ 营业收入: {latest.get('BIZINCOME', 'N/A'):,}")
    print(f"✅ 净利润: {latest.get('NETPROFIT', 'N/A'):,}")
    print(f"✅ 应收账款: {latest.get('ACCREC', 'N/A'):,}")

# 示例调用
fetch_financial_summary("000596", "gssz0000596")
