package com.example.rest_client_prototype.executor.type05;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * REST APIクライアントにて、postForLocationを使用してPOSTリクエストした後、リソース作成後のロケーションURIのみ取得する方法  
 */
public class Type5Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			var req = new Resource("4", "パスタ", LocalDate.of(2022, 5, 1));
			// 戻り値にロケーションURIを取得
			URI createdUri = restApiClient.postJsonForLocation("http://localhost:8080/rest_prototype/type2/create", req);
			
			System.out.println(createdUri); // http://localhost:8080/rest_prototype/type1/4 など
			
		}
	}
}
