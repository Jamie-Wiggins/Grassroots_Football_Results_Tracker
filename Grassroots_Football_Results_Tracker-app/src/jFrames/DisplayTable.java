package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.TableData;
import javax.swing.JScrollPane;
import java.awt.Color;

@SuppressWarnings("serial")
public class DisplayTable extends JFrame {

	/** The content pane */
	private JPanel contentPane;
	
	/** The league table */
	private static JTable table;
	
	/** The scroll pane */
	private JScrollPane scrollPane;

	/** The table array list */
	private ArrayList<String> list;
	
	/** The back button */
	private JButton btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayTable frame = new DisplayTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DisplayTable() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 26, 930, 473);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setEnabled(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Team", "GP", "W", "D", "L", "F", "A", "GD", "Pts"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setResizable(false);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		list = TableData.listTeams();

		Object rowData[] = new Object[9];
		for (int i = 0; i < list.size(); i++) {
			rowData[0] = list.get(i);
			rowData[1] = TableData.getGamesPlayed(list.get(i));
			rowData[2] = TableData.getGamesWon(list.get(i));
			rowData[3] = TableData.getGamesDrawn(list.get(i));
			rowData[4] = TableData.getGamesLost(list.get(i));
			rowData[5] = TableData.getGoalsScored(list.get(i));
			rowData[6] = TableData.getGoalsConceded(list.get(i));
			rowData[7] = TableData.getGoalDifference(list.get(i));
			rowData[8] = TableData.getPoints(list.get(i));

			model.addRow(rowData);
		}

		table.setBounds(326, 59, 648, 491);
		scrollPane.setViewportView(table);

		btnBack = new JButton("<");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(102, 102, 102));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				GuestMenu frmGuestMenu = new GuestMenu();
				frmGuestMenu.setVisible(true);
			}
		});
		btnBack.setBounds(30, 505, 48, 45);
		contentPane.add(btnBack);
	}
}
