package fr.flanjersi.cloudmqttapi.exception;

public class RestClientException extends Exception{

	private static final long serialVersionUID = 1L;

	public RestClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestClientException(String message) {
		super(message);
	}

	public RestClientException(Throwable cause) {
		super(cause);
	}

}
