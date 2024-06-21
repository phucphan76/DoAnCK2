package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Client;
import view.Main;
import view.login;

public class mainListener implements ActionListener{
	public static Main main;
	
	public mainListener(Main main) {
		mainListener.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
	    String buttonText = clickedButton.getText();
		if(buttonText.equals("New Game")) {
			main.setVisible(false);
			Client client = new Client();
			client.createClient();
		} else if(buttonText.equals("Log Out")) {
			main.dispose();
			login l = new login();
			l.setVisible(true);
		} else {
			System.exit(0);
		}
		
	}

}
