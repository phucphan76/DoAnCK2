package model;

import javax.swing.JButton;

public class Button {
	private int value = 2;
	private int row;
	private int column;
	private JButton button;
	
	public Button(int row, int column, JButton button) {
		this.row = row;
		this.column = column;
		this.button = button;
	}
	
	public Button(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
