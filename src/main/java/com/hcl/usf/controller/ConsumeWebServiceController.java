package com.hcl.usf.controller;

import java.io.IOException;
import java.net.URI;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * @author intakhabalam.s@hcl.com
 */
@RestController(value = "API Service")
@CrossOrigin
public class ConsumeWebServiceController {
	@Autowired
	RestTemplate restTemplate;
    /**
     * @param cosumerId {@link String}
     * @param correlationId {@link String}
     * @return {@link String}
     */
	@GetMapping(value = "/api/listheader")
	public String getListHeaders(
			@RequestHeader(name = "consumer-id", required = true, defaultValue = "ECOM") String cosumerId,
			@RequestHeader(name = "correlation-id", required = true, defaultValue = "ECOM") String correlationId) {
		/*javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});*/
		   String urlStr = "https://listshopping-api-sit.main.usfood.com/list-shopping-api/v1/listHeaders?customer=3687969&division=3023&department=1&listTypes=SL";
		   URI uri = URI.create(urlStr);

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("correlation-id", correlationId);
			headers.add("consumer-id", cosumerId);
			headers.setAccessControlAllowCredentials(false);
			headers.set("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			return restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return "BAD BOY-"+e.getMessage();
		}
	}
	 /**
     * @param cosumerId {@link String}
     * @param correlationId {@link String}
     * @return {@link String}
     */
	@GetMapping(value = "/api/listapi")
	public String getListApi(
			@RequestHeader(name = "consumer-id", required = true, defaultValue = "ECOM") String cosumerId,
			@RequestHeader(name = "correlation-id", required = true, defaultValue = "ECOM") String correlationId) {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});
		String url = "https://listshopping-api-sit.main.usfood.com/list-shopping-api/v1/lists/SL-25369?dataProfile=header,groups,items&clientId=AVENDRA&clientConcept=AVE";
		URI uri = URI.create(url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("correlation-id", correlationId);
			headers.add("consumer-id", cosumerId);
			headers.setAccessControlAllowCredentials(false);
			headers.set("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			return restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*@GetMapping(value = "/api/listHeaders")
	public String getListHeaders(
			@RequestHeader(name = "consumer-id", required = true, defaultValue = "ECOM") String cosumerId,
			@RequestHeader(name = "correlation-id", required = true, defaultValue = "ECOM") String correlationId,
			@RequestParam(name="customer")String customer,
			@RequestParam(name="division")String division,
			@RequestParam(name="department")String department,
			@RequestParam(name="listTypes")String listTypes
			) {
		
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});
		String url = "https://listshopping-api-sit.main.usfood.com/list-shopping-api/v1/listHeaders";
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("customer", customer);
			params.put("division", division);
			params.put("department", department);
			params.put("listTypes", listTypes);


			HttpHeaders headers = new HttpHeaders();
			headers.add("correlation-id", correlationId);
			headers.add("consumer-id", cosumerId);
			headers.setAccessControlAllowCredentials(false);
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity,String.class,params).getBody();
		} catch (Exception e) {
			return "BAD REQ-"+e.getMessage();
		}
	}*/
}

class CustomDateFormatter extends JsonSerializer<Date> {

    private final DateTimeFormatter formatter;

    public CustomDateFormatter() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String str = formatter.format(value.toInstant());
        gen.writeString(str);
    }
}

