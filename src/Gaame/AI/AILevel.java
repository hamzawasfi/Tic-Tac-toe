package Gaame.AI;

import java.awt.Font;

import Gaame.Game;
import Gaame.Utils.Vector2i;
import Gaame.graphics.Canvas.PlayingCanvas;
import Gaame.graphics.UI.UIActionListener;
import Gaame.graphics.UI.UIButton;
import Gaame.graphics.UI.UIButtonListener;
import Gaame.graphics.UI.UILabel;
import Gaame.graphics.level.LevelManager;

public class AILevel {

	public static boolean xPressed = false;
	public static boolean oPressed = false;
	
	public static int winScore = 0;
	public static int loseScore = 0;

	private LevelManager level;
	private AI ai;
	
	public UIButton xRadiobtn, oRadiobtn, multiPlayerbtn;
	public UILabel player1Score;
	
	public AILevel() {
		level = Game.getLevel();
		
		ai = new AI();
		Game.getLevel().vsPc = true;
		
		for (int i = 0; i < level.canvas.columns.size(); i++) {
			level.canvas.columns.get(i).pc = true;
		}

		// UI clear
		level.panel.components.remove(level.vs);
		level.panel.components.remove(level.player2Namelbl);
		level.panel.components.remove(level.player2Win);
		level.panel.components.remove(level.player2Hint);
		level.panel.components.remove(level.buttonComputer.label);
		level.panel.components.remove(level.buttonComputer);
		level.panel.components.remove(level.player1Win);
		if (!level.panel.components.contains(level.player1Hint))
			level.panel.addComponent(level.player1Hint);
		level.panel.components.remove(level.player1Hintlbl);
		if (level.player2Hint != null)
			level.panel.components.remove(level.player2Hint);
		if (level.player2Hintlbl != null)
			level.panel.components.remove(level.player2Hintlbl);

		// UI set
		level.player1Namelbl.setText("player1");
		player1Score = new UILabel(new Vector2i(10, 60));
		player1Score.setFont(new Font("Verdana", 0, 20));
		player1Score.setColor(0);
		player1Score.setText("player1's score is : " + winScore + " | " + loseScore);
		level.panel.addComponent(player1Score);

		// UI add
		multiPlayerbtn = new UIButton(new Vector2i(17, 520), new Vector2i(135, 30), new UIActionListener() {
			public void perform() {
				new Game();
				level.vsPc = false;
				level.warning.setText("multi player game started");
			}
		});
		multiPlayerbtn.setText("Multi player");
		level.panel.addComponent(multiPlayerbtn);

		xRadiobtn = new UIButton(new Vector2i(100, 180), new Vector2i(30, 30), new UIActionListener() {
			public void perform() {
				level.warning.setText("click on an column to add X");
				System.out.println("o : " + oPressed);
				System.out.println("x : " + xPressed);
			}
		});
		xRadiobtn.setButtonListener(new UIButtonListener() {
			public void pressed(UIButton button) {
				button.setColor(0xaa2222);
				oPressed = false;
				xPressed = true;
				while(level.panel.components.contains(oRadiobtn) || level.panel.components.contains(oRadiobtn.label)) {
					level.panel.components.remove(oRadiobtn);
					level.panel.components.remove(oRadiobtn.label);
				}
			}

			public void released(UIButton button) {
				if (xPressed)
					xRadiobtn.setColor(0xaa2222);
				else
					xRadiobtn.setColor(0x555555);
			}

			public void exited(UIButton button) {
				if (xPressed)
					xRadiobtn.setColor(0xaa2222);
				else
					xRadiobtn.setColor(0xaaaaaa);
			}

			public void entered(UIButton button) {
				if (xPressed)
					xRadiobtn.setColor(0xaa2222);
				else
					xRadiobtn.setColor(0x555555);
			}
		});
		xRadiobtn.setText("X");
		if(!level.panel.components.contains(xRadiobtn))
			level.panel.addComponent(xRadiobtn);

		oRadiobtn = new UIButton(new Vector2i(160, 180), new Vector2i(30, 30), new UIActionListener() {
			public void perform() {
				level.warning.setText("click on an column to add O");
				
			}
		});
		oRadiobtn.setButtonListener(new UIButtonListener() {
			public void pressed(UIButton button) {
				button.setColor(0xaa2222);
				oPressed = true;
				xPressed = false;
				while(level.panel.components.contains(xRadiobtn) || level.panel.components.contains(xRadiobtn.label)) {
					level.panel.components.remove(xRadiobtn);
					level.panel.components.remove(xRadiobtn.label);
				}
			}

			public void released(UIButton button) {
				if (oPressed)
					oRadiobtn.setColor(0xaa2222);
				else
					oRadiobtn.setColor(0x555555);
			}

			public void exited(UIButton button) {
				if (oPressed)
					oRadiobtn.setColor(0xaa2222);
				else
					oRadiobtn.setColor(0xaaaaaa);
			}

			public void entered(UIButton button) {
				if (oPressed)
					oRadiobtn.setColor(0xaa2222);
				else
					oRadiobtn.setColor(0x555555);
			}
		});
		oRadiobtn.setText("O");
		if(!level.panel.components.contains(oRadiobtn))
			level.panel.addComponent(oRadiobtn);
	}

	public void update() {
		if (!level.win) {
			if (xPressed) {
				if (PlayingCanvas.columnClicks % 2 == 0)
					ai.PlayerPlay();
				else 
					ai.pcPlay();
			} else if (oPressed) {
				if (PlayingCanvas.columnClicks % 2 != 0)
					ai.PlayerPlay();
				else 
					ai.pcPlay();
			}
		}
	}
}
