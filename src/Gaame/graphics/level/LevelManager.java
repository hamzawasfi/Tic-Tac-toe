package Gaame.graphics.level;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import Gaame.Game;
import Gaame.AI.AI;
import Gaame.AI.AILevel;
import Gaame.Utils.ImageUtils;
import Gaame.Utils.Vector2i;
import Gaame.graphics.Canvas.Column;
import Gaame.graphics.Canvas.PlayingCanvas;
import Gaame.graphics.UI.UIActionListener;
import Gaame.graphics.UI.UIButton;
import Gaame.graphics.UI.UIButtonListener;
import Gaame.graphics.UI.UILabel;
import Gaame.graphics.UI.UIPanel;

public class LevelManager {

	public boolean vsPc = false;
	public boolean win = false;

	public int width, height;
	public int player1Score;
	private int player2Score;

	private String message = "multi player game started";
	private BufferedImage resetImage, resetImageHover, resetImagePressed;
	private BufferedImage hint, hintHover, hintPressed;
	private UIButton clearCanvas;
	private UIButton reset;
	private Random random;

	public UIPanel panel;
	public UIButton player2Hint, buttonComputer, player1Hint;
	public UILabel vs, player2Namelbl, player2Win, player1Namelbl, player1Win, warning, player2Hintlbl, player1Hintlbl;
	
	public PlayingCanvas canvas;
	public AILevel aiLevel;
	public AI ai;

	private List<PlayingCanvas> canvases = new ArrayList<PlayingCanvas>();
	private List<UIPanel> panels = new ArrayList<UIPanel>();

