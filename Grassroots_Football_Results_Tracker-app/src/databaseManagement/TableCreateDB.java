package databaseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 
 * @author jw01543
 *
 */

public class TableCreateDB {

	/**
	 * This is the main method and executes the methods to create the required tables in the database.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		createAccountsTable();
		createResultsTable();
		createVerifyTable();
		createChatTable();
		createTable();
		mainAdmin();
	}

	/**
	 * Creates the accounts table.
	 * 
	 * @throws Exception
	 */
	public static void createAccountsTable() throws Exception {
		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS accounts(username varchar(45), password varchar(45), team varchar(45), type varchar(45), PRIMARY KEY(username))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Created accounts table");
		}
	}
	
	/**
	 * Inserts a super admin into accounts table
	 * 
	 * @throws Exception
	 */
	public static void mainAdmin() throws Exception {
		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement create = con.prepareStatement(
					"INSERT INTO accounts(username, password, team, type) VALUES('Jamie', 'Wiggins1', 'no team', 'Admin')");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Created admin account");
		}
	}

	/**
	 * Creates the results table.
	 * 
	 * @throws Exception
	 */
	public static void createResultsTable() throws Exception {
		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS results(resultID int AUTO_INCREMENT, gameWeek int, homePoints int, homeResult varchar(1), homeTeam varchar(45), homeScore int, awayScore int, awayTeam varchar(45), awayResult varchar(1), pointsAway int, PRIMARY KEY(resultID))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Created results table");
		}
	}
	
	/**
	 * Creates the verify table.
	 * 
	 * @throws Exception
	 */
	public static void createVerifyTable() throws Exception {
		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS verify(resultID int AUTO_INCREMENT, gameWeek int, homePoints int, homeResult varchar(1), homeTeam varchar(45), homeScore int, awayScore int, awayTeam varchar(45), awayResult varchar(1), pointsAway int, PRIMARY KEY(resultID))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Created results table");
		}
	}
	
	/**
	 * Creates the chat table.
	 * 
	 * @throws Exception
	 */
	public static void createChatTable() throws Exception {
		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS chat(chatID int AUTO_INCREMENT, adminMessage varchar(1000), managerMessage varchar(1000), sentBy varchar(45), recievedBy varchar(45), PRIMARY KEY(chatID))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Created chat table");
		}
	}

	/**
	 * Creates the league table table.
	 * 
	 * @throws Exception
	 */
	public static void createTable() throws Exception {
		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS league(TeamInfoID int AUTO_INCREMENT, Team varchar(45), P int,  W int, D int, L int, GS int, GC int, GD int, Points int, PRIMARY KEY(TeamInfoID))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Created league table");
		}
	}
}
