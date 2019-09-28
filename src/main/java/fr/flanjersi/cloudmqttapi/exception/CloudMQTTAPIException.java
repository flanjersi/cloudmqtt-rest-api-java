package fr.flanjersi.cloudmqttapi.exception;

/**
 * 
 * @author Flanjersi
 *
 */
public class CloudMQTTAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	public CloudMQTTAPIException(String message, Throwable cause) {
		super(message, cause);
	}

	public CloudMQTTAPIException(String message) {
		super(message);
	}

	public CloudMQTTAPIException(Throwable cause) {
		super(cause);
	}
	
}