	public LevelManager(int width, int height) {
		this.width = width;
		this.height = height;
		random = new Random();
		ai = new AI();

		canvas = new PlayingCanvas(width - height, 0, height);
		canvas.manageColumns();
		addCanvas(canvas);

		panel = new UIPanel(new Vector2i(5, 5), new Vector2i(width - height - 5, height - 10));
		addPanel(panel);

		// labels :
		player1Hintlbl = new UILabel(new Vector2i(70, 130));
		player1Hintlbl.setFont(new Font("Verdana", 0, 12));
		player1Hintlbl.setColor(0x660000);
		player1Hintlbl.setText("you have used your hint");

		player2Hintlbl = new UILabel(new Vector2i(70, 360));
		player2Hintlbl.setFont(new Font("Verdana", 0, 12));
		player2Hintlbl.setColor(0x660000);
		player2Hintlbl.setText("you have used your hint");

		player1Namelbl = new UILabel(new Vector2i(100, 30));
		player1Namelbl.setFont(new Font("Verdana", 0, 20));
		player1Namelbl.setColor(0x0000ff);
		player1Namelbl.setText("player1 (X)");
		panel.addComponent(player1Namelbl);

		player1Win = new UILabel(new Vector2i(10, 60));
		player1Win.setFont(new Font("Verdana", 0, 20));
		player1Win.setColor(0);
		player1Win.setText("player1's score is : " + player1Score);
		panel.addComponent(player1Win);

		vs = new UILabel(new Vector2i(5, 210));
		vs.setColor(0xaa0000);
		vs.setFont(new Font("Verdana", 0, 30));
		vs.setText("---------VS---------");
		panel.addComponent(vs);

		player2Namelbl = new UILabel(new Vector2i(100, 270));
		player2Namelbl.setFont(new Font("Verdana", 0, 20));
		player2Namelbl.setColor(0x0000ff);
		player2Namelbl.setText("player2 (O)");
		panel.addComponent(player2Namelbl);

		player2Win = new UILabel(new Vector2i(10, 300));
		player2Win.setFont(new Font("Verdana", 0, 20));
		player2Win.setColor(0);
		player2Win.setText("player2's score is : " + player2Score);
		panel.addComponent(player2Win);

		warning = new UILabel(new Vector2i(10, 580));
		warning.setFont(new Font("Verdana", 0, 14));
		warning.setColor(0xff0000);
		warning.setText(message);
		panel.addComponent(warning);
		//

		// buttons
		buttonComputer = new UIButton(new Vector2i(10, 520), new Vector2i(150, 30), new UIActionListener() {
			public void perform() {
				warning.setFont(new Font("Verdana", Font.PLAIN, 12));
				warning.setText("you are playing VS computer choose X or O");
				canvas.clearCanvas();
				vsPc = true;

				aiLevel = new AILevel();
			}
		});
		buttonComputer.setText("VS computer");
		panel.addComponent(buttonComputer);

		clearCanvas = new UIButton(new Vector2i(220, 520), new Vector2i(70, 30), new UIActionListener() {
			public void perform() {
				canvas.clearCanvas();
				warning.setText("last game cleared");
			}
		});
		clearCanvas.setText("Clear");
		panel.addComponent(clearCanvas);

		try {
			resetImage = ImageIO.read(new File("res/Reset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		resetImageHover = ImageUtils.changeBrightness(resetImage, -50);
		resetImagePressed = ImageUtils.changeBrightness(resetImage, 50);
		reset = new UIButton(new Vector2i(167, 515), resetImage, new UIActionListener() {
			public void perform() {
				for (int i = 0; i < canvas.columns.size(); i++) {
					canvas.columns.get(i).setChar("");
				}
				for (int i = 0; i < canvas.columns.size(); i++) {
					canvas.columns.get(i).clicked = false;
				}
				win = false;
				for (int i = 0; i < canvas.columns.size(); i++) {
					canvas.columns.get(i).setColor(0xffffff);
				}
				canvas.clearCanvas();
				player1Score = 0;
				player1Win.setText("player1's score is : " + player1Score);
				player2Score = 0;
				player2Win.setText("player2's score is : " + player2Score);
				if (!panel.components.contains(player1Hint))
					panel.addComponent(player1Hint);
				panel.components.remove(player1Hintlbl);
				if (!vsPc) {
					new Game();
					vsPc = false;
					warning.setText("game reseted press on any column to add X");

				} else {
					warning.setFont(new Font("Verdana", Font.PLAIN, 12));
					warning.setText("you are playing VS computer choose X or O");
					canvas.clearCanvas();
					vsPc = true;
					
					aiLevel = new AILevel();
				}
				warning.setFont(new Font("Verdana", 0, 12));
				warning.setText("game reseted press on any column to add X");
			}
		});
		reset.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.setImage(resetImageHover);
				warning.setText("press to reset the score");
			}

			public void exited(UIButton button) {
				button.setImage(resetImage);
			}

			public void pressed(UIButton button) {
				button.setImage(resetImagePressed);
			}

			public void released(UIButton button) {
				button.setImage(resetImageHover);
			}
		});
		panel.addComponent(reset);

		try {
			hint = ImageIO.read(new File("res/hint.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		hintHover = ImageUtils.changeBrightness(hint, 50);
		hintPressed = ImageUtils.changeBrightness(hint, 100);
		player1Hint = new UIButton(new Vector2i(100, 70), hint, new UIActionListener() {
			public void perform() {
				if(!canvas.columns.get(0).pc && PlayingCanvas.columnClicks % 2 == 0) {
					warning.setText("press on the yellow column");
					panel.components.remove(player1Hint);
					panel.addComponent(player1Hintlbl);
					hint();
				}else
					warning.setText("press your own hint");
			}
		});
		player1Hint.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.setImage(hintHover);
				warning.setText("press for help, note: you can use it once");
			}

			public void exited(UIButton button) {
				button.setImage(hint);
			}

			public void pressed(UIButton button) {
				button.setImage(hintPressed);
			}

			public void released(UIButton button) {
				button.setImage(hintHover);
			}
		});
		panel.addComponent(player1Hint);

		try {
			hint = ImageIO.read(new File("res/hint.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		hintHover = ImageUtils.changeBrightness(hint, 50);
		hintPressed = ImageUtils.changeBrightness(hint, 100);
		player2Hint = new UIButton(new Vector2i(100, 310), hint, new UIActionListener() {
			public void perform() {
				if(!canvas.columns.get(0).pc && PlayingCanvas.columnClicks % 2 != 0) {
					warning.setText("click on the yellow column");
					panel.components.remove(player2Hint);
					panel.addComponent(player2Hintlbl);
					hint();
				}else
					warning.setText("press your own hint");
			}
		});
		player2Hint.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.setImage(hintHover);
				warning.setText("press for help, note: you can use it once");
			}

			public void exited(UIButton button) {
				button.setImage(hint);
			}

			public void pressed(UIButton button) {
				button.setImage(hintPressed);
			}

			public void released(UIButton button) {
				button.setImage(hintHover);
			}
		});
		panel.addComponent(player2Hint);
	}

