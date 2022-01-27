package jFrames;


import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.Admin;
import core.Manager;
import java.awt.Color;

@SuppressWarnings("serial")
public class AccountLogin extends JFrame {

	/**The content pane */
	private JPanel contentPane;
	
	/**The username text field */
	private static JTextField txtUsername;
	
	/**The password text field */
	private JPasswordField txtPassword;
	
	/**The admin radio button */
	private JRadioButton rdbtnAdmin;
	
	/**The manager radio button */
	private JRadioButton rdbtnManager;
	
	/**The username label */
	private JLabel lblUsername;

	/**The password label */
	private JLabel lblPassword;
	
	/**The login button */
	private JButton btnLogin;
	
	/**The exit button */
	private JButton btnExit;
	
	/**The reset button */
	private JButton btnReset;
	
	/**The separator */
	private JSeparator separator;
	
	/** The account type label */
	private JLabel lblAccountType;
	
	/**The button group */
	private ButtonGroup bg;
	
	/**The back button */
	private JButton btnBack;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountLogin frame = new AccountLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AccountLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(70, 67, 84, 24);
		getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 98, 84, 24);
		getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(154, 69, 190, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(154, 100, 190, 20);
		getContentPane().add(txtPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(102, 204, 51));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent args1) {
				
				
				String type = "";
				new Admin(txtUsername.getText().toString(), txtPassword.getText().toString(), type);
				new Manager(txtUsername.getText().toString(), txtPassword.getText().toString(), Manager.getTeam(), type);
				
				
				if(rdbtnAdmin.isSelected()) {
					type = "Admin";
				}else if(rdbtnManager.isSelected()) {
					type = "Manager";
				}else {
					type = "incorrect type";
				}
				
				try {
					
					if (rdbtnAdmin.isSelected() && Admin.checkAdminExsits()) {
						txtPassword.setText(null);
						txtUsername.setText(null);
						
						dispose();
						AdminMenu frmAdminMenu = new AdminMenu();
						frmAdminMenu.setVisible(true);
					}
					else if(rdbtnManager.isSelected() && Manager.checkManagerExsits()) {
						txtPassword.setText(null);
						txtUsername.setText(null);
									
						dispose();
						ManagerMenu frmManagerMenu = new ManagerMenu();
						frmManagerMenu.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Details", "Login Error", JOptionPane.ERROR_MESSAGE);
							txtPassword.setText(null);
							txtUsername.setText(null);
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(100, 215, 76, 35);
		getContentPane().add(btnLogin);
		
		btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(255, 51, 51));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args1) {
				
				JFrame exit = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(exit, "Confirm if you want to exit", "Login Systems", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(285, 215, 87, 35);
		getContentPane().add(btnExit);
		
		btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(102, 102, 102));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(186, 215, 86, 35);
		getContentPane().add(btnReset);
		
		separator = new JSeparator();
		separator.setBounds(420, 15, 0, 2);
		getContentPane().add(separator);
		
		lblAccountType = new JLabel("Account Type");
		lblAccountType.setBounds(70, 131, 84, 24);
		getContentPane().add(lblAccountType);
		
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBackground(new Color(204, 204, 204));
		rdbtnManager.setBounds(179, 127, 93, 33);
		getContentPane().add(rdbtnManager);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBackground(new Color(204, 204, 204));
		rdbtnAdmin.setBounds(276, 127, 96, 33);
		getContentPane().add(rdbtnAdmin);
		
		bg = new ButtonGroup();
		bg.add(rdbtnManager);
		bg.add(rdbtnAdmin);
		
		btnBack = new JButton("<");
		btnBack.setBackground(new Color(102, 102, 102));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				Start frmStart = new Start();
				frmStart.setVisible(true);
			}
		});
		btnBack.setBounds(20, 215, 41, 35);
		contentPane.add(btnBack);
	}

}
