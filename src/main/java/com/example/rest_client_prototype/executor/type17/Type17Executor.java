package com.example.rest_client_prototype.executor.type17;

import java.net.URI;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rest_client_prototype.biz.WebApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * WebClientを使用してGET通信する方法
 */
public class Type17Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var webClient = context.getBean(WebApiClient.class);

			// URI定義
			URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8080/rest_prototype/type5/")
					.build()
					.toUri();

			// API導通
			// ParameterizedTypeReferenceの引数にリストを渡すと、戻り値はリストになる
			List<Resource> resList = webClient.getJson(uri, new ParameterizedTypeReference<List<Resource>>() {});
			resList.forEach(r -> System.out.println(r));
		}
	}
}