	public void checkWin() {
		// horizantal
		if (canvas.columns.get(0).XO == canvas.columns.get(1).XO
				&& canvas.columns.get(0).XO == canvas.columns.get(2).XO) {
			if (canvas.columns.get(0).XO == "X")
				win(canvas.columns.get(0), canvas.columns.get(1), canvas.columns.get(2), "X");
			else if (canvas.columns.get(0).XO == "O")
				win(canvas.columns.get(0), canvas.columns.get(1), canvas.columns.get(2), "O");
		}
		if (canvas.columns.get(3).XO == canvas.columns.get(4).XO
				&& canvas.columns.get(3).XO == canvas.columns.get(5).XO) {
			if (canvas.columns.get(3).XO == "X")
				win(canvas.columns.get(3), canvas.columns.get(4), canvas.columns.get(5), "X");
			else if (canvas.columns.get(3).XO == "O")
				win(canvas.columns.get(3), canvas.columns.get(4), canvas.columns.get(5), "O");
		}
		if (canvas.columns.get(6).XO == canvas.columns.get(7).XO
				&& canvas.columns.get(6).XO == canvas.columns.get(8).XO) {
			if (canvas.columns.get(6).XO == "X")
				win(canvas.columns.get(6), canvas.columns.get(7), canvas.columns.get(8), "X");
			else if (canvas.columns.get(6).XO == "O")
				win(canvas.columns.get(6), canvas.columns.get(7), canvas.columns.get(8), "O");
		}

		// vertical
		if (canvas.columns.get(0).XO == canvas.columns.get(3).XO
				&& canvas.columns.get(0).XO == canvas.columns.get(6).XO) {
			if (canvas.columns.get(0).XO == "X")
				win(canvas.columns.get(0), canvas.columns.get(3), canvas.columns.get(6), "X");
			else if (canvas.columns.get(0).XO == "O")
				win(canvas.columns.get(0), canvas.columns.get(3), canvas.columns.get(6), "O");
		}
		if (canvas.columns.get(1).XO == canvas.columns.get(4).XO
				&& canvas.columns.get(1).XO == canvas.columns.get(7).XO) {
			if (canvas.columns.get(1).XO == "X")
				win(canvas.columns.get(1), canvas.columns.get(4), canvas.columns.get(7), "X");
			else if (canvas.columns.get(1).XO == "O")
				win(canvas.columns.get(1), canvas.columns.get(4), canvas.columns.get(7), "O");
		}
		if (canvas.columns.get(2).XO == canvas.columns.get(5).XO
				&& canvas.columns.get(2).XO == canvas.columns.get(8).XO) {
			if (canvas.columns.get(2).XO == "X")
				win(canvas.columns.get(2), canvas.columns.get(5), canvas.columns.get(8), "X");
			else if (canvas.columns.get(2).XO == "O")
				win(canvas.columns.get(2), canvas.columns.get(5), canvas.columns.get(8), "O");
		}

		// angeular
		if (canvas.columns.get(0).XO == canvas.columns.get(4).XO
				&& canvas.columns.get(0).XO == canvas.columns.get(8).XO) {
			if (canvas.columns.get(0).XO == "X")
				win(canvas.columns.get(0), canvas.columns.get(4), canvas.columns.get(8), "X");
			else if (canvas.columns.get(0).XO == "O")
				win(canvas.columns.get(0), canvas.columns.get(4), canvas.columns.get(8), "O");
		}
		if (canvas.columns.get(2).XO == canvas.columns.get(4).XO
				&& canvas.columns.get(2).XO == canvas.columns.get(6).XO) {
			if (canvas.columns.get(2).XO == "X")
				win(canvas.columns.get(2), canvas.columns.get(4), canvas.columns.get(6), "X");
			else if (canvas.columns.get(2).XO == "O")
				win(canvas.columns.get(2), canvas.columns.get(4), canvas.columns.get(6), "O");
		}
	}

