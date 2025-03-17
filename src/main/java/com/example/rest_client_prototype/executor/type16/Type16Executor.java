package com.example.rest_client_prototype.executor.type16;

import java.net.URI;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rest_client_prototype.biz.WebApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * WebClientを使用してJSON文字列を取得する方法  
 */
public class Type16Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var webClient = context.getBean(WebApiClient.class);

			// URI定義
			URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8080/rest_prototype/type1/{id}")
					.buildAndExpand(1)
					.toUri();

			// API導通 
			Resource res = webClient.getJson(uri, Resource.class);
			System.out.println(res);
		}
	}
}
