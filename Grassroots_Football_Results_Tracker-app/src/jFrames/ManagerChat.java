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

@SuppressWarnings("serial")
public class ManagerChat extends JFrame {

	/**The content pane */
	private JPanel contentPane;
	
	/**The text area */
	private JTextArea txtType;
	
	/**The message pane */
	private JTextPane message;
	
	/**The list of admins */
	private JComboBox<String> sendTo;
	
	/**The list of managers */
	private JComboBox<String> comboBox;
	
	/**The send button */
	private JButton btnSend;
	
	/** The refresh button */
	private JButton btnRefresh;
	
	/**The back button */
	private JButton btnBack;
	
	/**The send to manager label */
	private JLabel lblSendToManager;
	
	/** The admin label */
	private JLabel lblFromAdmin; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerChat frame = new ManagerChat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagerChat() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtType = new JTextArea();
		txtType.setBounds(90, 344, 592, 52);
		contentPane.add(txtType);

		btnSend = new JButton(">");
		btnSend.setForeground(new Color(255, 255, 255));
		btnSend.setBackground(new Color(102, 102, 102));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String managerMessage = txtType.getText();
				String recievedBy = sendTo.getSelectedItem().toString();
				String sender = comboBox.getSelectedItem().toString();

				if (CheckMessage.validMessage(managerMessage)) {
					try {
						Connection con;
						con = ConnectionDB.getConnection();
						PreparedStatement posted;
						posted = con.prepareStatement("INSERT INTO chat(managerMessage, sentBy, recievedBy) VALUES('"
								+ managerMessage + "', '" + sender + "' ,'" + recievedBy + "')");

						posted.executeUpdate();
						System.out.println("successful chat");

						txtType.setText(null);

					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});
		btnSend.setBounds(692, 344, 53, 52);
		contentPane.add(btnSend);

		btnRefresh = new JButton("@");
		btnRefresh.setBackground(new Color(102, 102, 102));
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String recievedBy = sendTo.getSelectedItem().toString();

				try {
					Connection con = ConnectionDB.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT adminMessage FROM chat WHERE sentBy = '"+ recievedBy +"'");

					ResultSet result = statement.executeQuery();
					ArrayList<String> array = new ArrayList<String>();
					while (result.next()) {
						System.out.println(result.getString("adminMessage"));
						array.add(result.getString("adminMessage"));
					}					
					
					for(int i = 0; i< array.size(); i++) {
						message.setText(array.get(i).toString());
					}
					
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnRefresh.setBounds(27, 344, 53, 52);
		contentPane.add(btnRefresh);

		message = new JTextPane();
		message.setBounds(27, 82, 718, 251);
		contentPane.add(message);

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
		btnBack.setBounds(27, 407, 53, 43);
		contentPane.add(btnBack);

		sendTo = new JComboBox<String>();
		sendTo.setForeground(new Color(255, 255, 255));
		sendTo.setBackground(new Color(102, 102, 102));
		sendTo.setSelectedIndex(-1);
		for (String admin : Admin.getAdmin()) {
			sendTo.addItem(admin);
		}
		sendTo.setBounds(27, 51, 103, 20);
		contentPane.add(sendTo);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(102, 102, 102));
		comboBox.setForeground(new Color(255, 255, 255));
		for (String manager : Manager.getManager()) {
			comboBox.addItem(manager);
		}
		comboBox.setBounds(642, 51, 103, 20);
		contentPane.add(comboBox);
		
		lblSendToManager = new JLabel("Send to Admin:");
		lblSendToManager.setBounds(27, 27, 103, 14);
		contentPane.add(lblSendToManager);
		
		lblFromAdmin = new JLabel("From Manager:");
		lblFromAdmin.setBounds(642, 27, 103, 14);
		contentPane.add(lblFromAdmin);
	}
}
