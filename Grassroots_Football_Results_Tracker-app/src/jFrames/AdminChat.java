package jFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Admin;
import core.CheckMessage;
import core.Manager;
import databaseManagement.ConnectionDB;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * 
 * @author jw01543
 *
 */

@SuppressWarnings("serial")
public class AdminChat extends JFrame {

	/** The content pane of the JPanel */
	private JPanel contentPane;
	
	/** The text area where messages are typed */
	private JTextArea txtType;
	
	/** The text pane where messages are displayed */
	private JTextPane message;
	
	/** A list of potential recipients */
	private JComboBox<String> sendTo;
	
	/** A list of potential senders */
	private JComboBox<String> sentBy;
	
	/** The send button */
	private JButton btnSend;
	
	/** The refresh button */
	private JButton btnRefresh;
	
	/** The back button */
	private JButton btnBack;
	
	/** The send to label */
	private JLabel lblSendToManager;
	
	/** The sent from label */
	private JLabel lblFromAdmin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChat frame = new AdminChat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminChat() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtType = new JTextArea();
		txtType.setBackground(new Color(255, 255, 255));
		txtType.setBounds(90, 344, 592, 52);
		contentPane.add(txtType);

		btnSend = new JButton(">");
		btnSend.setForeground(new Color(255, 255, 255));
		btnSend.setBackground(new Color(102, 102, 102));
		// Activate the button
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String adminMessage = txtType.getText();
				String recievedBy = sendTo.getSelectedItem().toString();
				String sender = sentBy.getSelectedItem().toString();

				// Checks if the message is of a valid format
				if (CheckMessage.validMessage(adminMessage)) {
					try {
						Connection con;
						con = ConnectionDB.getConnection();
						PreparedStatement posted;
						posted = con.prepareStatement("INSERT INTO chat(adminMessage, sentBy, recievedBy) VALUES('"
								+ adminMessage + "', '" + sender + "' ,'" + recievedBy + "')");

						posted.executeUpdate();
						
						txtType.setText(null);

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnSend.setBounds(692, 344, 53, 52);
		contentPane.add(btnSend);

		btnRefresh = new JButton("@");
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(102, 102, 102));
		// Activate the button
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String recievedBy = sendTo.getSelectedItem().toString();

				// Get messages from the database and display
				try {
					Connection con = ConnectionDB.getConnection();
					PreparedStatement statement = con
							.prepareStatement("SELECT managerMessage FROM chat WHERE sentBy = '" + recievedBy + "'");

					ResultSet result = statement.executeQuery();
					
					ArrayList<String> array = new ArrayList<String>();
					
					while (result.next()) {
						array.add(result.getString("managerMessage"));
					}

					// Iterates through all the messages found in the database
					for (int i = 0; i < array.size(); i++) {
						message.setText(array.get(i).toString());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(27, 344, 53, 52);
		contentPane.add(btnRefresh);

		message = new JTextPane();
		message.setBackground(new Color(255, 255, 255));
		message.setBounds(27, 82, 718, 251);
		contentPane.add(message);

		btnBack = new JButton("<");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(102, 102, 102));
		// Activates the back button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Closes current JFrame
				dispose();
				
				AdminMenu frmAdminMenu = new AdminMenu();
				
				// Sets the admin menu window frame visible
				frmAdminMenu.setVisible(true);
			}
		});
		btnBack.setBounds(27, 404, 53, 46);
		contentPane.add(btnBack);

		sendTo = new JComboBox<String>();
		sendTo.setBackground(new Color(204, 204, 204));
		sendTo.setSelectedIndex(-1);
		for (String manager : Manager.getManager()) {
			sendTo.addItem(manager);
		}
		sendTo.setBounds(27, 51, 103, 20);
		contentPane.add(sendTo);

		sentBy = new JComboBox<String>();
		sentBy.setBackground(Color.LIGHT_GRAY);
		for (String admin : Admin.getAdmin()) {
			sentBy.addItem(admin);
		}
		sentBy.setBounds(642, 51, 103, 20);
		contentPane.add(sentBy);

		lblSendToManager = new JLabel("Send to Manager:");
		lblSendToManager.setBounds(27, 27, 103, 14);
		contentPane.add(lblSendToManager);

		lblFromAdmin = new JLabel("From Admin:");
		lblFromAdmin.setBounds(642, 27, 103, 14);
		contentPane.add(lblFromAdmin);
	}
}
