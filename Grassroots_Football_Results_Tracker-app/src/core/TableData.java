package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import databaseManagement.ConnectionDB;

public class TableData {

	/** The amount of goals conceded */
	private static int finalGoalsConceded = 0;
	
	/** The amount of goals scored */
	private static int finalGoalsSocred = 0;
	
	/** The goal difference */
	private static int finalGoalDifference = 0;
	
	/** The amount of games won */
	private static int finalGamesWon = 0;
	
	/** The amount of games drawn */
	private static int finalGamesDrawn = 0;
	
	/** The amount of games lost */
	private static int finalGamesLost = 0;
	
	/** The total points */
	private static int finalPoints = 0;
	
	/** The amount of games played */
	private static int finalGamesPLayed = 0;

	/**
	 * A list of all the teams
	 * 
	 * @return an ArrayList of the teams in the league
	 * @throws Exception
	 */
	public static ArrayList<String> listTeams() throws Exception {
		ArrayList<String> listTeams = new ArrayList<String>();
		Connection con = ConnectionDB.getConnection();
		PreparedStatement statement = con.prepareStatement("SELECT team FROM accounts WHERE type = 'Manager'");

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			listTeams.add(result.getString("team"));
		}
		return listTeams;
	}

	/**
	 * Goals conceded
	 * 
	 * @param team
	 * @return number goals conceded
	 */
	public static int getGoalsConceded(String team) {
		ArrayList<String> array = new ArrayList<String>();

		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement statement = con
					.prepareStatement("SELECT awayScore FROM results WHERE homeTeam = '" + team + "'");

			ResultSet r = statement.executeQuery();

			PreparedStatement statement2 = con
					.prepareStatement("SELECT homeScore FROM results WHERE awayTeam = '" + team + "'");

			ResultSet r2 = statement2.executeQuery();

			while (r.next()) {
				array.add(r.getString("awayScore"));
			}

			while (r2.next()) {
				array.add(r2.getString("homeScore"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		ArrayList<Integer> result2 = new ArrayList<Integer>();
		for (String value : array) {
			result2.add(Integer.parseInt(value));
		}

		int goalsConcededTotal = 0;
		for (int i = 0; i < result2.size(); i++) {
			goalsConcededTotal += result2.get(i);
		}		
		finalGoalsConceded = goalsConcededTotal;
		 return finalGoalsConceded;
	}

	/**
	 * Goals scored
	 * 
	 * @param team
	 * @return number of goals scored
	 */
	public static int getGoalsScored(String team) {
		ArrayList<String> array = new ArrayList<String>();

		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement statement = con
					.prepareStatement("SELECT homeScore FROM results WHERE homeTeam = '" + team + "'");

			ResultSet r = statement.executeQuery();

			PreparedStatement statement2 = con
					.prepareStatement("SELECT awayScore FROM results WHERE awayTeam = '" + team + "'");

			ResultSet r2 = statement2.executeQuery();

			while (r.next()) {
				array.add(r.getString("homeScore"));
			}

			while (r2.next()) {
				array.add(r2.getString("awayScore"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (String value : array) {
			result.add(Integer.parseInt(value));
		}
		int goalsScoredTotal = 0;
		for (int i = 0; i < result.size(); i++) {
			goalsScoredTotal += result.get(i);
		}
		
		finalGoalsSocred = goalsScoredTotal;
		return finalGoalsSocred;
	}

	/**
	 * Goal difference
	 * 
	 * @param team
	 * @return goal difference as type in
	 */
	public static int getGoalDifference(String team) {
		int goalDifference = getGoalsScored(team) - getGoalsConceded(team);

		String goalDifferenceFinal = Integer.toString(goalDifference);

		System.out.println("goal difference: " + goalDifferenceFinal);
		finalGoalDifference = Integer.parseInt(goalDifferenceFinal);
		
		return finalGoalDifference;
	}

	/**
	 * Games won
	 * 
	 * @param team
	 * @return number of games won
	 */
	public static int getGamesWon(String team) {
		ArrayList<String> array = new ArrayList<String>();

		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT homeResult FROM results WHERE homeTeam = '" + team + "' && homeResult = 'W'");

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				array.add("homeResult");
			}

			PreparedStatement statement2 = con.prepareStatement(
					"SELECT awayResult FROM results WHERE awayTeam = '" + team + "' && awayResult = 'W'");

			ResultSet r2 = statement2.executeQuery();

			while (r2.next()) {
				array.add("awayResult");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("total games won: " + array.size());
		finalGamesWon = array.size();
		return finalGamesWon;
	}

	/**
	 * Games lost
	 * 
	 * @param team
	 * @return number of games lost
	 */
	public static int getGamesLost(String team) {
		ArrayList<String> array = new ArrayList<String>();

		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT awayResult FROM results WHERE homeTeam = '" + team + "' && homeResult = 'L'");

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				array.add("homeResult");
			}
			PreparedStatement statement2 = con.prepareStatement(
					"SELECT awayResult FROM results WHERE awayTeam = '" + team + "' && awayResult = 'L'");

			ResultSet r2 = statement2.executeQuery();

			while (r2.next()) {
				array.add("awayResult");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("total games lost: " + array.size());
		finalGamesLost = array.size();
		return finalGamesLost;
	}

	/**
	 * Games drawn
	 * 
	 * @param team
	 * @return number of games drawn
	 */
	public static int getGamesDrawn(String team) {
		ArrayList<String> array = new ArrayList<String>();

		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT awayResult FROM results WHERE homeTeam = '" + team + "' && homeResult = 'D'");

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				array.add("homeResult");
			}
			PreparedStatement statement2 = con.prepareStatement(
					"SELECT awayResult FROM results WHERE awayTeam = '" + team + "' && awayResult = 'D'");

			ResultSet r2 = statement2.executeQuery();

			while (r2.next()) {
				array.add("awayResult");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("total games drawn: " + array.size());
		finalGamesDrawn = array.size();
		return finalGamesDrawn;
	}

	public static int getPoints(String team) {
		ArrayList<String> homeW = new ArrayList<String>();
		ArrayList<String> homeD = new ArrayList<String>();
		ArrayList<String> awayW = new ArrayList<String>();
		ArrayList<String> awayD = new ArrayList<String>();

		try {
			Connection con = ConnectionDB.getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT homePoints FROM results WHERE homeTeam = '" + team + "' && homePoints = '3'");

			ResultSet r = statement.executeQuery();

			while (r.next()) {
				homeW.add("homePoints");
			}

			PreparedStatement statement2 = con.prepareStatement(
					"SELECT homePoints FROM results WHERE homeTeam = '" + team + "' && homePoints = '1'");

			ResultSet r2 = statement2.executeQuery();

			while (r2.next()) {
				homeD.add("homePoints");
			}
			PreparedStatement statement3 = con.prepareStatement(
					"SELECT pointsAway FROM results WHERE awayTeam = '" + team + "' && pointsAway = '3'");

			ResultSet r3 = statement3.executeQuery();

			while (r3.next()) {
				awayW.add("pointsAway");
			}

			PreparedStatement statement4 = con.prepareStatement(
					"SELECT pointsAway FROM results WHERE awayTeam = '" + team + "' && pointsAway = '1'");

			ResultSet r4 = statement4.executeQuery();

			while (r4.next()) {
				awayD.add("pointsAway");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		finalPoints = ((homeW.size() * 3) + (awayW.size() * 3) + (homeD.size()) + (awayD.size()));
		
		return finalPoints;
	}

	/**
	 * Games played
	 * 
	 * @param team
	 * @return number of games played
	 */
	public static int getGamesPlayed(String team) {
		finalGamesPLayed = getGamesWon(team) + getGamesDrawn(team)+ getGamesLost(team);
		return finalGamesPLayed;
	}
}
