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
import core.TablePredictionsData;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class DisplayPredictedTable extends JFrame {

	/** The content pane */
	private JPanel contentPane;
	
	/** The league table */
	private static JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayPredictedTable frame = new DisplayPredictedTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DisplayPredictedTable() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 59, 881, 440);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null }, },
						new String[] { "Team", "GP", "W", "D", "L", "F", "A", "GD", "Pts" }) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { String.class, Integer.class, Integer.class, Integer.class,
							Integer.class, Integer.class, Integer.class, Integer.class, Integer.class };

					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		table.getColumnModel().getColumn(2).setResizable(false);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		ArrayList<String> list = TableData.listTeams();

		Object rowData[] = new Object[9];
		for (int i = 0; i < list.size(); i++) {
			rowData[0] = list.get(i);
			rowData[1] = TablePredictionsData.getNumberSeasonGames();
			rowData[2] = TablePredictionsData.getPredicitedGamesWon(list.get(i));
			rowData[3] = TablePredictionsData.getPredicitedGamesDrawn(list.get(i));
			rowData[4] = TablePredictionsData.getPredicitedGamesLost(list.get(i));
			rowData[5] = TablePredictionsData.getPredictedGoalsScored(list.get(i));
			rowData[6] = TablePredictionsData.getPredictedGoalsConceded(list.get(i));
			rowData[7] = TablePredictionsData.getPredicitedGoalDifference(list.get(i));
			rowData[8] = TablePredictionsData.getPredictedPoints(list.get(i));

			model.addRow(rowData);
		}

		table.setBounds(326, 59, 648, 491);
		scrollPane.setViewportView(table);

		JButton btnBack = new JButton("<");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				GuestMenu frmGuestMenu = new GuestMenu();
				frmGuestMenu.setVisible(true);
			}
		});
		btnBack.setBounds(10, 515, 41, 35);
		contentPane.add(btnBack);
	}
}
