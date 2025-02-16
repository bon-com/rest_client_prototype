# rest_client_prototype
RestAPIに対してリクエストするクライアントメモ

### ◆前提  
rest_prototypeをサーバーに追加して起動しておく
 
 
### ◆インデックス
 - type1：REST APIクライアントにて、getForObjectを使用してJSON文字列を取得する方法  
 - type2：REST APIクライアントにて、getForObjectを使用してオブジェクトを取得する方法 
 - type3：REST APIクライアントにて、getForObjectを使用してオブジェクトリストを取得する方法  
 - type4：REST APIクライアントにて、getForEntityを使用してレスポンスエンティティを取得する方法

  
### ◆補足  
## type1  
getForObjectを使用する  
GETリクエストを送信し、レスポンスボディを任意のオブジェクトに変換して取得する

## type4  
getForEntityを使用する  
GETリクエストを送信し、ヘッダー情報を含むレスポンスボディ情報を取得する  
例えばヘッダーにファイル名やロケーションURIが設定された際に、参照可能  


