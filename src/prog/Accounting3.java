package prog;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;


public class Accounting3 {

	private JFrame frame;
	public static String budget;
	public static int ss,budget2;
	/**
	 * Launch the application.
	 */
	public void Screen2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accounting3 window = new Accounting3();
					window.frame.setVisible(true);
					Accounting4 s=new Accounting4();
					//ss=s.settlement;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Accounting3() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(188, 143, 143));
		frame.setBounds(100, 100, 405, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		textArea.setBounds(133, 183, 129, 23);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setBackground(new Color(255, 250, 250));
		btnNewButton_1.setFont(new Font("Sitka Small", Font.BOLD, 14));
		btnNewButton_1.setBounds(272, 178, 76, 30);
		btnNewButton_1.setBorder(new RoundedBorder(15)); 
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setForeground(new Color(255, 228, 225));
		btnNewButton_1.setContentAreaFilled(true);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Reset");
		btnNewButton_1_1.setBackground(new Color(255, 250, 250));
		btnNewButton_1_1.setFont(new Font("Sitka Small", Font.BOLD, 14));
		btnNewButton_1_1.setVisible(false);
		btnNewButton_1_1.setBounds(272, 178, 76, 30);
		btnNewButton_1_1.setBorder(new RoundedBorder(15)); 
		btnNewButton_1_1.setOpaque(false);
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setForeground(new Color(255, 228, 225));
		btnNewButton_1_1.setContentAreaFilled(true);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(133, 181, 129, 27);
		lblNewLabel_2.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblNewLabel_2.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				budget=textArea.getText();
				textArea.setVisible(false);
				lblNewLabel_2.setText(budget);
				btnNewButton_1.setVisible(false);
				lblNewLabel_2.setVisible(true);
				btnNewButton_1_1.setVisible(true);
				budget2=Integer.parseInt(budget);
				if(1000>budget2) {
					UIManager.put("OptionPane.background",new ColorUIResource(250,240,230)); 
					UIManager.put("Panel.background",new ColorUIResource(250,240,230)); 
					UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("MS Reference Sans Serif", Font.ITALIC, 13)));
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("MS Reference Sans Serif", Font.ITALIC, 13)));
					int res=JOptionPane.showConfirmDialog( lblNewLabel_2, "Your settlement is large than budget", null, JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setVisible(true);
				btnNewButton_1.setVisible(true);
				btnNewButton_1_1.setVisible(false);
				textArea.setText("");
			}
		});
		
		
	}
	public void returnbutton() {
		JButton btnNewButton = new JButton("\uF0E7");
		btnNewButton.setBackground(new Color(255, 250, 250));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 10, 61, 38);
		frame.getContentPane().add(btnNewButton);
	}
	
	public void Label() {

		JLabel lblNewLabel = new JLabel("You can set the reminder:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblNewLabel.setBounds(66, 102, 268, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("budget:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(76, 178, 61, 30);
		frame.getContentPane().add(lblNewLabel_1);
	}
	public void name() {
		
	}
	private static class RoundedBorder implements Border {
		private int radius;
		RoundedBorder(int radius) {
			this.radius = radius;
		}
		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}
		public boolean isBorderOpaque() {
			return true;
		}
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}
	}

}