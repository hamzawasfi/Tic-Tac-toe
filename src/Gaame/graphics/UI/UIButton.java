package Gaame.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Gaame.Utils.Vector2i;
import Gaame.inputs.Mouse;

public class UIButton extends UIComponenet {

	public UILabel label;
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;

	private Image image;

	private boolean inside = false;
	private boolean pressed = false;
	private boolean ignorePress = false;
	private boolean ignoreAction = false;

	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
		super(position, size);
		this.actionListener = actionListener;
		Vector2i lp = new Vector2i(position);
		lp.x += 5;
		lp.y += size.y - 7;
		label = new UILabel(lp);
		label.setColor(0);
		label.active = false;

		init();
	}

	public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener) {
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.actionListener = actionListener;
		setImage(image);
		init();
	}

	private void init() {
		color = new Color(0xAAAAAA);
		buttonListener = new UIButtonListener();
	}

	void init(UIPanel panel) {
		super.init(panel);
		if (label != null)
			panel.addComponent(label);
	}

	public void setText(String text) {
		if (text == "")
			label.active = false;
		else
			label.text = text;
	}

	public void performAction() {
		actionListener.perform();
		ignoreAction = true;
	}

	public void ignoreNextPress() {
		ignoreAction = true;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void update() {
		Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
		boolean leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
		if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if (!inside) {
				if (leftMouseButtonDown)
					ignorePress = true;
				else
					ignorePress = false;

				buttonListener.entered(this);
			}
			inside = true;

			if (!pressed && !ignorePress && Mouse.getButton() == MouseEvent.BUTTON1) {
				buttonListener.pressed(this);
				pressed = true;
			} else if (pressed && Mouse.getButton() == MouseEvent.NOBUTTON) {
				if (pressed) {
					buttonListener.released(this);
					if (!ignoreAction)
						actionListener.perform();
					else
						ignoreAction = false;
					pressed = false;
				}
				ignorePress = false;
			}
		} else {
			if (inside) {
				buttonListener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}

	public void render(Graphics g) {
		int x = position.x + offset.x;
		int y = position.y + offset.y;
		if (image != null) {
			g.drawImage(image, x, y, null);
		} else {
			g.setColor(color);
			g.fillRect(x, y, size.x, size.y);

			if (label != null)
				label.render(g);
		}
	}
}
