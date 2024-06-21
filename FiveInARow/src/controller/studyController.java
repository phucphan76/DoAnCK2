package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Player;
import project.Select;
import view.Main;
import view.login;
import view.signup;

public class studyController implements ActionListener{
	private Player player;
	private login log;
	
	public studyController(Player player, login log) {
		this.player = player;
		this.log = log;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
	    String buttonText = clickedButton.getText();
		if(buttonText.equals("Login")) {
			int check = 0;
			String email = log.textField.getText();
			player.setName(email);
			String password = log.passwordField.getText();
			if(email.equals("") || password.equals("")) {
				check = 1;
				JOptionPane.showMessageDialog(null, "Every Field Is Required");
			} else {
				ResultSet rs = Select.getData("select *from Players where email='"+email+"' and password='"+password+"'");
				try {
					if(rs.next()) {
						ResultSet rs2 = Select.getData("SELECT COUNT(*) from  Games WHERE player1_id = "+rs.getString(1)+" OR player2_id = "+rs.getString(1));
						check = 1;
						log.setVisible(false);
						Main main = new Main();
						main.setId(rs.getInt(1));
						main.IDLabel_2.setText(rs.getString(1));
						player.setId(rs.getInt(1));
						main.NameLabel_2.setText(email);
						if(rs2.next()) {
							ResultSet rs3 = Select.getData("SELECT COUNT(*) from  Games WHERE winner_id = "+rs.getString(1));
							main.GameLabel_2.setText(rs2.getString(1));
							if(rs3.next()) {
								main.WinLabel_2.setText(rs3.getString(1));
							}
						}
						main.mainUpdate();
						main.setVisible(true);
					}
				} catch (Exception e2) {
					System.out.println("1");
					JOptionPane.showMessageDialog(null, e2);
				}
			}
			if(check == 0) {
				JOptionPane.showMessageDialog(null, "Incorrect Email Or Password");
			}
		}else {
			log.setVisible(false);
			new signup().setVisible(true);
		}

	}
}
