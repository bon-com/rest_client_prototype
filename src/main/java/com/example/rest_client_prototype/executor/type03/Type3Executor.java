package com.example.rest_client_prototype.executor.type03;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.rest_client_prototype.biz.RestApiClient;
import com.example.rest_client_prototype.resources.Resource;

/**
 * RestTemplateを使用して、getForObjectを使用してオブジェクトリストを取得する方法  
 */
public class Type3Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);
			
			// 戻り値にオブジェクトを指定
			Resource[] resArr = restApiClient.getJson("http://localhost:8080/rest_prototype/type5/", Resource[].class);
			List<Resource> resources = Arrays.asList(resArr);
		
			resources.forEach(r -> System.out.println(r));
		}
	}
}
