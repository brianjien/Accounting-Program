package prog;

import java.awt.Color;

import java.awt.EventQueue;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accounting4 {

	private JFrame frame2;
	private JPanel panel;

	/**
	 * Launch the application.
	 */

	public void Screen3() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Accounting4 window = new Accounting4();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Accounting4() {
		initialize();
		piechart();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame2 = new JFrame();
		frame2.getContentPane().setBackground(new Color(188, 143, 143));
		frame2.setBounds(100, 100, 649, 564);
		frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void piechart() {
		frame2.getContentPane().setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 67, 633, 419);
		frame2.getContentPane().add(panel_1);
		final DefaultPieDataset pieDataset = new DefaultPieDataset();
		int a = 0;		
		int b = 0;
		int c = 0;
		int d = 0;
		String filePath = "hello\\data1.txt";
		File file = new File(filePath);
		Scanner scanner;
		try {
			scanner = new Scanner(file);//read line by line
		    //process each line
		    String line = scanner.nextLine();
		    String line2 = scanner.nextLine();
		    String line3 = scanner.nextLine();
		    String line4 = scanner.nextLine();
		    System.out.println(line);
		    System.out.println(line2);
		    a = Integer.parseInt(line);
		    b = Integer.parseInt(line2);
		    c = Integer.parseInt(line3);
		    d = Integer.parseInt(line4);
		scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pieDataset.setValue("breakfest", a);
		pieDataset.setValue("lunch", b);
		pieDataset.setValue("dinner", c);
		pieDataset.setValue("daily supply", d);
		JFreeChart chart = ChartFactory.createPieChart3D("Expenditure", pieDataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		  PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
		            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
		        plot.setLabelGenerator(gen);
		panel_1.setLayout(null);
		ChartPanel frame = new ChartPanel(chart);
		frame.setBounds(-14, 0, 680, 420);
		panel_1.add(frame);
		frame.setLayout(null);
		 
		 JButton btnNewButton_1 = new JButton("„º");
		 btnNewButton_1.setBounds(0, 0, 61, 38);
		 frame2.getContentPane().add(btnNewButton_1);
		 btnNewButton_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	  frame2.setVisible(false);
		 	}
		 });
		 btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		 btnNewButton_1.setBackground(new Color(255, 250, 250));
	     
		
		frame.setVisible(true);
	
		

	}


}
