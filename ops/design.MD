# how to scrape data

scrape steps:
1. get all the name of the stock in chinese
2. invoke the search API to get the stock code
3. execute [scrape_es_bulk_final.py](scrape_es_bulk_final.py)

# search API
```shell
curl 'https://www.cninfo.com.cn/new/information/topSearch/query?keyWord=%E8%B4%B5%E5%B7%9E%E8%8C%85%E5%8F%B0&maxNum=10' \
  -X 'POST' \
  -H 'Accept: application/json, text/plain, */*' \
  -H 'Accept-Language: zh-CN,zh;q=0.9' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 0' \
  -b 'JSESSIONID=5FB8CFB5ADB893E732FBC2E39B338E23; SF_cookie_4=82505831; _sp_ses.2141=*; SID=503f1107-ba44-4fdf-9b34-37dbe2d23186; routeId=.uc1; cninfo_user_browse=600519,gssh0600519,%E8%B4%B5%E5%B7%9E%E8%8C%85%E5%8F%B0|000596,gssz0000596,%E5%8F%A4%E4%BA%95%E8%B4%A1%E9%85%92; _sp_id.2141=0eb117a3-ead9-4d9f-9bb5-5f21e3f3e7b5.1747366128.12.1751610197.1751529009.3e3039e6-1725-4291-add8-ab2391098c54' \
  -H 'Origin: https://www.cninfo.com.cn' \
  -H 'Referer: https://www.cninfo.com.cn/new/disclosure/stock?orgId=gssz0000596&stockCode=000596' \
  -H 'Sec-Fetch-Dest: empty' \
  -H 'Sec-Fetch-Mode: cors' \
  -H 'Sec-Fetch-Site: same-origin' \
  -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36' \
  -H 'sec-ch-ua: "Google Chrome";v="137", "Chromium";v="137", "Not/A)Brand";v="24"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "macOS"'
```
response:

```json
[
    {
        "code": "600519",
        "pinyin": "gzmt",
        "sjstsBond": "false",
        "category": "A股",
        "type": "shj",
        "orgId": "gssh0600519",
        "zwjc": "贵州茅台"
    }
]
```
