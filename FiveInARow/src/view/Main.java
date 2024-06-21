package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.mainListener;
import project.Select;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int id = 0;
	public JLabel WinLabel_2;
	public JLabel LossLabel_2;
	public JLabel RatioLabel_2;
	public JLabel GameLabel_2;
	public static JLabel NameLabel_2;
	public static JLabel IDLabel_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 218);
		contentPane.add(panel);
		panel.setLayout(null);
		
		mainListener ml = new mainListener(this);
		
		JLabel NameLabel_1 = new JLabel("Nick Name:");
		NameLabel_1.setForeground(Color.WHITE);
		NameLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		NameLabel_1.setBounds(25, 95, 103, 20);
		panel.add(NameLabel_1);
		
		JLabel GameLabel_1 = new JLabel("Game Played:");
		GameLabel_1.setForeground(Color.WHITE);
		GameLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		GameLabel_1.setBounds(25, 144, 103, 20);
		panel.add(GameLabel_1);
		
		JLabel WinLabel_1 = new JLabel("Win:");
		WinLabel_1.setForeground(Color.WHITE);
		WinLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		WinLabel_1.setBounds(319, 46, 103, 20);
		panel.add(WinLabel_1);
		
		JLabel LossLabel_1 = new JLabel("Loss:");
		LossLabel_1.setForeground(Color.WHITE);
		LossLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		LossLabel_1.setBounds(319, 95, 103, 20);
		panel.add(LossLabel_1);
		
		JLabel RatioLabel_1 = new JLabel("Win Ratio:");
		RatioLabel_1.setForeground(Color.WHITE);
		RatioLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		RatioLabel_1.setBounds(319, 144, 103, 20);
		panel.add(RatioLabel_1);
		
		NameLabel_2 = new JLabel("New label");
		NameLabel_2.setForeground(Color.WHITE);
		NameLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		NameLabel_2.setBounds(138, 95, 172, 20);
		panel.add(NameLabel_2);
		
		GameLabel_2 = new JLabel("New label");
		GameLabel_2.setForeground(Color.WHITE);
		GameLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		GameLabel_2.setBounds(138, 144, 100, 20);
		panel.add(GameLabel_2);
		
		WinLabel_2 = new JLabel("New label");
		WinLabel_2.setForeground(Color.WHITE);
		WinLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		WinLabel_2.setBounds(432, 46, 100, 20);
		panel.add(WinLabel_2);
		
		LossLabel_2 = new JLabel("New label");
		LossLabel_2.setForeground(Color.WHITE);
		LossLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		LossLabel_2.setBounds(432, 95, 100, 20);
		panel.add(LossLabel_2);
		
		RatioLabel_2 = new JLabel("New label");
		RatioLabel_2.setForeground(Color.WHITE);
		RatioLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		RatioLabel_2.setBounds(432, 144, 100, 20);
		panel.add(RatioLabel_2);
		
		JLabel IDLabel_1 = new JLabel("User ID:");
		IDLabel_1.setForeground(Color.WHITE);
		IDLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		IDLabel_1.setBounds(25, 46, 103, 20);
		panel.add(IDLabel_1);
		
		IDLabel_2 = new JLabel("New label");
		IDLabel_2.setForeground(Color.WHITE);
		IDLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 17));
		IDLabel_2.setBounds(138, 46, 100, 20);
		panel.add(IDLabel_2);
		
		JLabel BackgroundLabel = new JLabel("New label");
		BackgroundLabel.setIcon(new ImageIcon(Main.class.getResource("/images/background.jpg")));
		BackgroundLabel.setBounds(0, 0, 584, 218);
		panel.add(BackgroundLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 217, 584, 204);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/images/A.png")));
		lblNewLabel.setBounds(60, 22, 120, 120);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/images/B.png")));
		lblNewLabel_1.setBounds(235, 22, 120, 120);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Main.class.getResource("/images/C.png")));
		lblNewLabel_2.setBounds(410, 22, 120, 120);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(60, 153, 120, 27);
		btnNewButton.addActionListener(ml);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(235, 153, 120, 27);
		btnNewButton_1.addActionListener(ml);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit Game");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(410, 153, 120, 27);
		btnNewButton_2.addActionListener(ml);
		panel_1.add(btnNewButton_2);

	}
	
	public void mainUpdate() {
		int a = Integer.parseInt(GameLabel_2.getText())-Integer.parseInt(WinLabel_2.getText());
		LossLabel_2.setText(""+a);
		double percent = Double.parseDouble(WinLabel_2.getText())/Double.parseDouble(GameLabel_2.getText())*100;
		double rounded = Math.round(percent);
		RatioLabel_2.setText(""+rounded+"%");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
