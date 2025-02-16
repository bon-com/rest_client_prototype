package com.example.rest_client_prototype.executor.type09;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * REST APIクライアントにて、exchangを使用してGET,POST,PUT,DELETEリクエストを行う方法
 */
public class Type9Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			// READ（getメソッド）
			RequestEntity<Void> reqEntity = RequestEntity.get(URI.create("http://localhost:8080/rest_prototype/type1/1"))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			ResponseEntity<String> resEntity = restApiClient.exchange(reqEntity, String.class);
			System.out.println("★★json文字列★★:\n" + resEntity);
			
			// READ（getメソッド）
			RequestEntity<Void> reqEntity2 = RequestEntity.get(URI.create("http://localhost:8080/rest_prototype/type5/"))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			ResponseEntity<Resource[]> resEntity2 = restApiClient.exchange(reqEntity2, Resource[].class);
			System.out.println("\n★★オブジェクトリスト★★:");
			Arrays.stream(resEntity2.getBody()).forEach(r -> System.out.println(r));
			
			// CREATE（postメソッド）
			var reqBodyC = new Resource("4", "パスタ", LocalDate.of(2022, 5, 1));
			RequestEntity<Resource> reqEntity3 = RequestEntity.post(URI.create("http://localhost:8080/rest_prototype/type2/create"))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(reqBodyC);
			ResponseEntity<Void> resEntity3 = restApiClient.exchange(reqEntity3, Void.class);
			System.out.println("\n★★新規作成結果★★:\n" + resEntity3 + "\n");
			
			// UPDATE（putメソッド）
			var reqBodyU = new Resource("2", "パスタ", LocalDate.of(2022, 5, 1));
			RequestEntity<Resource> reqEntity4 = RequestEntity.put(URI.create("http://localhost:8080/rest_prototype/type3/2"))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(reqBodyU);
			ResponseEntity<Void> resEntity4 = restApiClient.exchange(reqEntity4, Void.class);
			System.out.println("\n★★更新結果★★:\n" + resEntity4 + "\n");
			
			// DELETE（deleteメソッド）
			RequestEntity<Void> reqEntity5 = RequestEntity.delete(URI.create("http://localhost:8080/rest_prototype/type4/2"))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			ResponseEntity<Void> resEntity5 = restApiClient.exchange(reqEntity5, Void.class);
			System.out.println("\n★★削除結果★★:\n" + resEntity5 + "\n");
			
		}
	}
}
