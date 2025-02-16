package com.example.rest_client_prototype.executor.type12;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * REST APIクライアントにて、exchangを使用してさらに汎用的にGET,POST,PUT,DELETEメソッドの呼び出しを行う方法  
 */
public class Type12Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);

			// READ（getメソッド）
			ResponseEntity<String> resEntity = restApiClient.exchange("http://localhost:8080/rest_prototype/type1/{id}",
					HttpMethod.GET, null, String.class, null, new Object[] { "3" });
			System.out.println("★★json文字列★★:\n" + resEntity);

			// READ（getメソッド）
			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("name", "ご");
			ResponseEntity<Resource[]> resEntity2 = restApiClient.exchange(
					"http://localhost:8080/rest_prototype/type5/",
					HttpMethod.GET, null, Resource[].class, queryParams, new Object[0]);
			System.out.println("\n★★オブジェクトリスト★★:");
			Arrays.stream(resEntity2.getBody()).forEach(r -> System.out.println(r));

			// CREATE（postメソッド）
			var reqBodyC = new Resource("4", "パスタ", LocalDate.of(2022, 5, 1));
			ResponseEntity<Void> resEntity3 = restApiClient.exchange(
					"http://localhost:8080/rest_prototype/type2/create",
					HttpMethod.POST, reqBodyC, Void.class, null, new Object[0]);
			System.out.println("\n★★新規作成結果★★:\n" + resEntity3 + "\n");

			// UPDATE（putメソッド）
			var reqBodyU = new Resource("2", "パスタ", LocalDate.of(2022, 5, 1));
			ResponseEntity<Void> resEntity4 = restApiClient.exchange("http://localhost:8080/rest_prototype/type3/{id}",
					HttpMethod.PUT, reqBodyU, Void.class, null, new Object[] { "2" });
			System.out.println("\n★★更新結果★★:\n" + resEntity4 + "\n");

			// DELETE（deleteメソッド）
			ResponseEntity<Void> resEntity5 = restApiClient.exchange("http://localhost:8080/rest_prototype/type4/{id}",
					HttpMethod.DELETE, null, Void.class, null, new Object[] { "1" });
			System.out.println("\n★★削除結果★★:\n" + resEntity5 + "\n");
		}
	}
}
