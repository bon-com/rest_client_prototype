package com.example.rest_client_prototype.executor.type04;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.rest_client_prototype.biz.RestApiClient;

/**
 * REST APIクライアントにて、getForEntityを使用してレスポンスエンティティを取得する方法
 * XMLファイルダウンロードAPI呼び出しなど  
 */
public class Type4Executor {

	public static void main(String args[]) {
		try (var context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml")) {
			var restApiClient = context.getBean(RestApiClient.class);

			// レスポンスエンティティを取得
			ResponseEntity<byte[]> resEntity = restApiClient
					.getJsonEntity("http://localhost:8080/rest_prototype/type11/xml", byte[].class);

			// --------------------------------------------------
			// HttpHeaders からは、Content-Disposition, Content-Type, Location, Authorization, Cache-Control など取得可能
			// --------------------------------------------------
			// ヘッダーを取得
			HttpHeaders headers = resEntity.getHeaders();

			// ヘッダーのContent-Dispositionからファイル名を取得
			String contentDisposition = headers.getFirst(HttpHeaders.CONTENT_DISPOSITION);
			String fileName = getFileName(contentDisposition);

			// ヘッダーのContent-typeを取得
			MediaType contentType = headers.getContentType(); // application/xml など

			// ロケーション
			String location = headers.getFirst(HttpHeaders.LOCATION);

			// ステータスコードを取得
			HttpStatus status = resEntity.getStatusCode(); // 200 OK など

			// レスポンスボディ部を取得
			byte[] xmlBody = resEntity.getBody();

		}
	}

	private static String getFileName(String contentDisposition) {
		if (contentDisposition == null) {
			return null;
		}

		List<NameValuePair> params = URLEncodedUtils.parse(contentDisposition, StandardCharsets.UTF_8);
		for (NameValuePair param : params) {
		    if ("filename".equalsIgnoreCase(param.getName()) || "filename*".equalsIgnoreCase(param.getName())) {
		        // 余計なダブルクォートを削除して返す
		        return param.getValue().replace("\"", "");
		    }
		}
		
		return null;
	}
}
