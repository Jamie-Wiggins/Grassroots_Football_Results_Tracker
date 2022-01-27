package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.CheckResults;
import databaseManagement.ConnectionDB;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class SubmitResults extends JFrame {

	/** The content pane of the JPanel */
	private JPanel contentPane;
	
	/** The homescore text field */
	private JTextField txtHomeScore;
	
	/** The awayscore text field */
	private JTextField txtAwayScore;
	
	/** The game week text field */
	private JTextField txtGameWeek;
	
	/** The home team selection box */
	private JComboBox<String> homeTeamSelection;
	
	/** The away team selection box */
	private JComboBox<String> awayTeamSelection;
	
	/** The home score label */
	private JLabel homeScore;
	
	/** The away score label */
	private JLabel awayScore;
	
	/** The vs label */
	private JLabel vs;
	
	/** The submit button */
	private JButton btnSubmit;
	
	/** The back button */
	private JButton btnBack;
	
	/** The game week label */
	private JLabel lblGameWeek;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmitResults frame = new SubmitResults();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SubmitResults() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		homeScore = new JLabel("Home Score");
		homeScore.setHorizontalAlignment(SwingConstants.CENTER);
		homeScore.setBounds(59, 130, 110, 26);
		contentPane.add(homeScore);

		awayScore = new JLabel("Away Score");
		awayScore.setHorizontalAlignment(SwingConstants.CENTER);
		awayScore.setBounds(273, 130, 100, 26);
		contentPane.add(awayScore);

		vs = new JLabel("VS");
		vs.setHorizontalAlignment(SwingConstants.CENTER);
		vs.setBounds(204, 99, 25, 26);
		contentPane.add(vs);

		txtHomeScore = new JTextField();
		txtHomeScore.setBounds(83, 96, 61, 32);
		contentPane.add(txtHomeScore);
		txtHomeScore.setColumns(10);

		txtAwayScore = new JTextField();
		txtAwayScore.setColumns(10);
		txtAwayScore.setBounds(293, 96, 61, 32);
		contentPane.add(txtAwayScore);

		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(0, 0, 0));
		btnSubmit.setBackground(new Color(102, 204, 51));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String homeScore = txtHomeScore.getText();
				String awayScore = txtAwayScore.getText();
				String homeTeam = homeTeamSelection.getSelectedItem().toString();
				String awayTeam = awayTeamSelection.getSelectedItem().toString();
				String gameWeek = txtGameWeek.getText();
				int homePoints = 0;
				int awayPoints = 0;
				String homeResult = "";
				String awayResult = "";

				int homeScoreNum = Integer.parseInt(homeScore);
				int awayScoreNum = Integer.parseInt(awayScore);

				if (homeScoreNum > awayScoreNum) {
					homePoints = 3;
					awayPoints = 0;
					homeResult = "W";
					awayResult = "L";
				} else if (homeScoreNum < awayScoreNum) {
					homePoints = 0;
					awayPoints = 3;
					homeResult = "L";
					awayResult = "W";
				} else if (homeScoreNum == awayScoreNum) {
					homePoints = 1;
					awayPoints = 1;
					homeResult = "D";
					awayResult = "D";
				}
				ArrayList<String> compareResult = new ArrayList<String>();
				compareResult.add(homeScore + homeTeam + awayScore + awayTeam);

				boolean check = false;
				try {
					for (String resultCheck : CheckResults.listResultV()) {
						if (resultCheck.contains(homeScore + homeTeam + awayScore + awayTeam)) {
							check = true;
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				boolean checkResults = true;
				try {
					for (String resultCheck2 : CheckResults.listResult()) {
						if (resultCheck2.contains(homeScore + homeTeam + awayScore + awayTeam)) {
							checkResults = false;
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (check && checkResults && CheckResults.checkScore(homeScore, awayScore)) {
					try {
						Connection con = ConnectionDB.getConnection();
						PreparedStatement posted = con.prepareStatement(
								"INSERT INTO results(gameWeek, homePoints, homeResult, homeTeam, homeScore, awayScore, awayTeam, awayResult, pointsAway) VALUES('"
										+ gameWeek + "','" + homePoints + "', '" + homeResult + "', '" + homeTeam
										+ "','" + homeScore + "', '" + awayScore + "', '" + awayTeam + "', '"
										+ awayResult + "', '" + awayPoints + "')");

						posted.executeUpdate();
					} catch (Exception e2) {

					}

				} else if (!check && CheckResults.checkScore(homeScore, awayScore)) {
					try {
						Connection con = ConnectionDB.getConnection();
						PreparedStatement posted = con.prepareStatement(
								"INSERT INTO verify(gameWeek, homePoints, homeResult, homeTeam, homeScore, awayScore, awayTeam, awayResult, pointsAway) VALUES('"
										+ gameWeek + "','" + homePoints + "', '" + homeResult + "', '" + homeTeam
										+ "','" + homeScore + "', '" + awayScore + "', '" + awayTeam + "', '"
										+ awayResult + "', '" + awayPoints + "')");

						posted.executeUpdate();
					} catch (Exception e2) {

					}

				}
			}
		});
		btnSubmit.setBounds(144, 215, 141, 35);

		getContentPane().add(btnSubmit);

		homeTeamSelection = new JComboBox<String>();
		homeTeamSelection.setBackground(new Color(102, 102, 102));
		homeTeamSelection.setForeground(new Color(255, 255, 255));
		try {
			for (String team : CheckResults.getTeams()) {
				homeTeamSelection.addItem(team);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		homeTeamSelection.setSelectedIndex(0);
		homeTeamSelection.setBounds(48, 43, 133, 32);
		getContentPane().add(homeTeamSelection);

		awayTeamSelection = new JComboBox<String>();
		awayTeamSelection.setForeground(new Color(255, 255, 255));
		awayTeamSelection.setBackground(new Color(102, 102, 102));
		try {
			for (String team : CheckResults.getTeams()) {
				awayTeamSelection.addItem(team);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		awayTeamSelection.setSelectedIndex(0);
		awayTeamSelection.setBounds(257, 43, 133, 32);
		getContentPane().add(awayTeamSelection);

		btnBack = new JButton("<");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(102, 102, 102));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				ManagerMenu frmManagerMenu = new ManagerMenu();
				frmManagerMenu.setVisible(true);
			}
		});
		btnBack.setBounds(10, 215, 41, 35);
		contentPane.add(btnBack);

		txtGameWeek = new JTextField();
		txtGameWeek.setBounds(221, 173, 86, 20);
		contentPane.add(txtGameWeek);
		txtGameWeek.setColumns(10);

		lblGameWeek = new JLabel("Game Week");
		lblGameWeek.setBounds(130, 176, 86, 14);
		contentPane.add(lblGameWeek);
	}
}
