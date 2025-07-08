import requests

url = "http://www.cninfo.com.cn/new/data/szse_stock.json"

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0 Safari/537.36",
    "Referer": "http://www.cninfo.com.cn/"
}

try:
    print(f"📡 请求接口: {url}")
    resp = requests.get(url, headers=headers, timeout=15)
    resp.raise_for_status()

    content = resp.text
    print(f"📦 响应内容前 300 字符:\n{content[:300]}")

    json_data = resp.json()
    print(f"\n✅ 成功解析 JSON，键名: {list(json_data.keys()) if isinstance(json_data, dict) else type(json_data)}")
except Exception as e:
    print(f"\n❌ 请求失败: {str(e)}")
