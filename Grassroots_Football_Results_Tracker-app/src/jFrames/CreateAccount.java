package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.Admin;
import core.Manager;
import databaseManagement.ConnectionDB;
import java.awt.Color;

/**
 * 
 * @author jw01543
 *
 */

@SuppressWarnings("serial")
public class CreateAccount extends JFrame {

	/** The content pane of the JPanel */
	private JPanel contentPane;
	
	/** empty username JTextField */
	private JTextField txtUsername;
	
	/** empty password JTextField */
	private JTextField txtPassword;
	
	/** empty team JTextField */
	private JTextField txtTeam;
	
	/** label for type */
	private JLabel lblType;
	
	/** Radio button admin */
	private JRadioButton rdbtnAdmin;
	
	/** Radio button manager */
	private JRadioButton rdbtnManager;
	
	@SuppressWarnings("unused")
	private Admin admin = null;
	
	@SuppressWarnings("unused")
	private Manager manager = null;
	
	/** label for username */
	private JLabel lblUsername;
	
	/** label for team */
	private JLabel lblTeam;
	private JButton btnCreate;
	
	/** label for password */
	private JLabel lblPassword;
	
	/** button group */
	private ButtonGroup bg;
	
	/** The back button */
	private JButton btnBack;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreateAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(83, 56, 95, 14);
		getContentPane().add(lblUsername);

		lblTeam = new JLabel("Team:");
		lblTeam.setBounds(83, 125, 73, 14);
		getContentPane().add(lblTeam);

		txtUsername = new JTextField();
		txtUsername.setBounds(177, 53, 162, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(177, 85, 162, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		txtTeam = new JTextField();
		txtTeam.setBounds(177, 122, 162, 20);
		getContentPane().add(txtTeam);
		txtTeam.setColumns(10);

		btnCreate = new JButton("Create");
		btnCreate.setBackground(new Color(102, 204, 51));
		btnCreate.setBounds(177, 203, 87, 47);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String type = "";
				
				// new admin object
				admin = new Admin(txtUsername.getText().toString(), txtPassword.getText().toString(), type);
				
				// new manager object
				manager = new Manager(txtUsername.getText().toString(), txtPassword.getText().toString(), type,
						txtTeam.getText().toString());
				
				if (rdbtnAdmin.isSelected()) {
					type = "Admin";
				} else if (rdbtnManager.isSelected()) {
					type = "Manager";
				} else {
					type = null;
				}

				try {
					
					// Checks if the admin exists in the database, admin radio button is checked and the team text field is empty
					if (!Admin.checkAdminExsits() && rdbtnAdmin.isSelected() && txtTeam.getText().isEmpty()) {
						try {
							Connection con = ConnectionDB.getConnection();
							PreparedStatement posted = con
									.prepareStatement("INSERT INTO accounts(username, password, team, type) VALUES('"
											+ Admin.getUsername() + "','" + Admin.getPassword() + "', 'No team', '"
											+ type + "')");
							posted.executeUpdate();
							System.out.print("New Admin account created");

						} catch (Exception exp) {
							System.out.println(exp);
						}
						
					// Checks if the manager exists in the database, manager radio button is checked and the team text field is not empty
					} else if (!Manager.checkAccountExsits() && rdbtnManager.isSelected() && !txtTeam.getText().isEmpty()) {
						try {
							Connection con = ConnectionDB.getConnection();
							PreparedStatement posted = con
									.prepareStatement("INSERT INTO accounts(username, password, team, type) VALUES('"
											+ Manager.getUsername() + "','" + Manager.getPassword() + "', '"
											+ Manager.getTeam() + "', '" + type + "')");
							posted.executeUpdate();
							System.out.println("New Manager account created");

						} catch (Exception exp) {
							System.out.println(exp);
						}
					} else {
						
						// If input is invalid it will reset the text fields to null
						
						txtPassword.setText(null);
						txtTeam.setText(null);
						txtUsername.setText(null);
						System.out.println("Invalid Input");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(83, 88, 95, 14);
		getContentPane().add(lblPassword);
		getContentPane().add(btnCreate);

		lblType = new JLabel("Type:");
		lblType.setBounds(83, 154, 71, 14);
		getContentPane().add(lblType);

		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBackground(new Color(204, 204, 204));
		rdbtnManager.setBounds(177, 149, 87, 23);
		getContentPane().add(rdbtnManager);

		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBackground(new Color(204, 204, 204));
		rdbtnAdmin.setBounds(276, 149, 95, 23);
		getContentPane().add(rdbtnAdmin);

		bg = new ButtonGroup();
		bg.add(rdbtnManager);
		bg.add(rdbtnAdmin);

		btnBack = new JButton("<");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(102, 102, 102));
		btnBack.setBounds(10, 215, 41, 35);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				AdminMenu frmAdminMenu = new AdminMenu();
				frmAdminMenu.setVisible(true);
			}
		});
		contentPane.add(btnBack);
	}

}
