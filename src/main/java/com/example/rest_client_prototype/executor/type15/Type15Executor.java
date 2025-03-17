package com.example.rest_client_prototype.executor.type15;

import java.net.URI;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rest_client_prototype.biz.WebApiClient;

/**
 * WebClientを使用してGET通信する方法 
 */
public class Type15Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var webClient = context.getBean(WebApiClient.class);

			// URI定義
			URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8080/rest_prototype/type1/{id}")
					.buildAndExpand(1)
					.toUri();

			// API導通 
			String json = webClient.getJson(uri);
			System.out.println(json);
		}
	}
}
