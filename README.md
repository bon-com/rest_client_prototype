# rest_client_prototype
RestAPIに対してリクエストするクライアントメモ

### ◆前提  
rest_prototypeをサーバーに追加して起動しておく
 
 
### ◆インデックス
## ◇RestTemplate
 - type1：RestTemplateを使用して、getForObjectを使用してJSON文字列を取得する方法  
 - type2：RestTemplateを使用して、getForObjectを使用してオブジェクトを取得する方法 
 - type3：RestTemplateを使用して、getForObjectを使用してオブジェクトリストを取得する方法  
 - type4：RestTemplateを使用して、getForEntityを使用してレスポンスエンティティを取得する方法
 - type5：RestTemplateを使用して、postForLocationを使用してリソース作成後のロケーションURIのみ取得する方法
 - type6：RestTemplateを使用して、postForEntityを使用してPOSTリクエストした後、レスポンスエンティティを取得する方法 
 - type7：RestTemplateを使用して、putを使用してリソースを更新する方法
 - type8：RestTemplateを使用して、deleteを使用してリソースを削除する方法
 - type9：RestTemplateを使用して、exchangを使用してGET,POST,PUT,DELETEリクエストを行う方法
 - type10：RestTemplateを使用して、UriComponentsBuilderを使用して動的にパスパラメータを作成する方法
 - type11：RestTemplateを使用して、UriComponentsBuilderを使用して動的にクエリパラメータを作成する方法
 - type12：RestTemplateを使用して、exchangを使用してさらに汎用的にGET,POST,PUT,DELETEメソッドの呼び出しを行う方法
 - type13：RestTemplateを使用して、RestTemplateメソッドの例外ハンドリングする方法
 - type14：RestTemplateを使用して、POSTリクエストする際、値が設定されていないフィールドをJSONに含めない方法
 - 共通機能01：RestTemplateを使用して、JSONをログに出力する方法
 
## ◇WebClient
 - type15：WebClientを使用して、GET通信でJSON文字列を取得する方法
 - type16：WebClientを使用して、GET通信でオブジェクトを取得する方法 
 - type17：WebClientを使用して、GET通信でリストを取得する方法 
 
 
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
戻り値の型にListやMapなどジェネリクスを含むケースも取得可能にした

## type13 
RestTemplate実行時の4xx系と5xx系のエラーハンドリング方法  
4xx系：HttpClientErrorExceptionがスローされる  
5xx系：HttpServerErrorExceptionがスローされる

## 共通01
ClientHttpRequestInterceptorの機能を使用して  
RestTemplateのリクエスト前後にログ出力処理を入れる  
ログライブラリの依存資材が必要

## type15
RestTemplateは今後廃止される予定  
これからはWebClientを使用する  
WebClientは基本的に非同期通信を行うために設計されているが、同期通信も可能
WebClientを使用するためには、  
spring-webfluxライブラリ等を導入する  
Bean定義する（今回XMLベースでBean定義がなかなかうまくいかなかったため、Javaアノテーションベースにした）  


