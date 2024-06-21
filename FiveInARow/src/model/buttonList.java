package model;

import java.util.ArrayList;
import java.util.List;

public class buttonList {
	private List<List<Button>> board;
	private List<Button> rows;
	
	public buttonList(List<List<Button>> board) {
		board = new ArrayList<List<Button>>();
	}

	public List<List<Button>> getBoard() {
		return board;
	}

	public void setBoard(List<List<Button>> board) {
		this.board = board;
	}
	
}
