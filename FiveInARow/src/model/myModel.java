package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import view.Board;

public class myModel {
	private static int currentPlayer;
	private static List<Player> Player;
	private List<List<Button>> buttonList;
	
	public myModel(int currentPlayer, List<Player> Player) {
		this.currentPlayer = currentPlayer;
		this.Player = new ArrayList<Player>();
		this.Player.add(new Player("PlayerX", new ImageIcon(Board.class.getResource("/images/redX.png"))));
		this.Player.add(new Player("PlayerO", new ImageIcon(Board.class.getResource("/images/blueO.png"))));
	}

	public static int getCurrentPlayer() {
		try {
			return currentPlayer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static void setCurrentPlayer() {
		if(currentPlayer==0) {
			currentPlayer = 1;
			Board.setMarkLabel(new ImageIcon(Board.class.getResource("/images/labelO.png")));
		}else {
			currentPlayer = 0;
			Board.setMarkLabel(new ImageIcon(Board.class.getResource("/images/labelX.png")));
		}
	}

	public static List<Player> getPlayer() {
		return Player;
	}

	public void setPlayer(List<Player> player) {
		Player = player;
	}
	
}
