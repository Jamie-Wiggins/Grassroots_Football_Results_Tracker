package databaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 
 * @author jw01543
 *
 */

public class ConnectionDB {

	/**
	 * This is the main method and executes the getConnection() method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		getConnection();
	}

	/**
	 * This method creates a connection to the database and also creates a db schema.
	 * 
	 * @return a connection to the database
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		try {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
			String username = "jw01543";
			String password = "1234";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			
			// Creates the database db if it does not already exists
			PreparedStatement create = conn.prepareStatement("CREATE SCHEMA IF NOT EXISTS db");
			
			create.executeUpdate();

			String newUrl = "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
			Connection newCon = DriverManager.getConnection(newUrl, username, password);
			
			return newCon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}