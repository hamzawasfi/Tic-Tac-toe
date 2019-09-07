package Gaame.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;

import Gaame.Utils.Vector2i;


public class UIComponenet {
	public Vector2i position, size;
	protected Vector2i offset;
	public Color color;
	protected UIPanel panel;
	
	public boolean active = true;
	
	public UIComponenet(Vector2i position) {	
		this.position = position;
		offset = new Vector2i();
	}
	
	public UIComponenet(Vector2i position, Vector2i size) {	
		this.position = position;
		this.size = size;
		offset = new Vector2i();
	}

	public UIComponenet setColor(int color) {
		this.color = new Color(color);
		return this;
	}
	
	public Vector2i getAbsolutePosition() {
		return new Vector2i (position).add(offset);
	}
	
	public void update() {
    }
	
	public void render(Graphics g) {
	}

	void init(UIPanel panel) {
		this.panel = panel;
	}
}
