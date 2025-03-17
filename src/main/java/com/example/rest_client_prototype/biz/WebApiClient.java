package com.example.rest_client_prototype.biz;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClientを使用したAPI疎通クラス
 */
@Component
public class WebApiClient {

	@Autowired
	private WebClient webClient;

	/**
	 * GETリクエストを行ない、JSON文字列を返却
	 * @param uri
	 * @return
	 */
	public String getJson(URI uri) {
		return webClient.get()
				.uri(uri)
                .retrieve()  // レスポンスを取得
                .bodyToMono(String.class)  // レスポンスボディをStringとして受け取る
                .block();  // 同期的に結果を取得
	}
	
	/**
	 * GETリクエストを行ない、指定した型のレスポンスを取得
	 * @param uri
	 * @param responseType
	 * @return
	 */
	public <T> T getJson(URI uri, Class<T> responseType) {
		return webClient.get()
				.uri(uri)
                .retrieve()  // レスポンスを取得
                .bodyToMono(responseType)  // レスポンスボディを指定した型で受け取る
                .block();  // 同期的に結果を取得
	}
}
