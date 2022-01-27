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

public class CheckResults {

	/**
	 * This method query's the database to create a list of teams that exist.
	 * 
	 * @return an ArrayList of teams
	 * @throws Exception
	 */
	public static ArrayList<String> getTeams() throws Exception {
		
		ArrayList<String> teamList = new ArrayList<String>();
		
		try {
			// Get connection
			Connection con = ConnectionDB.getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT team FROM accounts WHERE type = 'Manager'");

			// Execute query
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				teamList.add(result.getString("team"));
			}
			return teamList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method checks the score inputed is valid: must be an Integer value.
	 * 
	 * @param homeScore
	 * @param awayScore
	 * @return a boolean to indicate whether the score is valid or not
	 */
	public static boolean checkScore(String homeScore, String awayScore) {
		if (homeScore.matches("[0-9]*") && awayScore.matches("[0-9]*")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<String> listResultV() throws Exception {
		ArrayList<String> listResult = new ArrayList<String>();

		Connection con = ConnectionDB.getConnection();
		PreparedStatement statement = con
				.prepareStatement("SELECT homeScore, homeTeam, awayScore, awayTeam FROM verify");

		ResultSet result = statement.executeQuery();

		while (result.next()) {

			listResult.add(result.getString("homeScore") + result.getString("homeTeam") + result.getString("awayScore")
					+ result.getString("awayTeam"));
		}
		return listResult;
	}

	public static ArrayList<String> listResult() throws Exception {
		ArrayList<String> listResult = new ArrayList<String>();

		Connection con = ConnectionDB.getConnection();
		PreparedStatement statement = con
				.prepareStatement("SELECT homeScore, homeTeam, awayScore, awayTeam FROM results");

		ResultSet result = statement.executeQuery();

		while (result.next()) {

			listResult.add(result.getString("homeScore") + result.getString("homeTeam") + result.getString("awayScore")
					+ result.getString("awayTeam"));
		}
		return listResult;
	}
}

