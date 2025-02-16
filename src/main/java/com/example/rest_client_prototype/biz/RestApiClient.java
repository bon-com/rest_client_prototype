package com.example.rest_client_prototype.biz;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Rest APIを呼び出すクライアント
 */
@Component
public class RestApiClient {
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * GETリクエストを行ない、指定した型のレスポンスを取得
	 * @param <T>
	 * @param url
	 * @param responseType
	 * @return responseTypeに指定した型
	 */
	public <T> T getJson(String url, Class<T> responseType) {
		// レスポンスを指定したresponseTypeの型で取得
		return restTemplate.getForObject(url, responseType);

	}

	/**
	 * GETリクエストを行ない、指定した型のレスポンスボディを保持するレスポンスエンティティを取得
	 * @param <T>
	 * @param url
	 * @param responseType
	 * @return responseTypeに指定した型のボディを保持するResponseEntity
	 */
	public <T> ResponseEntity<T> getJsonEntity(String url, Class<T> responseType) {
		// 指定した responseType の型でレスポンスを取得
		return restTemplate.getForEntity(url, responseType);
	}

	/**
	 * POSTリクエストを行ない、リソース作成後のロケーションURIを取得
	 * @param url
	 * @param requestBody
	 * @return リソース作成後の問い合わせ先URI
	 */
	public URI postJsonForLocation(String url, Object requestBody) {
		// ロケーションURIを返却
		return restTemplate.postForLocation(url, requestBody);
	}

	/**
	 * POSTリクエストを行ない、レスポンスエンティティを取得
	 * @param url
	 * @param requestBody
	 * @return ResponseEntity
	 */
	public ResponseEntity<Void> postJson(String url, Object requestBody) {
		return restTemplate.postForEntity(url, requestBody, Void.class);
	}

	/**
	 * PUTリクエストを行う
	 * @param url
	 * @param requestBody
	 * @param uriVariables
	 */
	public void put(String url, Object requestBody, Object... uriVariables) {
		// uriVariablesには可変引数が入る
		// https://example.com/api/resource/{id}/{name}など、プレースホルダの数だけ渡せる
		restTemplate.put(url, requestBody, uriVariables);
	}

	/**
	 * DELETEリクエストを行う
	 * @param url
	 * @param uriVariables
	 */
	public void delete(String url, Object... uriVariables) {
		// uriVariablesには可変引数が入る
		// https://example.com/api/resource/{id}/{name}など、プレースホルダの数だけ渡せる
		restTemplate.delete(url, uriVariables);
	}

	/**
	 * 汎用的な記載にしてGET,POST,PUT,DELETEリクエストを行う
	 * @param <T>
	 * @param requestEntity
	 * @param responseType
	 * @return responseTypeに指定した型のボディを保持するResponseEntity
	 */
	public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) {
		return restTemplate.exchange(requestEntity, responseType);
	}

	/**
	 * さらに汎用的な記載にしてGET,POST,PUT,DELETEリクエストを行う
	 * リクエストヘッダーなどに共通した値を設定する場合、RequestEntityの設定を隠蔽する
	 * @param <T>
	 * @param url URIテンプレート
	 * @param method HTTPメソッド
	 * @param body リクエストボディ
	 * @param responseType レスポンスの型
	 * @param queryParams クエリパラメータマップ
	 * @param uriVariables パスパラメータ可変長
	 * @return responseTypeに指定した型のボディを保持するResponseEntity
	 */
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method, Object body, Class<T> responseType,
			Map<String, String> queryParams, Object... uriVariables) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

		// クエリパラメータ設定
		if (queryParams != null) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
		}

		// 最終的にURIを取得
		URI uri = builder.buildAndExpand(uriVariables) // パスパラメータ設定
				.encode() // URIエンコード
				.toUri();

		// RequestEntityを共通化
		RequestEntity<Object> requestEntity = RequestEntity
				.method(method, uri)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer token") // Authorizationヘッダーを設定したり
				.header("Custom-Header", "CustomValue") // カスタムヘッダーを設定したり
				.body(body);

		// exchangeメソッドでリクエストを送信し、ResponseEntityを返却
		return restTemplate.exchange(requestEntity, responseType);
	}

}
