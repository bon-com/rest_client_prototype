package com.example.rest_client_prototype.common.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * RestTemplateのHttpリクエスト/レスポンスの内容をログ出力するインターセプター
 */
public class RestApiClientInterceptor implements ClientHttpRequestInterceptor {
	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(RestApiClientInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		// リクエスト内容をログ出力
		logger.info("----------★★RestApiClient.java★★----------\n" +
				"★Request URI: {}\n" +
				"★Request Method: {}\n" +
				"★Request Headers: {}\n" +
				"★Request Body: {}",
				request.getURI(),
				request.getMethod(),
				request.getHeaders(),
				new String(body, StandardCharsets.UTF_8));

		// 実際にリクエスト送信
		ClientHttpResponse response = execution.execute(request, body);

		// レスポンスボディを再利用するために ByteArrayInputStream に変換
		// ※response.getBodyをログで使用すると、その後レスポンスボディを使いまわせない
		byte[] bodyBytes = response.getBody().readAllBytes();
		InputStream newBodyStream = new ByteArrayInputStream(bodyBytes);

		// レスポンス内容をログ出力
		String responseBody = new String(bodyBytes, StandardCharsets.UTF_8);
		logger.info("----------★★RestApiClient.java★★----------\n" +
				"★Response Status Code: {}\n" +
				"★Response Headers: {}\n" +
				"★Response Body: {}",
				response.getStatusCode(),
				response.getHeaders(),
				responseBody);

		// 新しいレスポンスオブジェクトを返却する
		return new ClientHttpResponseWrapper(response, newBodyStream);
	}

	/**
	 * 退避用レスポンスラッパークラス
	 */
	public static class ClientHttpResponseWrapper implements ClientHttpResponse {
		// 元の ClientHttpResponse
		private final ClientHttpResponse response;
		// レスポンスボディを保持するためのバイト配列
		private final byte[] bodyBytes;
		// 新しい InputStream を保持
		private final InputStream bodyStream;

		public ClientHttpResponseWrapper(ClientHttpResponse response, InputStream bodyStream) throws IOException {
			this.response = response;
			this.bodyBytes = bodyStream.readAllBytes();
			this.bodyStream = new ByteArrayInputStream(bodyBytes);
		}

		@Override
		public InputStream getBody() throws IOException {
			return bodyStream;
		}

		@Override
		public HttpHeaders getHeaders() {
			return response.getHeaders();
		}

		@Override
		public HttpStatus getStatusCode() throws IOException {
			return response.getStatusCode();
		}

		@Override
		public int getRawStatusCode() throws IOException {
			return response.getRawStatusCode();
		}

		@Override
		public String getStatusText() throws IOException {
			return response.getStatusText();
		}

		@Override
		public void close() {
			response.close();
		}
	}
}
