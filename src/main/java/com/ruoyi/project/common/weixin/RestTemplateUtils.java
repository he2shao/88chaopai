package com.ruoyi.project.common.weixin;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class RestTemplateUtils {
	public static <T> T get(String url, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseType);
		return responseEntity.getBody();
	}

	public static void getFile(String url, HttpServletResponse httpServletResponse) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
//        HttpHeaders httpHeaders=responseEntity.getHeaders();
//        if(httpHeaders!=null){
//            httpServletResponse.setHeader("Content-Disposition",  httpHeaders.get("Content-Disposition").get(0));
//        }
		InputStream inputStream = new ByteArrayInputStream(responseEntity.getBody());
		IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
	}

	public static <T> T postForm(String url, Class<T> responseType, Map<String, Object> paramMap) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			map.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, responseType);
		return responseEntity.getBody();
	}

	public static <T> T postJson(String url, Class<T> responseType, String paramJson) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(paramJson, headers);
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, responseType);
		return responseEntity.getBody();
	}
}
