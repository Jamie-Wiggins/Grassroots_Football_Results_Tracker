
package core;

/**
 * 
 * @author jw01543
 *
 */

public class CheckMessage {

	/**
	 * This method checks to see if a message contains any offensive language.
	 * 
	 * @param message
	 * @return a boolean to indicate whether a message is valid or not.
	 */
	public static boolean validMessage(String message) {
		if (message.matches("^[a-zA-Z0-9_ ]*$")) {
			return true;
		}
		return false;
	}
}
