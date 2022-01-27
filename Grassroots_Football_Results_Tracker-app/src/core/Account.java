package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databaseManagement.ConnectionDB;

/**
 * 
 * @author jw01543
 *
 */

public class Account {

	/** The username of the account */
	private static String username = null;

	/** format for a username: must only include letters */
	private static final String usernameRegex = "[a-zA-Z]+";
	
	/** The password of the account */
	private static String password = null;
	
	/** format for a password: must only include at least one or more letters and numbers */
	private static final String passwordRegex = "[a-zA-Z]+[0-9]+";
	
	/** The type of an account */
	private static String type = null;

	/**
	 * Creates an account with username, password and type
	 * 
	 * @param username
	 * 			matches the REGEX format
	 * @param password
	 * 			matches the REGEX format
	 * @param type
	 */
	public Account(String username, String password, String type) {
		super();
		if (username.matches(usernameRegex) && password.matches(passwordRegex)) {
			Account.username = username;
			Account.password = password;
		} else {
			throw new IllegalArgumentException("Invalid username, password");
		}
		Account.type = type;
	}
	
	/**
	 * @return the account type
	 */
	public static String getType() {
		return Account.type;
	}

	/**
	 * @return the account password
	 */
	public static String getPassword() {
		return Account.password;
	}

	/**
	 * @return the account username
	 */
	public static String getUsername() {
		return Account.username;
	}

	/**
	 * This method query's the database to evaluate whether a username exists. 
	 * 
	 * @return a boolean to indicate whether an account exists or not in the database.
	 * @throws Exception
	 */
	public static boolean checkAccountExsits() throws Exception {
		
		// Connection to database
		Connection con = ConnectionDB.getConnection();
		
		PreparedStatement statement = con
				.prepareStatement("SELECT username FROM accounts WHERE username = '" + getUsername() + "'");

		// Execute query
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}
}
