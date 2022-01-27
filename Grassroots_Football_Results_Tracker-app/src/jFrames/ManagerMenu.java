package jFrames;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ManagerMenu extends JFrame {

	/** The content pane */
	private JPanel contentPane;
	
	/** The submit results button */
	private JButton btnSubmitResults;
	
	/** The chat button */
	private JButton btnChat;
	
	/** The guest button */
	private JButton btnGuest;
	
	/** The back button */
	private JButton btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMenu frame = new ManagerMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagerMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSubmitResults = new JButton("Submit Results");
		btnSubmitResults.setForeground(new Color(255, 255, 255));
		btnSubmitResults.setBackground(new Color(102, 102, 102));
		btnSubmitResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
				SubmitResults frmSubmit = new SubmitResults();
				frmSubmit.setVisible(true);
			}
		});
		btnSubmitResults.setBounds(126, 40, 171, 42);
		contentPane.add(btnSubmitResults);

		btnChat = new JButton("Chat");
		btnChat.setBackground(new Color(102, 102, 102));
		btnChat.setForeground(new Color(255, 255, 255));
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				ManagerChat frmChat = null;
				try {
					frmChat = new ManagerChat();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frmChat.setVisible(true);
			}
		});
		btnChat.setBounds(126, 93, 171, 42);
		contentPane.add(btnChat);

		btnGuest = new JButton("Guest");
		btnGuest.setForeground(new Color(255, 255, 255));
		btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuest.setBackground(new Color(102, 102, 102));
		btnGuest.setBounds(126, 146, 171, 44);
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args1) {
				
				dispose();
				GuestMenu frmGuestMenu = new GuestMenu();
				frmGuestMenu.setVisible(true);
			}
		});
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		getContentPane().add(btnGuest);
		
		btnBack = new JButton("Log Out");
		btnBack.setBackground(new Color(255, 51, 51));
		btnBack.setForeground(new Color(255, 255, 255));
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