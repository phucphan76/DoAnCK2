package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	private int id;
	private String name;
	private String XorO;
	private ImageIcon mark;
	
	public Player() {
		
	}
	
	public Player(String name, ImageIcon imageIcon) {
		this.name = name;
		this.mark = imageIcon;
	}
	
	public String getXorO() {
		return XorO;
	}

	public void setXorO(String xorO) {
		XorO = xorO;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getMark() {
		return mark;
	}

	public void setMark(ImageIcon mark) {
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
