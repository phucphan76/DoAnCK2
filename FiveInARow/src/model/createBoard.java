package model;

import java.net.Socket;

import view.Board;

public class createBoard implements Runnable{
	private Socket socket;
	private Board board;
	
	public createBoard(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		board = new Board(socket);
        board.setVisible(true);
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
