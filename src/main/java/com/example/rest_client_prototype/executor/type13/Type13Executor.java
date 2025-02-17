package com.example.rest_client_prototype.executor.type13;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.example.rest_client_prototype.biz.RestApiClient;

/**
 * REST APIクライアントにて、例外ハンドリングする方法
 */
public class Type13Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);

			try {
				// エラーが発生するリクエスト
				restApiClient.exchange("http://localhost:8080/rest_prototype/type9/{id}",
						HttpMethod.GET, null, String.class, null, new Object[] { "6" }); 
			} catch (HttpClientErrorException e) {
				// 4xx系エラー（クライアントエラー）
				System.err.println("ステータスコード: " + e.getStatusCode());
				System.err.println("レスポンスボディ: " + e.getResponseBodyAsString());
			}
			
			try {
				// エラーが発生するリクエスト
				restApiClient.exchange("http://localhost:8080/rest_prototype/type10/error",
						HttpMethod.GET, null, Void.class, null, new Object[] {}); 
			} catch (HttpServerErrorException e) {
				// 5xx系エラー（サーバーエラー）
				System.err.println("ステータスコード: " + e.getStatusCode());
				System.err.println("レスポンスボディ: " + e.getResponseBodyAsString());
			}
			
		}
	}
}
