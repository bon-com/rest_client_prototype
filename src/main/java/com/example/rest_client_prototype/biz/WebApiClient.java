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

	public String getJson(URI uri) {
		return webClient.get()
				.uri(uri)
                .retrieve()  // レスポンスを取得
                .bodyToMono(String.class)  // レスポンスボディをStringとして受け取る
                .block();  // 同期的に結果を取得
	}
}
