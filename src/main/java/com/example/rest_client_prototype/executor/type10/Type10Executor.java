package com.example.rest_client_prototype.executor.type10;

import java.net.URI;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rest_client_prototype.biz.RestApiClient;

/**
 * REST APIクライアントにて、UriComponentsBuilderを使用して動的にパスパラメータを作成する方法
 */
public class Type10Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			// URIのテンプレート
			String uriTemplate = "http://localhost:8080/rest_prototype/type1/{id}";
			// バインドさせたい値
			String uriVal = "3";
			// URI作成
			URI uri = UriComponentsBuilder.fromUriString(uriTemplate)
					.buildAndExpand(uriVal)
					.encode()
					.toUri();

			// GETリクエスト
			RequestEntity<Void> reqEntity = RequestEntity.get(uri)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			ResponseEntity<String> resEntity = restApiClient.exchange(reqEntity, String.class);
			System.out.println(resEntity);

		}
	}
}
