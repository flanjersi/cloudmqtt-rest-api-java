package fr.flanjersi.cloudmqttapi.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import fr.flanjersi.cloudmqttapi.authentication.BasicAuthCredentials;
import fr.flanjersi.cloudmqttapi.authentication.ICredentials;
import fr.flanjersi.cloudmqttapi.exception.RestClientException;

public class RestClient {
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private URL host;
	
	private ICredentials creds;
	
	private OkHttpClient httpClient;
	
	
	public RestClient(URL host, String apiKey) {
		this(host, new BasicAuthCredentials(apiKey));
	}
	
	public RestClient(URL host, ICredentials creds) {
		this.host = host;
		this.creds = creds;
		
		this.httpClient = new OkHttpClient();
	}
	
	private URL buildURL(String endpoint) throws MalformedURLException {
		if(endpoint.startsWith("/")) {
			return new URL(host, "api" + endpoint);
		}
		else {
			return new URL(host, "api/" + endpoint);
		}
		
	}
	
	public JsonNode request(String endpoint) throws RestClientException {
		URL url;
		
		try {
			url = buildURL(endpoint);
		} catch (MalformedURLException e) {
			throw new RestClientException("Can't create the url of the request", e);
		}
		
		Request request = new Request.Builder()
					  .url(url)
					  .get()
					  .build();
		
		request = creds.authenticate(request);
		
		Response response;
		
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			throw new RestClientException(String.format("Error throwed when attempt to send the request : %s", url), e);
		}
		
		if(!response.isSuccessful()) {
			throw new RestClientException(String.format("Request '%s' not received a successful response, code status : %d", url, response.code()));
		}
		
		String subType = response.body().contentType().subtype();
		
		if(!subType.equals("json")) {
			throw new RestClientException(String.format("Request '%s' not received a body with the JSON format, content-type : %s", url, response.body().contentType()));
		}
		
		try {
			return payloadToJson(response.body().string());
		} catch (IOException e) {
			throw new RestClientException(String.format("Error throwed when attempt to get the payload of the response of the request : %s", url), e);
		}
	}
	
	public void post(String endpoint, String payload) throws RestClientException {
		URL url;
		
		try {
			url = buildURL(endpoint);
		} catch (MalformedURLException e) {
			throw new RestClientException("Can't create the url of the request", e);
		}
		
		Request request = new Request.Builder()
					  .url(url)
					  .post(RequestBody.create(JSON, payload))
					  .build();
		
		request = creds.authenticate(request);
		
		Response response;
		
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			throw new RestClientException(String.format("Error throwed when attempt to send the request : %s", url), e);
		}
		
		if(!response.isSuccessful()) {
			throw new RestClientException(String.format("Request '%s' not received a successful response, code status : %d", url, response.code()));
		}
	}
	
	public void delete(String endpoint) throws RestClientException {
		URL url;
		
		try {
			url = buildURL(endpoint);
		} catch (MalformedURLException e) {
			throw new RestClientException("Can't create the url of the request", e);
		}
		
		Request request = new Request.Builder()
					  .url(url)
					  .delete()
					  .build();
		
		request = creds.authenticate(request);
		
		Response response;
		
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			throw new RestClientException(String.format("Error throwed when attempt to send the request : %s", url), e);
		}
		
		if(!response.isSuccessful()) {
			throw new RestClientException(String.format("Request '%s' not received a successful response, code status : %d", url, response.code()));
		}
	}
	
	public void delete(String endpoint, String payload) throws RestClientException {
		URL url;
		
		try {
			url = buildURL(endpoint);
		} catch (MalformedURLException e) {
			throw new RestClientException("Can't create the url of the request", e);
		}
		
		Request request = new Request.Builder()
					  .url(url)
					  .delete(RequestBody.create(JSON, payload))
					  .build();
		
		request = creds.authenticate(request);
		
		Response response;
		
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			throw new RestClientException(String.format("Error throwed when attempt to send the request : %s", url), e);
		}
		
		if(!response.isSuccessful()) {
			throw new RestClientException(String.format("Request '%s' not received a successful response, code status : %d", url, response.code()));
		}
	}
	
	public void put(String endpoint, String payload) throws RestClientException {
		URL url;
		
		try {
			url = buildURL(endpoint);
		} catch (MalformedURLException e) {
			throw new RestClientException("Can't create the url of the request", e);
		}
		
		Request request = new Request.Builder()
					  .url(url)
					  .put(RequestBody.create(JSON, payload))
					  .build();
		
		request = creds.authenticate(request);
		
		Response response;
		
		try {
			response = httpClient.newCall(request).execute();
		} catch (IOException e) {
			throw new RestClientException(String.format("Error throwed when attempt to send the request : %s", url), e);
		}
		
		if(!response.isSuccessful()) {
			throw new RestClientException(String.format("Request '%s' not received a successful response, code status : %d", url, response.code()));
		}
	}
	
	public JsonNode payloadToJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
	    
		return mapper.readTree(json);
	}

}
