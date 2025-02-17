# rest_client_prototype
RestAPIに対してリクエストするクライアントメモ

### ◆前提  
rest_prototypeをサーバーに追加して起動しておく
 
 
### ◆インデックス
 - type1：REST APIクライアントにて、getForObjectを使用してJSON文字列を取得する方法  
 - type2：REST APIクライアントにて、getForObjectを使用してオブジェクトを取得する方法 
 - type3：REST APIクライアントにて、getForObjectを使用してオブジェクトリストを取得する方法  
 - type4：REST APIクライアントにて、getForEntityを使用してレスポンスエンティティを取得する方法
 - type5：REST APIクライアントにて、postForLocationを使用してリソース作成後のロケーションURIのみ取得する方法
 - type6：REST APIクライアントにて、postForEntityを使用してPOSTリクエストした後、レスポンスエンティティを取得する方法 
 - type7：REST APIクライアントにて、putを使用してリソースを更新する方法
 - type8：REST APIクライアントにて、deleteを使用してリソースを削除する方法
 - type9：REST APIクライアントにて、exchangを使用してGET,POST,PUT,DELETEリクエストを行う方法
 - type10：REST APIクライアントにて、UriComponentsBuilderを使用して動的にパスパラメータを作成する方法
 - type11：REST APIクライアントにて、UriComponentsBuilderを使用して動的にクエリパラメータを作成する方法
 - type12：REST APIクライアントにて、exchangを使用してさらに汎用的にGET,POST,PUT,DELETEメソッドの呼び出しを行う方法
 - type13：REST APIクライアントにて、RestTemplateメソッドの例外ハンドリングする方法

  
### ◆補足  
## type1  
getForObjectを使用する  
GETリクエストを送信し、レスポンスボディを任意のオブジェクトに変換して取得する

## type4  
getForEntityを使用する  
GETリクエストを送信し、ヘッダー情報を含むレスポンスボディ情報を取得する  
例えばヘッダーにファイル名やロケーションURIが設定された際に、参照可能  

## type5  
POSTリクエスト先のAPIにて、リソース作成後に以下のようにLocationヘッダーを指定しているケースにて使用  
[ResponseEntity.created(URI.create(resourceUri)).build();]  
上記の記載は、HTTPレスポンスに201Createdステータスコードと、  
Locationヘッダーにリソースの場所（URI）の設定と、  
build()は空のレスポンスボディを作成  
という意味

## type6 
POSTリクエストした後、レスポンスヘッダーを取得したい時などに使用

## type9 
exchangeを使用する  
RequestEntityと取得したい戻り値の型を指定し、その戻り値の型をボディに保持するResponseEntityを返却する  
汎用的にメソッドを作成することができる

## type12 
exchangeを使用する  
リクエストヘッダーに共通した設定が存在する場合、  
RequestEntity自体を隠蔽する

## type13 
RestTemplate実行時の4xx系と5xx系のエラーハンドリング方法  
4xx系：HttpClientErrorExceptionがスローされる  
5xx系：HttpServerErrorExceptionがスローされる
