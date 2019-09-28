package fr.flanjersi.cloudmqttapi.model;

public class UserACL {

	private String topic;
	
	private boolean read;
	
	private boolean write;

	public UserACL() {}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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

	@Override
	public String toString() {
		return "UserACL [topic=" + topic + ", read=" + read + ", write=" + write + "]";
	}
	
	
	
}
