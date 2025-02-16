package com.example.rest_client_prototype.executor.type01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.rest_client_prototype.biz.RestApiClient;

/**
 * REST APIクライアントにて、getForObjectを使用してJSON文字列を取得する方法  
 */
public class Type1Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			// 戻り値にString文字列を指定
			String jsonRes = restApiClient.getJson("http://localhost:8080/rest_prototype/type1/1", String.class);
			
			System.out.println(jsonRes);
		}
	}
}
