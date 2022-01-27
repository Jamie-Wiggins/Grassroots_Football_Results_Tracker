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

public class Manager extends Account {

	/** The manager's team */
	private static String team;

	/**
	 * Creates a manager with username, password team and type extended from Account
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @param team
	 */
	public Manager(String username, String password, String type, String team) {
		super(username, password, type);
			Manager.team = team;
	}

	/**
	 * @return the manager's team
	 */
	public static String getTeam() {
		return Manager.team;
	}

	/**
	 * This method query's the database to evaluate whether a manager account exists.
	 * 
	 * @return a boolean to indicate whether an account and password with type manager exists or not in the database.
	 * @throws Exception
	 */
	public static boolean checkManagerExsits() throws Exception {
		
		// Get connection
		Connection con = ConnectionDB.getConnection();
		
		PreparedStatement statement = con
				.prepareStatement("SELECT username FROM accounts WHERE username = '" + getUsername() + "' && password = '" + getPassword() + "' && type = 'Manager'");

		// Execute query
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method query's the database to create a list of manager accounts that exist.
	 * 
	 * @return an ArrayList of manager accounts
	 * @throws Exception
	 */
	public static ArrayList<String> getManager() throws Exception {
		
		ArrayList<String> managerList = new ArrayList<String>();
		
		try {
			
			// Get connection
			Connection con = ConnectionDB.getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT username FROM accounts WHERE type = 'Manager'");

			// Execute query
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				managerList.add(result.getString("username"));
			}
			return managerList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
