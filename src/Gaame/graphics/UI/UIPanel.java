package Gaame.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Gaame.Utils.Vector2i;

public class UIPanel extends UIComponenet{
	
	public List<UIComponenet> components = new CopyOnWriteArrayList<UIComponenet>();
	private Vector2i size;
	
	public UIPanel(Vector2i position, Vector2i size) {
		super(position);
		this.position = position;
		this.size = size;
		color = new Color(0xcacaca);
	}
	
	public void addComponent(UIComponenet component) {
		component.init(this);
		components.add(component);
	}
	
	public void update() {
		for (UIComponenet component : components) {
			component.update();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(position.x, position.y, size.x, size.y);
		for (UIComponenet component : components) {
			component.render(g);
		}
	}
}
