package fr.flanjersi.cloudmqttapi.api;

import fr.flanjersi.cloudmqttapi.client.RestClient;
import fr.flanjersi.cloudmqttapi.exception.CloudMQTTAPIException;
import fr.flanjersi.cloudmqttapi.exception.RestClientException;

public class InstanceSectionAPI {

	private RestClient restClient;
	
	public InstanceSectionAPI(RestClient restClient) {
		this.restClient = restClient;
	}
	
	public void restart() throws CloudMQTTAPIException {
		
		try {
			restClient.post("instance/restart", "");
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException("Error throwed when attemp to restart the instance", e);
		}
		
	}
	
	
}
