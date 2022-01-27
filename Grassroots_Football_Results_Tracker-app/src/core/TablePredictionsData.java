package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import databaseManagement.ConnectionDB;

public class TablePredictionsData {

	/** The predicted amount of goals conceded */
	private static int predictedGoalsConceded = 0;
	
	/** The predicted amount of goals scored */
	private static int predictedGoalsSocred = 0;
	
	/** The predicted goal difference */
	private static int predictedGoalDifference = 0;
	
	/** The predicted amount of games won */
	private static int predictedGamesWon = 0;
	
	/** The predicted amount of games drawn */
	private static int predictedGamesDrawn = 0;
	
	/** The predicted amount of games lost */
	private static int predictedGamesLost = 0;
	
	/** The predicted amount of total points */
	private static int predictedPoints = 0;
	
	/** Total number of games in one season */
	private static int numberSeasonGames = 38;

	/**
	 * Predicts the number of goals conceded
	 * 
	 * @param team
	 * @return the number of conceded goals
	 */
	public static int getPredictedGoalsConceded(String team) {
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
		
		if(goalsConcededTotal <= 0) {
			predictedGoalsConceded = 0;
		} else {
			predictedGoalsConceded = ((goalsConcededTotal/TableData.getGamesPlayed(team))*numberSeasonGames);
		}
		 
		return predictedGoalsConceded;
	}

	/**
	 * Predicts the number of goals scored
	 * 
	 * @param team
	 * @return number of goals scored
	 */
	public static int getPredictedGoalsScored(String team) {
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
		
		if (goalsScoredTotal <= 0) {
			predictedGoalsSocred = 0;
		}else {
			predictedGoalsSocred = ((goalsScoredTotal/TableData.getGamesPlayed(team))*numberSeasonGames);
		}
		return predictedGoalsSocred;
	}

	public static int getPredicitedGoalDifference(String team) {
		predictedGoalDifference = getPredictedGoalsScored(team) - getPredictedGoalsConceded(team);

		return predictedGoalDifference;
	}

	/**
	 * Predicted games won
	 * 
	 * @param team
	 * @return number of games won
	 */
	public static int getPredicitedGamesWon(String team) {
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
		
		if (array.size() <= 0) {
			predictedGamesWon = 0;
		}else {
		predictedGamesWon = ((array.size())/(TableData.getGamesPlayed(team))*numberSeasonGames);
		}
		return predictedGamesWon;
	}

	/**
	 * Predicted games lost
	 * 
	 * @param team
	 * @return number of games lost
	 */
	public static int getPredicitedGamesLost(String team) {
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

		if (array.size() <= 0) {
			predictedGamesLost = 0;
		}else {
		predictedGamesLost = (array.size()/(TableData.getGamesPlayed(team))*numberSeasonGames);
		}
		return predictedGamesLost;
	}

	/**
	 * Predicted number of games drawn
	 * 
	 * @param team
	 * @return number of games drawn
	 */
	public static int getPredicitedGamesDrawn(String team) {
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
		if (array.size() <= 0) {
			predictedGamesDrawn = 0;
		}else {
		predictedGamesDrawn = (array.size()/(TableData.getGamesPlayed(team))*numberSeasonGames);
		}
		return predictedGamesDrawn;
	}

	/**
	 * Predicted points total
	 * 
	 * @param team
	 * @return number total points
	 */
	public static int getPredictedPoints(String team) {
		int currentPoints = 0;
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
		currentPoints = ((homeW.size() * 3) + (awayW.size() * 3) + (homeD.size()) + (awayD.size()));
		
		if (currentPoints <= 0) {
			predictedPoints = 0;
		}else {
		predictedPoints = (currentPoints/(TableData.getGamesPlayed(team)*3))*numberSeasonGames;
		}
		return predictedPoints;
	}

	/**
	 * 
	 * @return number of games in a season
	 */
	public static int getNumberSeasonGames() {
		return numberSeasonGames;
	}

}
