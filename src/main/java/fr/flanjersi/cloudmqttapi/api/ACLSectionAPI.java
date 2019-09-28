package fr.flanjersi.cloudmqttapi.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.flanjersi.cloudmqttapi.client.RestClient;
import fr.flanjersi.cloudmqttapi.exception.CloudMQTTAPIException;
import fr.flanjersi.cloudmqttapi.exception.RestClientException;
import fr.flanjersi.cloudmqttapi.model.ACL;
import fr.flanjersi.cloudmqttapi.model.User;

public class ACLSectionAPI {

	private RestClient restClient;

	public ACLSectionAPI(RestClient restClient) {
		this.restClient = restClient;
	}

	public List<ACL> getACLRules() throws CloudMQTTAPIException{
		
		try {
			JsonNode aclsJSON = restClient.request("acl");
			JsonNode aclsJSONArray = aclsJSON.get("items");
			
			List<ACL> acls = new ArrayList<ACL>();
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			for(int indexACL = 0; indexACL < aclsJSONArray.size(); indexACL++) {
				ACL acl = objectMapper.readValue(aclsJSONArray.get(indexACL).toString(), ACL.class);
				
				acls.add(acl);
			}
			
			return Collections.unmodifiableList(acls);
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException("Error throwed when attempt to get acl rules", e);
		} catch (IOException e) {
			throw new CloudMQTTAPIException("Error throwed when attempt to bind json data to acl object", e);
		}
		
	}
	
	public void createTopicACL(String username, String topic, boolean read, boolean write) throws CloudMQTTAPIException {
		if(username == null) throw new CloudMQTTAPIException("The username cannot be null");
		if(username.isEmpty()) throw new CloudMQTTAPIException("The username cannot be empty");
		if(username.contains(" "))  throw new CloudMQTTAPIException("The username can't contains whitespace");
		
		if(topic == null) throw new CloudMQTTAPIException("The topic cannot be null");
		if(topic.isEmpty()) throw new CloudMQTTAPIException("The topic cannot be empty");
		if(topic.contains(" "))  throw new CloudMQTTAPIException("The topic can't contains whitespace");
		
		createACL("topic", username, topic, read, write);
	}
	
	public void createPatternACL(String pattern, boolean read, boolean write) throws CloudMQTTAPIException {
		if(pattern == null) throw new CloudMQTTAPIException("The pattern cannot be null");
		if(pattern.isEmpty()) throw new CloudMQTTAPIException("The pattern cannot be empty");
		if(pattern.contains(" "))  throw new CloudMQTTAPIException("The pattern can't contains whitespace");
		
		createACL("pattern", null, pattern, read, write);
	}
	
	private void createACL(String type, String username, String topicOrPattern, boolean read, boolean write) throws CloudMQTTAPIException {
		try {
			ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
			
			objectNode.put("type", type);
			objectNode.put("username", username);
			objectNode.put("pattern", topicOrPattern);
			objectNode.put("read", read);
			objectNode.put("write", write);
			
			restClient.post("acl", objectNode.toString());	
			
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException("Error throwed when attempt to create acl rule", e);
		}
	}
	
	public void removeACLRule(String username, String topic) throws CloudMQTTAPIException {
		if(username != null && username.isEmpty()) throw new CloudMQTTAPIException("The username cannot be empty");
		if(username != null && username.contains(" "))  throw new CloudMQTTAPIException("The username can't contains whitespace");
		
		try {
			ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
			
			objectNode.put("username", username);
			objectNode.put("topic", topic);
			
			restClient.delete("acl", objectNode.toString());
		} catch (RestClientException e) {
			throw new CloudMQTTAPIException(String.format("Error throwed when attempt to delete user : %s", username), e);
		}
	}
	
}
