package fr.flanjersi.cloudmqttapi.model;

public class ACL {

	private int id;
	
	private String type;
	private String pattern;
	
	private boolean read;
	private boolean write;
	
	private String user;

	public ACL() { }
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ACL [id=" + id + ", type=" + type + ", pattern=" + pattern + ", read=" + read + ", write=" + write
				+ ", user=" + user + "]";
	}

	
	
	
	
}
