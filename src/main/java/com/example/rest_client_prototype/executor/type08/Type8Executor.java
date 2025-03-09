package com.example.rest_client_prototype.executor.type08;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.rest_client_prototype.biz.RestApiClient;

/**
 * RestTemplateを使用して、deleteを使用してリソースを削除する方法
 */
public class Type8Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			// 戻り値はなし
			restApiClient.delete("http://localhost:8080/rest_prototype/type4/{id}", "2");
			
		}
	}
}
