package jFrames;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import core.TableData;
import databaseManagement.ConnectionDB;
import java.awt.Color;

/**
 * 
 * @author jw01543
 *
 */

@SuppressWarnings("serial")
public class AdminMenu extends JFrame {

	/** The content pane of the JPanel */
	private JPanel contentPane;
	
	/** The chat button */
	private JButton btnChat;
	
	/** The create account button */
	private JButton btnCreateAccount;
	
	/** The back button */
	private JButton btnBack;
	
	/** The update button */
	private JButton btnUpdate;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		btnChat = new JButton("Chat");
		btnChat.setForeground(new Color(255, 255, 255));
		btnChat.setBackground(new Color(102, 102, 102));
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AdminChat frmAdminChat = null;
				try {
					frmAdminChat = new AdminChat();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frmAdminChat.setVisible(true);
			}
		});
		btnChat.setBounds(143, 60, 138, 43);
		getContentPane().add(btnChat);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setForeground(new Color(255, 255, 255));
		btnCreateAccount.setBackground(new Color(102, 102, 102));
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				CreateAccount frmCreate = new CreateAccount();
				frmCreate.setVisible(true);
			}
			
		});
		btnCreateAccount.setBounds(143, 127, 138, 43);
		getContentPane().add(btnCreateAccount);
		
		btnUpdate = new JButton("Upload League to database");
		btnUpdate.setBackground(new Color(102, 204, 51));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = ConnectionDB.getConnection();
					
					PreparedStatement posted = con.prepareStatement(
							"INSERT INTO league(Team, P, W, D, L, GS, GC, GD, Points) VALUES(?,?,?,?,?,?,?,?,?)");

					ArrayList<String> list = TableData.listTeams();
					
					// Iterates through all the teams
					for (int i = 0; i < list.size(); i++) {

						String teams = (String) list.get(i).toString();
						int p = (int) TableData.getGamesPlayed(list.get(i));
						int w = (int) TableData.getGamesWon(list.get(i));
						int d = (int) TableData.getGamesDrawn(list.get(i));
						int l = (int) TableData.getGamesLost(list.get(i));
						int gs = (int) TableData.getGoalsScored(list.get(i));
						int gc = (int) TableData.getGoalsConceded(list.get(i));
						int gd = (int) TableData.getGoalDifference(list.get(i));
						int pts = (int) TableData.getPoints(list.get(i));

						posted.setString(1, teams);
						posted.setInt(2, p);
						posted.setInt(3, w);
						posted.setInt(4, d);
						posted.setInt(5, l);
						posted.setInt(6, gs);
						posted.setInt(7, gc);
						posted.setInt(8, gd);
						posted.setInt(9, pts);

						// Updates a batch to the database
						posted.addBatch();
					}
					// Executes the batch
					posted.executeBatch();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnUpdate.setBounds(143, 215, 243, 35);
		contentPane.add(btnUpdate);
		
		btnBack = new JButton("Log Out");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(204, 51, 51));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				AccountLogin frmLogin = new AccountLogin();
				frmLogin.setVisible(true);
			}
		});
		btnBack.setBounds(35, 215, 98, 35);
		contentPane.add(btnBack);
	}
}
