package fr.flanjersi.cloudmqttapi.authentication;

import java.util.Base64;

import com.squareup.okhttp.Request;

public class BasicAuthCredentials implements ICredentials{

	private String apiKey;
	
	public BasicAuthCredentials(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public Request authenticate(Request request) {
		return request.newBuilder()
				.addHeader("Authorization", getAuthorizationToken())
				.build();
	}
	
	private String getAuthorizationToken() {
		String strToEncode = ":" + apiKey;
		
		byte[] strEncoded = Base64
				.getEncoder()
				.encode(strToEncode.getBytes());
		
		return "Basic " + new String(strEncoded);
	}

}
