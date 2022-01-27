package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class GuestMenu extends JFrame {

	/** The content pane */
	private JPanel contentPane;
	
	/**The league table button */
	private JButton btnLeagueTable;
	
	/**The league table predictions button */
	private JButton btnLeaguePredictions;
	
	/**The back button */
	private JButton btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestMenu frame = new GuestMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GuestMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLeagueTable = new JButton("League Table");
		btnLeagueTable.setBackground(new Color(102, 102, 102));
		btnLeagueTable.setForeground(new Color(255, 255, 255));
		btnLeagueTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				DisplayTable frmTable = null;
				try {
					frmTable = new DisplayTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frmTable.setVisible(true);
			}
		});
		btnLeagueTable.setBounds(109, 87, 222, 35);
		getContentPane().add(btnLeagueTable);

		btnLeaguePredictions = new JButton("League Predictions");
		btnLeaguePredictions.setBackground(new Color(102, 102, 102));
		btnLeaguePredictions.setForeground(new Color(255, 255, 255));
		btnLeaguePredictions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				DisplayPredictedTable frmPredictedTable = null;
				try {
					frmPredictedTable = new DisplayPredictedTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frmPredictedTable.setVisible(true);
			
		}});
		btnLeaguePredictions.setBounds(109, 134, 222, 35);
		getContentPane().add(btnLeaguePredictions);

		btnBack = new JButton("<");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(102, 102, 102));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				Start frmStart = new Start();
				frmStart.setVisible(true);
			}
		});
		btnBack.setBounds(10, 215, 41, 35);
		contentPane.add(btnBack);
	}

}
