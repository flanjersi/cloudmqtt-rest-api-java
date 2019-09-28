package fr.flanjersi.cloudmqttapi.authentication;

import com.squareup.okhttp.Request;

public interface ICredentials {

	public Request authenticate(Request request);
	
}
