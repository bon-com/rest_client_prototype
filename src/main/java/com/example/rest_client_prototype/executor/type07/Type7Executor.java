package com.example.rest_client_prototype.executor.type07;

import java.time.LocalDate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * REST APIクライアントにて、putを使用してリソースを更新する方法
 */
public class Type7Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			var req = new Resource("2", "パスタ", LocalDate.of(2022, 5, 1));
			// 戻り値はなし
			restApiClient.put("http://localhost:8080/rest_prototype/type3/{id}", req, "2");
			
		}
	}
}
