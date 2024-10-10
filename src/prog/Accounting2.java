package prog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.RMIFailureHandler;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.JPanel;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class Accounting2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void Screen1() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Accounting2 window = new Accounting2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Accounting2() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	final Object[] columnNames = { "breakfest", "lunch", "dinner", "daily supply", "sattlement" };
	final Object[][] rowData = {};
	private JTextField textField_1;
	private JTextField textField_2;


	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(188, 143, 143));
		frame.setBounds(100, 100, 463, 630);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("\uF0E7");
		btnNewButton.setBackground(new Color(255, 250, 250));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBounds(10, 10, 61, 38);
		frame.getContentPane().add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(10, 58, 423, 385);
		frame.getContentPane().add(panel);

		TableModel tableModel = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(tableModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
		final TableModel tableModel1 = table.getModel();
		tableModel1.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int firstRow = e.getFirstRow();
				int lastRow = e.getLastRow();
				int column = e.getColumn();
				int type = e.getType();
				if (type == TableModelEvent.UPDATE) {
					if (column < 0 || column > 3) {
						return;
					}
					for (int row = firstRow; row <= lastRow; row++) {
						Object breakfestObj = tableModel1.getValueAt(row, 0);
						Object lunchObj = tableModel1.getValueAt(row, 1);
						Object dinnerObj = tableModel1.getValueAt(row, 2);
						Object dailysupplyObj = tableModel1.getValueAt(row, 3);
						int breakfest = 0;
						try {
							breakfest = Integer.parseInt("" + breakfestObj);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						int lunch = 0;
						try {
							lunch = Integer.parseInt("" + lunchObj);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						int dinner = 0;
						try {
							dinner = Integer.parseInt("" + dinnerObj);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						int dailysupply = 0;
						try {
							dailysupply = Integer.parseInt("" + dailysupplyObj);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						int totalScore = breakfest + lunch + dinner + dailysupply;
						tableModel1.setValueAt(totalScore, row, 4);
						int sum = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							sum = sum + Integer.parseInt(table.getValueAt(i, 4).toString());
						}
						textField_1.setText(Integer.toString(sum));
					    int budget = 0;
		                String filePath_budget = "hello/budget.txt";
		                File file_budget = new File(filePath_budget);
		                try {
		                    Scanner scanner = new Scanner(file_budget);
		                    budget = Integer.parseInt(scanner.nextLine()); // Parse the budget value
		                    scanner.close();
		                } catch (FileNotFoundException e1) {
		                    e1.printStackTrace();
		                }

		                // Show the message IMMEDIATELY after getting settlement1 and comparing with budget
		                if (budget < sum) {
		                    JOptionPane.showMessageDialog(btnNewButton, 
		                        "YOU ARE OVER BUDGET.",
		                        "Budget Exceeded", 
		                        JOptionPane.WARNING_MESSAGE);
		                }
						float sum1 = sum;
						int rowsCC = table.getRowCount();
						float average = sum1 / rowsCC;
						textField_2.setText(Float.toString(average));

						int sumbr = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							sumbr = sumbr + Integer.parseInt(table.getValueAt(i, 0).toString());
						}
						int sumlun = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							sumlun = sumlun + Integer.parseInt(table.getValueAt(i, 1).toString());
						}
						int sumdin = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							sumdin = sumdin + Integer.parseInt(table.getValueAt(i, 2).toString());
						}
						int sumsu = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							sumsu = sumsu + Integer.parseInt(table.getValueAt(i, 3).toString());
						}
						
					

						String filePath1 = "hello\\data1.txt";
						File file1 = new File(filePath1);
						try {
							FileWriter fw = new FileWriter(file1);
							BufferedWriter bw = new BufferedWriter(fw);

							bw.write(sumbr + "\n" + sumlun + "\n" + sumdin + "\n" + +sumsu + "\n" + sum);

							bw.close();
							fw.close();

						} catch (IOException ex) {

						}

					}
				}
			}
		});

		MyCellEditor cellEditor = new MyCellEditor(new JTextField());

		for (int i = 1; i < columnNames.length; i++) {
			TableColumn tableColumn = table.getColumn(columnNames[i]);
			tableColumn.setCellEditor(cellEditor);
		}
		table.setRowSorter(rowSorter);
		table.setRowSorter(rowSorter);
		table.setModel(tableModel1);
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("None recoed yet...");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(table, BorderLayout.CENTER);
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) tableModel1).addRow(new Object[] { "" });
				lblNewLabel.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 24));
		btnNewButton_1.setBackground(new Color(255, 250, 250));
		btnNewButton_1.setBounds(372, 13, 61, 38);
		frame.getContentPane().add(btnNewButton_1);

		textField_1 = new JTextField();
		textField_1.setBounds(137, 21, 86, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Settlement");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_1.setBounds(75, 10, 66, 38);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Average");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_2.setBounds(233, 14, 52, 32);
		frame.getContentPane().add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(284, 19, 86, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_2 = new JButton("export");
		btnNewButton_2.setBackground(SystemColor.control);
		btnNewButton_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		// Inside the export button's ActionListener
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Database insertion logic
		        Connection conn = null;
		        Statement stmt = null;
		        int batchSize = 20;
		        String csvFilePath = "hello\\data666.csv";
		        try {
		           
		            stmt = conn.createStatement();
		            conn.setAutoCommit(false);
		            String sql = "INSERT INTO data666 (breakfest, lunch, dinner, dailysupply, settlement) VALUES (?, ?, ?, ?, ?)";
		            PreparedStatement statement = conn.prepareStatement(sql);
		            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
		            String lineText = null;
		            int count = 0;
		            lineReader.readLine(); // Skip the header line
		            while ((lineText = lineReader.readLine()) != null) {
		                String[] data = lineText.split(",");
		                String breakfest = data[0];
		                String lunch = data[1];
		                String dinner = data[2];
		                String dailysupply = data[3];
		                String settlement = data[4];
		                int breakfest1 = Integer.parseInt(breakfest);
		                int lunch1 = Integer.parseInt(lunch);
		                int dinner1 = Integer.parseInt(dinner);
		                int dailysupply1 = Integer.parseInt(dailysupply);
		                int settlement1 = Integer.parseInt(settlement); 
		           
		                statement.setInt(1, breakfest1);
		                statement.setInt(2, lunch1);
		                statement.setInt(3, dinner1);
		                statement.setInt(4, dailysupply1);
		                statement.setInt(5, settlement1);
		                statement.addBatch();

		                if (count % batchSize == 0) {
		                    statement.executeBatch();
		                }

		                // Read the budget value from the file BEFORE comparing it to settlement1
		            

		                count++;
		            }
		            lineReader.close();

		            // Execute the remaining batch
		            statement.executeBatch();
		            conn.commit();
		            stmt.close();
		            conn.close();
		        } catch (SQLException se) {
		            se.printStackTrace();
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        } finally {
		            try {
		                if (stmt != null) stmt.close();
		            } catch (SQLException se2) {
		            }
		            try {
		                if (conn != null) conn.close();
		            } catch (SQLException se) {
		                se.printStackTrace();
		            }
		        }
		    }
		});



		btnNewButton_2.setBounds(10, 467, 124, 54);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("import");
		btnNewButton_2_1.setBackground(SystemColor.control);
		btnNewButton_2_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int rowCount = dm.getRowCount();
				// Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
				String filePath = "hello\\data.txt";
				File file = new File(filePath);

				try {
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					Object[] lines = br.lines().toArray();

					for (int i = 0; i < lines.length; i++) {
						String[] row = lines[i].toString().split(" ");
						model.addRow(row);
					}
				} catch (FileNotFoundException ex) {
				}
				int sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum = sum + Integer.parseInt(table.getValueAt(i, 4).toString());
				}
				textField_1.setText(Integer.toString(sum));
				float sum1 = sum;
				int rowsCC = table.getRowCount();
				float average = sum1 / rowsCC;
				textField_2.setText(Float.toString(average));
				
			     int budget = 0;
	                String filePath_budget = "hello/budget.txt";
	                File file_budget = new File(filePath_budget);
	                try {
	                    Scanner scanner = new Scanner(file_budget);
	                    budget = Integer.parseInt(scanner.nextLine()); // Parse the budget value
	                    scanner.close();
	                } catch (FileNotFoundException e1) {
	                    e1.printStackTrace();
	                }

	                // Show the message IMMEDIATELY after getting settlement1 and comparing with budget
	                if (budget < sum1) {
	                    JOptionPane.showMessageDialog(btnNewButton_2, 
	                        "YOU ARE OVER BUDGET.",
	                        "Budget Exceeded", 
	                        JOptionPane.WARNING_MESSAGE);
	                }
			}
		});
		btnNewButton_2_1.setBounds(161, 467, 124, 54);
		frame.getContentPane().add(btnNewButton_2_1);

		JButton btnNewButton_2_1_1 = new JButton("delete");
		btnNewButton_2_1_1.setBackground(SystemColor.control);
		btnNewButton_2_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				int rowCount = dm.getRowCount();
				// Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					dm.removeRow(i);
				}
				int sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum = sum + Integer.parseInt(table.getValueAt(i, 4).toString());
				}
				textField_1.setText(Integer.toString(sum));
				float sum1 = sum;
				int rowsCC = table.getRowCount();
				float average = sum1 / rowsCC;
				textField_2.setText(Float.toString(average));
			}
		});
		btnNewButton_2_1_1.setBounds(308, 467, 124, 54);
		frame.getContentPane().add(btnNewButton_2_1_1);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBackground(SystemColor.controlDkShadow);
		scrollBar.setForeground(Color.PINK);
		scrollBar.setBounds(430, 58, 17, 385);
		frame.getContentPane().add(scrollBar);

		JButton btnNewButton_3 = new JButton("expenditure");
		btnNewButton_3.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc = tcm.getColumn(0);
				TableColumn tc1 = tcm.getColumn(1);
				TableColumn tc2 = tcm.getColumn(2);
				tc.setHeaderValue("breakfest");
				tc1.setHeaderValue("lunch");
				tc2.setHeaderValue("dinner");
				((DefaultTableModel) tableModel).addColumn("daily supply");
				positionColumn(table, 3);
				th.repaint();
				int sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum = sum + Integer.parseInt(table.getValueAt(i, 4).toString());
				}
				textField_1.setText(Integer.toString(sum));
				float sum1 = sum;
				int rowsCC = table.getRowCount();
				float average = sum1 / rowsCC;
				textField_2.setText(Float.toString(average));
				TableColumn tcol = table.getColumnModel().getColumn(3);
				table.removeColumn(tcol);
			}
		});
		btnNewButton_3.setBounds(35, 531, 167, 50);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("income");
		btnNewButton_4.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc = tcm.getColumn(0);
				TableColumn tc1 = tcm.getColumn(1);
				TableColumn tc2 = tcm.getColumn(2);
				tc.setHeaderValue("Wage");
				tc1.setHeaderValue("Living expenses");
				tc2.setHeaderValue("Bonus");
				int sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum = sum + Integer.parseInt(table.getValueAt(i, 3).toString());
				}
				textField_1.setText(Integer.toString(sum));
				float sum1 = sum;
				int rowsCC = table.getRowCount();
				float average = sum1 / rowsCC;
				textField_2.setText(Float.toString(average));
				th.repaint();

				TableColumn tcol = table.getColumnModel().getColumn(3);
				table.removeColumn(tcol);
			}
		});
		btnNewButton_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 23));
		btnNewButton_4.setBounds(233, 531, 183, 50);
		frame.getContentPane().add(btnNewButton_4);
		
		
		
		
	}

	public static class MyCellEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;

		public MyCellEditor(JTextField textField) {
			super(textField);
		}

		public MyCellEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public MyCellEditor(JComboBox comboBox) {
			super(comboBox);
		}

		@Override
		public boolean stopCellEditing() {
			Component comp = getComponent();
			Object obj = getCellEditorValue();
			if (obj == null || !obj.toString().matches("[0-9]*")) {
				comp.setForeground(Color.RED);
				return false;
			}
			comp.setForeground(Color.BLACK);
			return super.stopCellEditing();
		}

	}

	public void positionColumn(JTable table, int col_Index) {
		table.moveColumn(table.getColumnCount() - 1, col_Index);
	}

}