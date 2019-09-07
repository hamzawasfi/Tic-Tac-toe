package Gaame.graphics.Canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Gaame.Game;
import Gaame.graphics.level.LevelManager;

public class PlayingCanvas{

	public int xp, yp ,size;
	public static int columnClicks = 0;
	
	private String text = "";
	
	private Column column;
	public LevelManager level;
	
	public List<Column> columns = new ArrayList<Column>();
		
	public PlayingCanvas(int xp, int yp, int size) {
		this.xp = xp;
		this.yp = yp;
		this.size = size;
		level = Game.getLevel();
	}

	public void manageColumns() {
		int xOffset = xp;
		int yOffset = yp;
		int border = 5;
		int columnSize = (size - (border * 4) / 3) / 3;

		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				column = new Column(xOffset + border, yOffset + border, columnSize - border, new ColumnActionListener() {
					public void perform() {
					}
				});
				columns.add(column);
				xOffset += columnSize;
			}
			yOffset += columnSize;
			xOffset = xp;
		}
	}
	
	public PlayingCanvas setText(String text) {
		this.text = text;
		return this;
	}
	
	public void clearCanvas() {
		for (int i = 0; i < Game.getLevel().canvas.columns.size(); i++) {
			Game.getLevel().canvas.columns.get(i).setChar("");
		}
		for (int i = 0; i < Game.getLevel().canvas.columns.size(); i++) {
			Game.getLevel().canvas.columns.get(i).clicked = false;
		}
		Game.getLevel().win = false;
		for (int i = 0; i < Game.getLevel().canvas.columns.size(); i++) {
			Game.getLevel().canvas.columns.get(i).setColor(0xffffff);
		}
		for (int i = 0; i < Game.getLevel().canvas.columns.size(); i++) {
			Game.getLevel().canvas.columns.get(i).setChar("");
		}
		columnClicks = 0;
		text = "";
	}
	
	public void update() {
		for (Column column : columns) {
			column.update();
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(0));
		g.fillRect(xp, yp, size, size);
		for (Column column : columns) {
			column.render(g);
		}
		g.setColor(new Color(0xff0022));
		g.setFont(new Font("Verdana", Font.PLAIN, 120));
		g.drawString(text, xp + 10, yp + 350);
	}
}