	public void win(Column start, Column middle, Column end, String XO) {
		// canvas
		win = true;

		start.setColor(0);
		middle.setColor(0);
		end.setColor(0);

		String text = "";
		int randomNumber = random.nextInt(4);

		if (XO == "X") {
			if (!vsPc || (vsPc && ai.xPressed)) {
				switch (randomNumber) {
				case 0:
					text = "Good!";
					break;
				case 1:
					text = "Excellent!";
					break;
				case 2:
					text = "Great!";
					break;
				case 3:
					text = "Nice job";
					break;
				}
			} else if (vsPc && ai.oPressed) {
				switch (randomNumber) {
				case 0:
					text = "you lost";
					break;
				case 1:
					text = "try again";
					break;
				case 2:
					text = "really bad!";
					break;
				case 3:
					text = "OH NO!";
					break;
				}
			}
		}
		
		if (XO == "O") {
			if (!vsPc || (vsPc && ai.oPressed)) {
				switch (randomNumber) {
				case 0:
					text = "Good!";
					break;
				case 1:
					text = "Excellent!";
					break;
				case 2:
					text = "Great!";
					break;
				case 3:
					text = "Nice job";
					break;
				}
			} else if (vsPc && ai.xPressed) {
				switch (randomNumber) {
				case 0:
					text = "you lost";
					break;
				case 1:
					text = "try again";
					break;
				case 2:
					text = "really bad!";
					break;
				case 3:
					text = "OH NO!";
					break;
				}
			}
		}
		canvas.setText(text);

		// UI
		if (XO == "X") {
			if (!vsPc) {
				player1Score++;
				player1Win.setText("player1's score is : " + player1Score / 2);
			} else if (vsPc) {
				if (ai.xPressed) {
					aiLevel.winScore++;
					aiLevel.player1Score.setText("player1's score is : " + aiLevel.winScore + " | " + aiLevel.loseScore / 2);
				} else if (ai.oPressed) {
					aiLevel.loseScore++;
					aiLevel.player1Score.setText("player1's score is : " + aiLevel.winScore + " | " + aiLevel.loseScore / 2);
				}
			}
		} else if (XO == "O") {
			if (!vsPc) {
				player2Score++;
				player2Win.setText("player2's score is : " + player2Score / 2);
			} else if (vsPc) {
				if (ai.oPressed) {
					aiLevel.winScore++;
					aiLevel.player1Score.setText("player1's score is : " + aiLevel.winScore + " | " + aiLevel.loseScore / 2);
				} else if (ai.xPressed) {
					aiLevel.loseScore++;
					aiLevel.player1Score.setText("player1's score is : " + aiLevel.winScore + " | " + aiLevel.loseScore / 2);
				}
			}
		}
		win = true;

		warning.setText("press (clear) button to start a new round");
	}
	
	public void hint() {
		ai.hint = true;
		ai.pcPlay();
	}

	public void addPanel(UIPanel panel) {
		panels.add(panel);
	}

	public void addCanvas(PlayingCanvas canvas) {
		canvases.add(canvas);
	}

	public void update() {
		for (PlayingCanvas canvas : canvases) {
			canvas.update();
		}

		for (UIPanel panel : panels) {
			panel.update();
		}
		if (aiLevel != null)
			aiLevel.update();
	}

	public void render(Graphics g) {
		for (UIPanel panel : panels) {
			panel.render(g);
		}

		for (PlayingCanvas canvas : canvases) {
			canvas.render(g);
		}
	}
}