package view;

import java.awt.Dimension;	
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.mainListener;
import controller.myController;
import model.Button;
import model.Client;
import model.Player;
import model.buttonList;
import model.myModel;
import model.progressBarModel;
import project.InsertUpdateDelete;
import project.Select;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Board extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private List<List<Button>> board;
	public JProgressBar progressBar;
	public Thread progressThread;
	public int counter;
	public Socket socket;
	private InputStream input;
	private OutputStream output;
	public String XorO;
	private String id = Main.IDLabel_2.getText();
	public int turn = 0;

	public List<List<Button>> getBoard() {
		return board;
	}

	public void setBoard(List<List<Button>> board) {
		this.board = board;
	}
	private static JLabel markLabel;
	static myModel model = new myModel(0, new ArrayList<Player>());
	private progressBarModel pBr = new progressBarModel();
	private JPanel panelBoard;
	

	/**
	 * Create the frame.
	 */
	public Board(Socket socket) {
		this.socket = socket;
		try {
            this.input = socket.getInputStream();
            this.output = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void initializeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelBoard = new JPanel();
		panelBoard.setForeground(new Color(0, 0, 0));
		panelBoard.setBackground(Color.LIGHT_GRAY);
		panelBoard.setBounds(10, 11, 512, 512);
		panelBoard.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 1));
		contentPane.add(panelBoard);
		
		JPanel panelAvatar = new JPanel();
		panelAvatar.setBounds(531, 11, 266, 266);
		contentPane.add(panelAvatar);
		panelAvatar.setLayout(new BorderLayout(0, 0));
		
		JLabel markLabel_1_1 = new JLabel("New label");
		markLabel_1_1.setIcon(new ImageIcon(Board.class.getResource("/images/avatar.png")));
		panelAvatar.add(markLabel_1_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(531, 288, 266, 234);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 120, 26);
		textField.setText(Main.NameLabel_2.getText());
		panel_2.add(textField);
		textField.setColumns(10);
		
		progressBar = new JProgressBar(0, pBr.getMaxValue());
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(10, 48, 120, 14);
		panel_2.add(progressBar);
			
		markLabel = new JLabel("");
		markLabel.setBackground(Color.LIGHT_GRAY);
		markLabel.setBounds(148, 19, 100, 100);
		markLabel.setIcon(new ImageIcon(Board.class.getResource("/images/pepe.png")));
		panel_2.add(markLabel);
		
		
		textField_1 = new JTextField();
		textField_1.setText("127.0.0.1");
		textField_1.setBounds(10, 73, 120, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(socket.getInetAddress().toString());
		
		JButton btnNewButton = new JButton("LAN\r\n");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setBounds(10, 104, 120, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(e -> {
			int a = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the match?");
			if(a == 0) {
				dispose();
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainListener.main.setVisible(true);
			}else {
				
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(Board.class.getResource("/images/exitGame.png")));
		btnNewButton_1.setBounds(10, 142, 120, 75);
		panel_2.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("EXIT MATCH");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 217, 120, 14);
		panel_2.add(lblNewLabel);
		
		JLabel markLabel_1 = new JLabel("");
		markLabel_1.setIcon(new ImageIcon(Board.class.getResource("/images/pepe.png")));
		markLabel_1.setBackground(Color.LIGHT_GRAY);
		markLabel_1.setBounds(148, 123, 100, 100);
		panel_2.add(markLabel_1);
		
		board = new ArrayList<>();
		
		for(int i = 0; i < 17; i++) {
			List<Button> row = new ArrayList<>();
			for (int j = 0; j < 17; j++) {
				JButton button = new JButton();
				Button modelButton = new Button(i, j, button);
				modelButton.getButton().setPreferredSize(new Dimension(29, 29));
				modelButton.getButton().setBackground(Color.WHITE);
				modelButton.getButton().addActionListener(new myController(this));
				panelBoard.add(modelButton.getButton());
				row.add(modelButton);
			}
			board.add(row);
		}
		
		buttonList btL = new buttonList(board);
		setVisible(true);
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public static void setMarkLabel(ImageIcon img) {
			try {
				markLabel.setIcon(img);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void fiveInRows(Button btn) {
		int count = 1;
		int cRight = 1;
		int cLeft = 1;
		boolean check = true;
		while(check) {
			if(btn.getColumn()+cRight>16) {
				break;
			}
			if(board.get(btn.getRow()).get(btn.getColumn()+cRight).getValue()==btn.getValue()) {
				count++;
				cRight++;
			}else {
				check = false;
			}
		}
		check = true;
		while(check) {
			if(btn.getColumn()-cLeft<0) {
				break;
			}
			if(board.get(btn.getRow()).get(btn.getColumn()-cLeft).getValue()==btn.getValue()) {
				count++;
				cLeft++;
			}else {
				check = false;
			}
		}
		if(count>=5) {
			matchStopper();

		}
	}
	
	public void fiveInColumns(Button btn) {
		int count = 1;
		int cTop = 1;
		int cBot = 1;
		boolean check = true;
		while(check) {
			if(btn.getRow()-cTop<0) {
				break;
			}
			if(board.get(btn.getRow()-cTop).get(btn.getColumn()).getValue()==btn.getValue()) {
				count++;
				cTop++;
			}else {
				check = false;
			}
		}
		check = true;
		while(check) {
			if(btn.getRow()+cBot>16) {
				break;
			}
			if(board.get(btn.getRow()+cBot).get(btn.getColumn()).getValue()==btn.getValue()) {
				count++;
				cBot++;
			}else {
				check = false;
			}
		}
		if(count>=5) {
			matchStopper();
		}
	}
	
	public void fiveInDiagonal1(Button btn) {
		int count = 1;
		int cTop = 1;
		int cBot = 1;
		boolean check = true;
		while(check) {
			if(btn.getRow()-cTop < 0 || btn.getColumn()-cTop < 0) {
				break;
			}
			if(board.get(btn.getRow()-cTop).get(btn.getColumn()-cTop).getValue()==btn.getValue()) {
				count++;
				cTop++;
			}else {
				check = false;
			}
		}
		check = true;
		while(check) {
			if(btn.getColumn()+cBot > 16 || btn.getRow()+cBot > 16) {
				break;
			}
			if(board.get(btn.getRow()+cBot).get(btn.getColumn()+cBot).getValue()==btn.getValue()) {
				count++;
				cBot++;
			}else {
				check = false;
			}
		}
		if(count>=5) {
			matchStopper();
		}
	}
	public void fiveInDiagonal2(Button btn) {
		int count = 1;
		int cTop = 1;
		int cBot = 1;
		boolean check = true;
		while(check) {
			if(btn.getColumn()-cTop < 0 || btn.getRow()+cTop > 16) {
				break;
			}
			if(board.get(btn.getRow()+cTop).get(btn.getColumn()-cTop).getValue()==btn.getValue()) {
				count++;
				cTop++;
			}else {
				check = false;
			}
		}
		check = true;
		while(check) {
			if(btn.getColumn()+cBot > 16 || btn.getRow()-cBot < 0) {
				break;
			}
			if(board.get(btn.getRow()-cBot).get(btn.getColumn()+cBot).getValue()==btn.getValue()) {
				count++;
				cBot++;
			}else {
				check = false;
			}
		}
		if(count>=5) {
			matchStopper();
		}
	}
	
	public progressBarModel getpBr() {
		return pBr;
	}
	
	public void updateProgressBar(int progress) {
	    progressBar.setValue(progress);
	 }
	
	@SuppressWarnings("deprecation")
	public void startProgressSimulation() {
	    progressThread = new Thread(() -> {
	      for (counter = 0; counter <= getpBr().getMaxValue(); counter++) {
	        try {
	          Thread.sleep(50); // Simulate some work
	          getpBr().updateProgress(1); // Update progress in the model
	          SwingUtilities.invokeLater(() -> updateProgressBar(getpBr().getProgress())); // Update UI from EDT
	          if(getpBr().getProgress() == getpBr().getMaxValue()) {
	        	  for(int i = 0; i < 17; i++) {
	      			for (int j = 0; j < 17; j++) {
	      				board.get(i).get(j).getButton().setEnabled(false);;
	      			}
	      			}
	        	  if(myModel.getCurrentPlayer()==0) {
	      			sendMessage("oWin"+XorO);
	      			JOptionPane.showMessageDialog(contentPane, "Match over, O win!!");
	      		}else {
	      			sendMessage("xWin"+XorO);
	      			JOptionPane.showMessageDialog(contentPane, "Match over, X win!!");
	      		}
	        	  progressThread.stop();
	        	  
	          }
	        } catch (InterruptedException e) {
	          
	        }
	      }
	    });
	    progressThread.start();
	 }
	
	@SuppressWarnings("deprecation")
	public void matchStopper() {
		progressThread.stop();
		for(int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				board.get(i).get(j).getButton().setEnabled(false);;
			}
		}
		if(myModel.getCurrentPlayer()==0) {
			sendMessage("XWin"+XorO);
			JOptionPane.showMessageDialog(contentPane, "Match over, X win!!");
		}else {
			sendMessage("OWin"+XorO);
			JOptionPane.showMessageDialog(contentPane, "Match over, O win!!");
		}
  	  	
	}

	@Override
	public void run() {
		try {
			sendMessage(Main.IDLabel_2.getText());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead=input.read(buffer))!=-1){
                String message = new String(buffer, 0, bytesRead);
                if(message.charAt(0)=='X') {
                	XorO=message;
                	JOptionPane.showMessageDialog(contentPane, "You are X");
                } else if(message.charAt(0)=='O') {
                	XorO=message;
                	JOptionPane.showMessageDialog(contentPane, "You are O");
                }
                if(message.length()>=3) {getMessageButton(message);}
                
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(contentPane, "Client disconnected");
        }
		
	}
	
	public void getMessageButton(String message) {
		int a = 0, b = 0;
		String[] address = message.split(",");
		a = Integer.parseInt(address[0]);
    	b = Integer.parseInt(address[1]);
    	board.get(a).get(b).getButton().doClick();
	} 

	public void sendMessage(String message){
        try {
            output.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
