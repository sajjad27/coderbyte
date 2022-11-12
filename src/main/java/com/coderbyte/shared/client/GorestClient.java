package com.coderbyte.shared.client;

import java.net.URI;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderbyte.shared.exception.CoderbyteException;
import com.coderbyte.shared.exception.model.errorresponse.KeyValue;

@Service
public class GorestClient {

	private RestTemplate restTemplate;

	public GorestClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public <T> T get(String url, Class<T> responseType, String clientName) {
		try {
			return this.restTemplate.getForObject(url, responseType);
		} catch (ResourceAccessException e) {
			throw new CoderbyteException("CONNECTION_TIMOUT", new KeyValue("{{CLIENT_NAME}}", clientName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoderbyteException("KNOWN_CLIENT_ERROR", new KeyValue("{{CLIENT_NAME}}", clientName));
		}
	}

	public <T> T get(String url, Class<T> responseType, String clientName, MultiValueMap<String, String> params) {
		URI targetUrl = UriComponentsBuilder.fromUriString(url).queryParams(params).build().encode().toUri();
		return this.get(targetUrl.toString(), responseType, clientName);
	}

}
