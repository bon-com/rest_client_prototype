package com.example.rest_client_prototype.executor.type02;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * RestTemplateを使用して、getForObjectを使用してオブジェクトを取得する方法  
 */
public class Type2Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			// 戻り値にオブジェクトを指定
			Resource res = restApiClient.getJson("http://localhost:8080/rest_prototype/type1/1", Resource.class);
			
			System.out.println(res);
		}
	}
}
