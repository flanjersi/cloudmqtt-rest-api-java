package fr.flanjersi.cloudmqttapi.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.flanjersi.cloudmqttapi.client.RestClient;
import fr.flanjersi.cloudmqttapi.exception.CloudMQTTAPIException;
import fr.flanjersi.cloudmqttapi.exception.RestClientException;
import fr.flanjersi.cloudmqttapi.model.User;

public class UserSectionAPI {

	private RestClient restClient;
	
	public UserSectionAPI(RestClient restClient) {
		this.restClient = restClient;
	}
	
	
	public List<String> getUsersName() throws CloudMQTTAPIException{
		try {
			JsonNode usersJson = restClient.request("user");
			
			List<String> usersName = new ArrayList<String>();
			
			for(int indexUser = 0; indexUser < usersJson.size(); indexUser++) {
				usersName.add(usersJson.get(indexUser).get("username").asText());
			}
			
			return Collections.unmodifiableList(usersName);
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException("Error throwed when attempt to get users name", e);
		}
	}
	
	public User getUserInfo(String username) throws CloudMQTTAPIException {
		if(username == null) throw new CloudMQTTAPIException("The username cannot be null");
		if(username.isEmpty()) throw new CloudMQTTAPIException("The username cannot be empty");
		if(username.contains(" "))  throw new CloudMQTTAPIException("The username can't contains whitespace");
		
		try {
			JsonNode usersJson = restClient.request("user/" + username);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			return objectMapper.readValue(usersJson.toString(), User.class);
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException("Error throwed when attempt to get user details", e);
		} catch (IOException e) {
			throw new CloudMQTTAPIException("Error throwed when attempt to bind response to user object", e);
		}
	}
	
	public void createUser(String username, String password) throws CloudMQTTAPIException {
		if(username == null) throw new CloudMQTTAPIException("The username cannot be null");
		if(username.isEmpty()) throw new CloudMQTTAPIException("The username cannot be empty");
		if(username.contains(" "))  throw new CloudMQTTAPIException("The username can't contains whitespace");
		
		if(password == null) throw new CloudMQTTAPIException("The password cannot be null");
		if(password.isEmpty()) throw new CloudMQTTAPIException("The password cannot be empty");
		if(password.contains(" "))  throw new CloudMQTTAPIException("The password can't contains whitespace");
		
		
		try {
			ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
			
			objectNode.put("username", username);
			objectNode.put("password", password);
			
			restClient.post("user", objectNode.toString());
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException(String.format("Error throwed when attempt to create user : %s", username), e);
		}
	}
	
	public void updateUserPassword(String username, String password) throws CloudMQTTAPIException {
		if(username == null) throw new CloudMQTTAPIException("The username cannot be null");
		if(username.isEmpty()) throw new CloudMQTTAPIException("The username cannot be empty");
		if(username.contains(" "))  throw new CloudMQTTAPIException("The username can't contains whitespace");
		
		if(password == null) throw new CloudMQTTAPIException("The password cannot be null");
		if(password.isEmpty()) throw new CloudMQTTAPIException("The password cannot be empty");
		if(password.contains(" "))  throw new CloudMQTTAPIException("The password can't contains whitespace");
		
		try {
			ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
			
			objectNode.put("password", password);
			
			restClient.put("user/" + username, objectNode.toString());
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException(String.format("Error throwed when attempt to update user password: %s", username), e);
		}
	}
	
	public void removeUser(String username) throws CloudMQTTAPIException {
		if(username == null) throw new CloudMQTTAPIException("The username cannot be null");
		if(username.isEmpty()) throw new CloudMQTTAPIException("The username cannot be empty");
		if(username.contains(" "))  throw new CloudMQTTAPIException("The username can't contains whitespace");
		
		try {
			restClient.delete("user/" + username);
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException(String.format("Error throwed when attempt to delete user : %s", username), e);
		}
	}
}
