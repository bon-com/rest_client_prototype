package com.example.rest_client_prototype.biz;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Rest APIを呼び出すクライアント
 */
@Component
public class RestApiClient {
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * GETリクエストを行ない、指定した型のレスポンスを受け取る
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
	 * GETリクエストを行ない、指定した型のレスポンスボディを保持するレスポンスエンティティを受け取る
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

}
