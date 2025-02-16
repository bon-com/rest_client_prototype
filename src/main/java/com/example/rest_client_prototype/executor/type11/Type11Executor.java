package com.example.rest_client_prototype.executor.type11;

import java.net.URI;
import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * REST APIクライアントにて、UriComponentsBuilderを使用して動的にクエリパラメータを作成する方法
 */
public class Type11Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			// URIのテンプレート
			String uriTemplate = "http://localhost:8080/rest_prototype/type5/";
			// URI作成　（例：http://localhost:8080/rest_prototype/type5/?name=%E3%81%94&hogeDate=2025-02-01）
			URI uri = UriComponentsBuilder.fromUriString(uriTemplate)
					.queryParam("name", "ご")
					.queryParam("hogeDate", "2025-02-01")
					.encode()
					.build()
					.toUri();

			// GETリクエスト
			RequestEntity<Void> reqEntity = RequestEntity.get(uri)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			ResponseEntity<Resource[]> resEntity = restApiClient.exchange(reqEntity, Resource[].class);
			Arrays.stream(resEntity.getBody()).forEach(r -> System.out.println(r));

		}
	}
}
