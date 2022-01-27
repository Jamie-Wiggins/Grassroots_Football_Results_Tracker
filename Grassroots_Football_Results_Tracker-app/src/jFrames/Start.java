package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class Start extends JFrame {

	/** The content pane */
	private JPanel contentPane;
	
	/** The guest button */
	private JButton btnGuest;
	
	/** The admin or manager button */
	private JButton btnAdminmanager;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnGuest = new JButton("Guest");
		btnGuest.setForeground(new Color(255, 255, 255));
		btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuest.setBackground(new Color(102, 102, 102));
		btnGuest.setBounds(128, 58, 181, 54);
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

		btnAdminmanager = new JButton("Admin/Manager");
		btnAdminmanager.setForeground(new Color(255, 255, 255));
		btnAdminmanager.setBackground(new Color(204, 51, 51));
		btnAdminmanager.setBounds(128, 136, 181, 54);
		btnAdminmanager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args1) {
				
				dispose();
				AccountLogin frmLogin = new AccountLogin();
				frmLogin.setVisible(true);
			}
		});
		getContentPane().add(btnAdminmanager);
	}
}
