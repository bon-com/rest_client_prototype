package com.example.rest_client_prototype.executor.type14;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;
import com.example.rest_client_prototype.resources.Resource2;

/**
 * RestTemplateを使用して、POSTリクエストする際、値が設定されていないフィールドをJSONに含めない方法  
 */
public class Type14Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);

			// 値が設定されるケース
			var req = new Resource("4", "パスタ", null);
			restApiClient.exchange(
					"http://localhost:8080/rest_prototype/type2/create",
					HttpMethod.POST, req, Void.class, null, new Object[0]);

			// 値が設定されないケース
			var req2 = new Resource2("5", "たらこ", null);
			restApiClient.exchange(
					"http://localhost:8080/rest_prototype/type2/create",
					HttpMethod.POST, req2, Void.class, null, new Object[0]);
		}
	}
}
