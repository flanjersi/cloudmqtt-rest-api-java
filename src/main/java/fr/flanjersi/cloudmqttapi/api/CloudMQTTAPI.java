package fr.flanjersi.cloudmqttapi.api;

import java.net.MalformedURLException;
import java.net.URL;

import fr.flanjersi.cloudmqttapi.client.RestClient;
import fr.flanjersi.cloudmqttapi.exception.CloudMQTTAPIException;
import fr.flanjersi.cloudmqttapi.exception.RestClientException;
import fr.flanjersi.cloudmqttapi.utils.UUIDUtils;

/**
 * 
 * @author Flanjersi
 *
 */
public class CloudMQTTAPI {
	
	private RestClient restClient;
	
	private InstanceSectionAPI instanceAPI;
	private UserSectionAPI userAPI;
	private ACLSectionAPI aclAPI;
	
	
	public CloudMQTTAPI(String apiKey) throws CloudMQTTAPIException {
		
		if(apiKey == null) throw new CloudMQTTAPIException("The API key could not be null, it must be in the UUID format");
		if(apiKey.isEmpty() || !UUIDUtils.isUUID(apiKey)) throw new CloudMQTTAPIException("The API key must be in the UUID format");
		
		try {
			this.restClient = new RestClient(new URL("https://api.cloudmqtt.com"), apiKey);
		} catch (MalformedURLException e) {
			throw new CloudMQTTAPIException("Error when attempt to format host url");
		}
		
		if(!checkAuthentication()) {
			throw new CloudMQTTAPIException("Network is not ready or your API key is not valide");
		}
		
		this.instanceAPI = new InstanceSectionAPI(restClient);
		this.userAPI = new UserSectionAPI(restClient);
		this.aclAPI = new ACLSectionAPI(restClient);
	}
	
	private boolean checkAuthentication() throws CloudMQTTAPIException {
		
		try {
			restClient.request("user");
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException("Can't validate the authentication to the cloud mqtt rest api", e);
		}
		
		return true;
	}

	public InstanceSectionAPI getInstanceAPI() {
		return instanceAPI;
	}

	public UserSectionAPI getUserAPI() {
		return userAPI;
	}

	public ACLSectionAPI getAclAPI() {
		return aclAPI;
	}

	
}
