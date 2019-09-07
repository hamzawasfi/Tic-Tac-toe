package Gaame.graphics.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Gaame.Utils.Vector2i;

public class UILabel extends UIComponenet{

	public String text = "";
	private Font font;
	
	public UILabel(Vector2i position) {
		super(position);
		font = new Font("Helvetica", Font.PLAIN, 24);
		color = new Color(0xff00ff);
	}
	
	public UILabel setText(String text) {
		this.text = text;
		return this;
	}
	
	public UILabel setFont(Font font) {
		this.font = font;
		return this;
	}
	
	public UILabel setColor(Color color) {
		this.color = color;
		return this;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.x + offset.x, position.y + offset.y);
	}
	
}
