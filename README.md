# rest_client_prototype
RestAPIに対してリクエストするクライアントメモ

### ◆前提  
rest_prototypeをサーバーに追加して起動しておく
 
 
### ◆インデックス
 - type1：REST APIクライアントにて、getForObjectを使用してJSON文字列を取得する方法  
 - type2：REST APIクライアントにて、getForObjectを使用してオブジェクトを取得する方法 
 - type3：REST APIクライアントにて、getForObjectを使用してオブジェクトリストを取得する方法  

  
### ◆補足  
## type1  
getForObjectを使用する  
GETリクエストを送信し、レスポンスボディを任意のオブジェクトに変換して取得する
