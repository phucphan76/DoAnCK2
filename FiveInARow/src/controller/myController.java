package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.w3c.dom.events.MouseEvent;

import model.Button;
import model.Player;
import model.myModel;
import view.Board;

public class myController implements ActionListener, MouseListener{
	private Board b;
	myModel model = new myModel(0, new ArrayList<Player>());
	
	public myController(Board b) {
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		Button cButton = null;
		for(int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				b.getBoard().get(i).get(j).getButton().setEnabled(true);
				if(b.getBoard().get(i).get(j).getButton()==clickedButton) {
					b.getBoard().get(i).get(j).setValue(myModel.getCurrentPlayer());
					cButton = b.getBoard().get(i).get(j);
					b.sendMessage(i+","+j);
				}
			}
		}
		if(b.progressThread == null) {
			b.startProgressSimulation();
		} else {
			b.progressThread.interrupt();
			b.getpBr().updateProgress(0);
			b.getpBr().setProgress(0);
			b.updateProgressBar(0);
			b.counter = 0;
		}
		clickedButton.setIcon(model.getPlayer().get(myModel.getCurrentPlayer()).getMark());
		b.fiveInRows(cButton);
		b.fiveInColumns(cButton);
		b.fiveInDiagonal1(cButton);
		b.fiveInDiagonal2(cButton);
		myModel.setCurrentPlayer();	
		clickedButton.removeActionListener(this);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {

	}

}
