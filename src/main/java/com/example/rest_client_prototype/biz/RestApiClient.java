package com.example.rest_client_prototype.biz;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * @return
	 */
	public <T> T getJson(String url, Class<T> responseType) {
        // レスポンスを String 型で取得
        return restTemplate.getForObject(url, responseType);
		
	}
}
