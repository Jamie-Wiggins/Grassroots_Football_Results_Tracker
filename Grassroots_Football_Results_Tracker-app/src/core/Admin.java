package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import databaseManagement.ConnectionDB;

/**
 * 
 * @author jw01543
 *
 */

public class Admin extends Account {

	/**
	 * Creates an admin with username, password and type extended from Account
	 * 
	 * @param username
	 * 			username of the account
	 * @param password
	 * 			password of the account
	 * @param type
	 * 			type of the account
	 */
	public Admin(String username, String password, String type) {
		super(username, password, type);
	}

	/**
	 * This method query's the database to evaluate whether an admin account exists. 
	 * 
	 * @return a boolean to indicate whether an account and password with type admin exists or not in the database.
	 * @throws Exception
	 */
	public static boolean checkAdminExsits() throws Exception {
		
		// Connection to database
		Connection con = ConnectionDB.getConnection();
		
		PreparedStatement statement = con.prepareStatement(
				"SELECT username FROM accounts WHERE username = '" + getUsername() + "' && password = '"+ getPassword() +"' && type = 'Admin'");

		// Executes query
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method query's the database to create a list of admin accounts that exist.
	 * 
	 * @return an ArrayList of admin accounts
	 * @throws Exception
	 */
	public static ArrayList<String> getAdmin() throws Exception {
		
		ArrayList<String> adminList = new ArrayList<String>();
		
		try {
			// Connection to database
			Connection con = ConnectionDB.getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT username FROM accounts WHERE type = 'Admin'");

			// Executes query
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				adminList.add(result.getString("username"));
			}
			return adminList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
