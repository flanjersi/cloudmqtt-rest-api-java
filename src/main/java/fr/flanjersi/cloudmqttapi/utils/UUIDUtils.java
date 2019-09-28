package fr.flanjersi.cloudmqttapi.utils;

import java.util.UUID;

public class UUIDUtils {

	public static boolean isUUID(String uuidString) {
	    try {
	        UUID.fromString(uuidString);
	        return true;
	    } catch (Exception ex) {
	        return false;
	    }
	}
	
}
