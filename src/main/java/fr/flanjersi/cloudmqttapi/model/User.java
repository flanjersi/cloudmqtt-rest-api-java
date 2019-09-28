package fr.flanjersi.cloudmqttapi.model;

import java.util.List;

public class User {

	private String username;
	
	private List<UserACL> acls;

	public User() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserACL> getAcls() {
		return acls;
	}

	public void setAcls(List<UserACL> acls) {
		this.acls = acls;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", acls=" + acls + "]";
	}	
	
	
	
}
