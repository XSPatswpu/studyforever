import requests
import pandas as pd
import time
import traceback
from scrape_es_bulk_final import scrape_to_bulk_json

def fetch_stock_json(url):
    headers = {
        "User-Agent": "Mozilla/5.0",
        "Referer": "http://www.cninfo.com.cn/"
    }
    print(f"🌐 请求: {url}")
    resp = requests.get(url, headers=headers, timeout=15)
    resp.raise_for_status()
    try:
        data = resp.json()
        if isinstance(data, dict) and "stockList" in data:
            return data["stockList"]
        return data
    except Exception as e:
        print(f"❌ JSON 解析失败: {e}")
        return []

def load_all_stocks():
    print("📥 正在下载全量 A 股公司列表...")
    sz_list = fetch_stock_json("http://www.cninfo.com.cn/new/data/szse_stock.json")
#     sh_list = fetch_stock_json("http://www.cninfo.com.cn/new/data/shse_stock.json")

#     all_list = sz_list + sh_list
    df = pd.DataFrame(sz_list)
    df = df[df["category"] == "A股"]  # 仅 A 股
    df = df[["code", "zwjc", "orgId"]].drop_duplicates()
    print(f"✅ 获取到 A 股公司共计: {len(df)} 家")
    return df

def batch_scrape():
    df = load_all_stocks()
    failed = []

    for idx, row in df.iterrows():
        code = row["code"]
        name = row["zwjc"]
        org_id = row["orgId"]

        print(f"\n🚀 正在抓取: {code} {name}")
        try:
            scrape_to_bulk_json(stock_code=code, stock_name=name, org_id=org_id)
            time.sleep(1.5)  # 防止过快触发限流
        except Exception:
            print(f"❌ 抓取失败: {code} {name}")
            print(traceback.format_exc())
            failed.append({"code": code, "name": name})

    if failed:
        print(f"\n⚠️ 抓取失败 {len(failed)} 项:")
        for item in failed:
            print(f"- {item['code']} {item['name']}")
    else:
        print("\n✅ 全部公司抓取完成！")

if __name__ == "__main__":
    batch_scrape()
