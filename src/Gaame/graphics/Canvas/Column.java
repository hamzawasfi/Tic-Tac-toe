package Gaame.graphics.Canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import Gaame.Game;
import Gaame.Utils.Vector2i;
import Gaame.inputs.Mouse;

public class Column extends PlayingCanvas {
	
	public String XO = " ";
	
	public ColumnListener columnListener;
	private ColumnActionListener actionListener;

	private boolean inside = false;
	public boolean pressed = false;
	public boolean ignorePress = false;
	private boolean ignoreAction = false;
	public boolean clicked = false;
	public boolean pc = false;

	int xp, yp, size;
	int color = 0xffffff;

	public Column(int xp, int yp, int size, ColumnActionListener actionListener) {
		super(xp, yp, size);
		this.xp = xp;
		this.yp = yp;
		this.size = size;
		this.actionListener = actionListener;
		columnListener = new ColumnListener();
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setChar(String XO) {
		if (XO == " " || XO == "X" || XO == "O") {
			if (columnClicks % 2 == 0)
				XO = "X";
			else
				XO = "O";
		}
		this.XO = XO;
		if(!Game.getLevel().win) {
			columnClicks++;
		}
	}

	public Vector2i getAbsolutePosition() {
		return new Vector2i(xp, yp).add(new Vector2i());
	}
	
	public void performAction() {
		actionListener.perform();
		ignoreAction = true;
	}

	public void ignoreNextPress() {
		ignoreAction = true;
	}
	
	public void setColumnListener(ColumnListener columnListener) {
		this.columnListener = columnListener;
	}

	public void update() {
		Rectangle rect = new Rectangle(getAbsolutePosition().x - 5, getAbsolutePosition().y - 5, size, size);
		boolean leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
		if (rect.contains(new Point(Mouse.getX(), Mouse.getY())) && !Game.getLevel().win) {
			if (!inside) {
				if (leftMouseButtonDown)
					ignorePress = true;
				else
					ignorePress = false;
				if(!pc)
					columnListener.entered(this);
			}
			inside = true;

			if (!pressed && !ignorePress && !clicked && Mouse.getButton() == MouseEvent.BUTTON1) {
				if(!pc)
					columnListener.pressed(this);
				else {
					if(PlayingCanvas.columnClicks == 0)
						if(!Game.getLevel().ai.xPressed && !Game.getLevel().ai.oPressed && Game.getLevel().vsPc)
							Game.getLevel().warning.setText("choose X or O first");
						else
							Game.getLevel().warning.setText("wait for the computer to play");
					else
						Game.getLevel().warning.setText("wait for the computer to play");
				}
				pressed = true;
			} else if (pressed && Mouse.getButton() == MouseEvent.NOBUTTON) {
				if (pressed) {
					columnListener.released(this);
					if (!ignoreAction)
						if(!pc)
							actionListener.perform();
					else
						ignoreAction = false;
					pressed = false;
				}
				ignorePress = false;
			}
		} else {
			if (inside) {
				columnListener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(color));
		g.fillRect(xp, yp, size, size);
		g.setColor(new Color(0xff));
		g.setFont(new Font("Helvetica", Font.PLAIN, 255));
		g.drawString(XO, xp, yp + 186);
	}
}
