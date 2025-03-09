package com.example.rest_client_prototype.executor.type06;

import java.time.LocalDate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * RestTemplateを使用して、postForEntityを使用してPOSTリクエストした後、レスポンスエンティティを取得する方法  
 */
public class Type6Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			var req = new Resource("4", "パスタ", LocalDate.of(2022, 5, 1));
			// 戻り値にレスポンスエンティティを取得
			ResponseEntity<Void> resEntity = restApiClient.postJson("http://localhost:8080/rest_prototype/type2/create", req);
			
			// ResponseEntityから、レスポンスヘッダーを参照可能
			System.out.println(resEntity);
		}
	}
}
