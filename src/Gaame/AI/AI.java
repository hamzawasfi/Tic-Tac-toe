package Gaame.AI;

import java.util.Random;

import Gaame.Game;
import Gaame.graphics.Canvas.PlayingCanvas;
import Gaame.graphics.level.LevelManager;

public class AI {

	private LevelManager level;
	private Random random;
	
	public boolean hint = false;
	public boolean oPressed = false;
	public boolean xPressed = false;
	
	public AI() {
		random = new Random();
	}
	
	public void PlayerPlay() {
		level = Game.getLevel();

		for (int i = 0; i < level.canvas.columns.size(); i++) {
			level.canvas.columns.get(i).pc = false;
		}
	}

	public void pcPlay() {
		level = Game.getLevel();
			
		if(!hint) {
			for (int i = 0; i < level.canvas.columns.size(); i++) {
				level.canvas.columns.get(i).pc = true;
			}
		}
		
		if (PlayingCanvas.columnClicks == 0) {
			int randomColumn = random.nextInt(9);
			level.canvas.columns.get(randomColumn).columnListener.action(level.canvas.columns.get(randomColumn));
		} else if (PlayingCanvas.columnClicks == 1 || PlayingCanvas.columnClicks == 2) {
			while (true) {
				int randomColumn = random.nextInt(9);
				if (!level.canvas.columns.get(randomColumn).clicked && !level.win) {
					level.canvas.columns.get(randomColumn).columnListener.action(level.canvas.columns.get(randomColumn));
					break;
				}
			}
		} else if (PlayingCanvas.columnClicks > 2 && PlayingCanvas.columnClicks <= 4) {
			pcWin();
		} else if (PlayingCanvas.columnClicks > 4 && PlayingCanvas.columnClicks < 9) {
			level.checkWin();
			if (!level.win) {
				pcWin();
			}
		} else if (PlayingCanvas.columnClicks >= 9 && level.win) {
			if (!level.win)
				level.canvas.clearCanvas();
		}
	}

	public void pcWin() {
		boolean case1 = (oPressed && level.canvas.columns.get(0).XO == "X") || (xPressed && level.canvas.columns.get(0).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(0).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(0).XO == "X");
		boolean case2 = (oPressed && level.canvas.columns.get(1).XO == "X") || (xPressed && level.canvas.columns.get(1).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0  && level.canvas.columns.get(1).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(1).XO == "O");
		boolean case3 = (oPressed && level.canvas.columns.get(2).XO == "X") || (xPressed && level.canvas.columns.get(2).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0  && level.canvas.columns.get(2).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(2).XO == "O");
		boolean case4 = (xPressed && level.canvas.columns.get(3).XO == "O") || (oPressed && level.canvas.columns.get(3).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(3).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(3).XO == "X");
		boolean case5 = (xPressed && level.canvas.columns.get(4).XO == "O") || (oPressed && level.canvas.columns.get(4).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(4).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(4).XO == "X");
		boolean case6 = (xPressed && level.canvas.columns.get(5).XO == "O") || (oPressed && level.canvas.columns.get(5).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(5).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(5).XO == "X");
		boolean case7 = (xPressed && level.canvas.columns.get(6).XO == "O") || (oPressed && level.canvas.columns.get(6).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(6).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(6).XO == "X");
		boolean case8 = (xPressed && level.canvas.columns.get(7).XO == "O") || (oPressed && level.canvas.columns.get(7).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(7).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(7).XO == "X");
		boolean case9 = (oPressed && level.canvas.columns.get(0).XO == "O") || (xPressed && level.canvas.columns.get(0).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(0).XO == "O") ||  (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(0).XO == "X");
		boolean case10 = (oPressed && level.canvas.columns.get(1).XO == "O") || (xPressed && level.canvas.columns.get(1).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(1).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(1).XO == "X");
		boolean case11 = (oPressed && level.canvas.columns.get(2).XO == "O") || (xPressed && level.canvas.columns.get(2).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(2).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(2).XO == "X");
		boolean case12 = (xPressed && level.canvas.columns.get(3).XO == "X") || (oPressed && level.canvas.columns.get(3).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(3).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(3).XO == "O");
		boolean case13 = (xPressed && level.canvas.columns.get(4).XO == "X") || (oPressed && level.canvas.columns.get(4).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(4).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(4).XO == "O");
		boolean case14 = (xPressed && level.canvas.columns.get(5).XO == "X") || (oPressed && level.canvas.columns.get(5).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(5).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(5).XO == "O");
		boolean case15 = (xPressed && level.canvas.columns.get(6).XO == "X") || (oPressed && level.canvas.columns.get(6).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(6).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(6).XO == "O");
		boolean case16 = (xPressed && level.canvas.columns.get(7).XO == "X") || (oPressed && level.canvas.columns.get(7).XO == "O") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(7).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(7).XO == "O");
		
		if (!level.win) {
			if (level.canvas.columns.get(0).XO == level.canvas.columns.get(1).XO && !level.canvas.columns.get(2).clicked && case1) {
					level.canvas.columns.get(2).columnListener.action(level.canvas.columns.get(2));
					System.out.println("defend1");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(3).XO && !level.canvas.columns.get(6).clicked && case1) { 
					level.canvas.columns.get(6).columnListener.action(level.canvas.columns.get(6));
					System.out.println("defend2");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(8).clicked && case1) {
					level.canvas.columns.get(8).columnListener.action(level.canvas.columns.get(8));
					System.out.println("defend3");
			} else if (level.canvas.columns.get(1).XO == level.canvas.columns.get(2).XO && !level.canvas.columns.get(0).clicked && case2) {
					level.canvas.columns.get(0).columnListener.action(level.canvas.columns.get(0));
					System.out.println("defend4");
			} else if (level.canvas.columns.get(1).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(7).clicked && case2) {
					level.canvas.columns.get(7).columnListener.action(level.canvas.columns.get(7));
					System.out.println("defend5");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(5).XO && !level.canvas.columns.get(8).clicked && case3) {
					level.canvas.columns.get(8).columnListener.action(level.canvas.columns.get(8));
					System.out.println("defend6");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(6).clicked && case3) {
					level.canvas.columns.get(6).columnListener.action(level.canvas.columns.get(6));
					System.out.println("defend7");
			} else if (level.canvas.columns.get(3).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(6).clicked && case4) {
					level.canvas.columns.get(5).columnListener.action(level.canvas.columns.get(5));
					System.out.println("defend8");
			} else if (level.canvas.columns.get(3).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(0).clicked && case4) {
					level.canvas.columns.get(0).columnListener.action(level.canvas.columns.get(0));
					System.out.println("defend9");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(5).XO && !level.canvas.columns.get(3).clicked && case5) { 
					level.canvas.columns.get(3).columnListener.action(level.canvas.columns.get(3));
					System.out.println("defend10");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(2).clicked && case5) { 
					level.canvas.columns.get(2).columnListener.action(level.canvas.columns.get(2));
					System.out.println("defend11");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(7).XO && !level.canvas.columns.get(4).clicked && case5) { 
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend12");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(0).clicked && case5) {
					level.canvas.columns.get(0).columnListener.action(level.canvas.columns.get(0));
					System.out.println("defend13");
			} else if (level.canvas.columns.get(5).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(2).clicked && case6) { 
					level.canvas.columns.get(2).columnListener.action(level.canvas.columns.get(2));
					System.out.println("defend14");
			} else if (level.canvas.columns.get(6).XO == level.canvas.columns.get(7).XO && !level.canvas.columns.get(8).clicked && case7) { 
					level.canvas.columns.get(8).columnListener.action(level.canvas.columns.get(8));
					System.out.println("defend15");
			} else if (level.canvas.columns.get(7).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(6).clicked && case8) { 
					level.canvas.columns.get(6).columnListener.action(level.canvas.columns.get(6));
					System.out.println("defend16");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(2).XO && !level.canvas.columns.get(1).clicked && case1) { 
					level.canvas.columns.get(1).columnListener.action(level.canvas.columns.get(1));
					System.out.println("defend17");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(3).clicked && case1) {
					level.canvas.columns.get(3).columnListener.action(level.canvas.columns.get(3));
					System.out.println("defend18");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(4).clicked && case1) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend19");
			} else if (level.canvas.columns.get(1).XO == level.canvas.columns.get(7).XO && !level.canvas.columns.get(4).clicked && case2) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend20");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(4).clicked && case3) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend21");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(5).clicked && case3) {
					level.canvas.columns.get(5).columnListener.action(level.canvas.columns.get(5));
					System.out.println("defend22");
			} else if (level.canvas.columns.get(3).XO == level.canvas.columns.get(5).XO && !level.canvas.columns.get(4).clicked && case4) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend23");
			} else if (level.canvas.columns.get(6).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(7).clicked && case7) {
					level.canvas.columns.get(7).columnListener.action(level.canvas.columns.get(7));
					System.out.println("defend24");
			}else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(1).XO && !level.canvas.columns.get(2).clicked && case9) {
					level.canvas.columns.get(2).columnListener.action(level.canvas.columns.get(2));
					System.out.println("defend25");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(3).XO && !level.canvas.columns.get(6).clicked && case9) { 
					level.canvas.columns.get(6).columnListener.action(level.canvas.columns.get(6));
					System.out.println("defend26");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(8).clicked && case9) {
					level.canvas.columns.get(8).columnListener.action(level.canvas.columns.get(8));
					System.out.println("defend27");
			} else if (level.canvas.columns.get(1).XO == level.canvas.columns.get(2).XO && !level.canvas.columns.get(0).clicked && case10) {
					level.canvas.columns.get(0).columnListener.action(level.canvas.columns.get(0));
					System.out.println("defend28");
			} else if (level.canvas.columns.get(1).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(7).clicked && case10) {
					level.canvas.columns.get(7).columnListener.action(level.canvas.columns.get(7));
					System.out.println("defend29");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(5).XO && !level.canvas.columns.get(8).clicked && case11) {
					level.canvas.columns.get(8).columnListener.action(level.canvas.columns.get(8));
					System.out.println("defend30");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(6).clicked && case11) {
					level.canvas.columns.get(6).columnListener.action(level.canvas.columns.get(6));
					System.out.println("defend31");
			} else if (level.canvas.columns.get(3).XO == level.canvas.columns.get(4).XO && !level.canvas.columns.get(6).clicked && case12) {
					level.canvas.columns.get(5).columnListener.action(level.canvas.columns.get(5));
					System.out.println("defend32");
			} else if (level.canvas.columns.get(3).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(0).clicked && case12) {
					level.canvas.columns.get(0).columnListener.action(level.canvas.columns.get(0));
					System.out.println("defend33");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(5).XO && !level.canvas.columns.get(3).clicked && case13) { 
					level.canvas.columns.get(3).columnListener.action(level.canvas.columns.get(3));
					System.out.println("defend34");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(2).clicked && case13) { 
					level.canvas.columns.get(2).columnListener.action(level.canvas.columns.get(2));
					System.out.println("defend35");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(7).XO && !level.canvas.columns.get(4).clicked && case13) { 
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend36");
			} else if (level.canvas.columns.get(4).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(0).clicked && case13) {
					level.canvas.columns.get(0).columnListener.action(level.canvas.columns.get(0));
					System.out.println("defend37");
			} else if (level.canvas.columns.get(5).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(2).clicked && case14) { 
					level.canvas.columns.get(2).columnListener.action(level.canvas.columns.get(2));
					System.out.println("defend38");
			} else if (level.canvas.columns.get(6).XO == level.canvas.columns.get(7).XO && !level.canvas.columns.get(8).clicked && case15) { 
					level.canvas.columns.get(8).columnListener.action(level.canvas.columns.get(8));
					System.out.println("defend39");
			} else if (level.canvas.columns.get(7).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(6).clicked && case16) { 
					level.canvas.columns.get(6).columnListener.action(level.canvas.columns.get(6));
					System.out.println("defend40");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(2).XO && !level.canvas.columns.get(1).clicked && case9) { 
					level.canvas.columns.get(1).columnListener.action(level.canvas.columns.get(1));
					System.out.println("defend41");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(3).clicked && case9) {
					level.canvas.columns.get(3).columnListener.action(level.canvas.columns.get(3));
					System.out.println("defend42");
			} else if (level.canvas.columns.get(0).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(4).clicked && case9) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend43");
			} else if (level.canvas.columns.get(1).XO == level.canvas.columns.get(7).XO && !level.canvas.columns.get(4).clicked && case10) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend44");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(6).XO && !level.canvas.columns.get(4).clicked && case11) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend45");
			} else if (level.canvas.columns.get(2).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(5).clicked && case11) {
					level.canvas.columns.get(5).columnListener.action(level.canvas.columns.get(5));
					System.out.println("defend46");
			} else if (level.canvas.columns.get(3).XO == level.canvas.columns.get(5).XO && !level.canvas.columns.get(4).clicked && case12) {
					level.canvas.columns.get(4).columnListener.action(level.canvas.columns.get(4));
					System.out.println("defend47");
			} else if (level.canvas.columns.get(6).XO == level.canvas.columns.get(8).XO && !level.canvas.columns.get(7).clicked && case15) {
					level.canvas.columns.get(7).columnListener.action(level.canvas.columns.get(7));
					System.out.println("defend48");
			}else {
				attack();
			}
		}
	}

	public void attack() {
		int[] options = new int[3];

		boolean case1 = (xPressed && level.canvas.columns.get(0).XO == "O") || (oPressed && level.canvas.columns.get(0).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(0).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(0).XO == "X");
		boolean case2 = (xPressed && level.canvas.columns.get(1).XO == "O") || (oPressed && level.canvas.columns.get(1).XO == "X") || (PlayingCanvas.columnClicks % 2 != 0  && level.canvas.columns.get(1).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(1).XO == "O");
		boolean case3 = (oPressed && level.canvas.columns.get(2).XO == "X") || (xPressed && level.canvas.columns.get(2).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0  && level.canvas.columns.get(2).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(2).XO == "O");
		boolean case4 = (xPressed && level.canvas.columns.get(3).XO == "O") || (oPressed && level.canvas.columns.get(3).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(3).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(3).XO == "X");
		boolean case5 = (xPressed && level.canvas.columns.get(5).XO == "O") || (oPressed && level.canvas.columns.get(5).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(5).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(5).XO == "X");
		boolean case6 = (xPressed && level.canvas.columns.get(6).XO == "O") || (oPressed && level.canvas.columns.get(6).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(6).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(6).XO == "X");
		boolean case7 = (xPressed && level.canvas.columns.get(7).XO == "O") || (oPressed && level.canvas.columns.get(7).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(7).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(7).XO == "X");
		boolean case8 = (xPressed && level.canvas.columns.get(8).XO == "O") || (oPressed && level.canvas.columns.get(8).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(8).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(8).XO == "X");;
		boolean case17 = (xPressed && level.canvas.columns.get(4).XO == "O") || (oPressed && level.canvas.columns.get(4).XO == "X") || (PlayingCanvas.columnClicks % 2 == 0 && level.canvas.columns.get(4).XO == "O") || (PlayingCanvas.columnClicks % 2 != 0 && level.canvas.columns.get(4).XO == "X");
		boolean case9 = (!level.canvas.columns.get(1).clicked) || (!level.canvas.columns.get(3).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case10 = (!level.canvas.columns.get(0).clicked) || (!level.canvas.columns.get(2).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case11 = (!level.canvas.columns.get(1).clicked) || (!level.canvas.columns.get(5).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case12 = (!level.canvas.columns.get(0).clicked) || (!level.canvas.columns.get(6).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case13 = (!level.canvas.columns.get(2).clicked) || (!level.canvas.columns.get(8).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case14 = (!level.canvas.columns.get(3).clicked) || (!level.canvas.columns.get(7).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case15 = (!level.canvas.columns.get(6).clicked) || (!level.canvas.columns.get(8).clicked) || (!level.canvas.columns.get(4).clicked);
		boolean case16 = (!level.canvas.columns.get(5).clicked) || (!level.canvas.columns.get(7).clicked) || (!level.canvas.columns.get(4).clicked);
		
		if(level.canvas.columns.get(0).clicked && case1 && case9) {
				 options[0] = 1;
				 options[1] = 3;
				 options[2] = 4;
				 if(!level.canvas.columns.get(options[0]).clicked)
					 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
				 else if(!level.canvas.columns.get(options[1]).clicked)
					 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
				 else if(!level.canvas.columns.get(options[2]).clicked)
					 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
				 else
					 System.out.println("bug");
		}else if(level.canvas.columns.get(1).clicked && case2 && case10) {
					options[0] = 0;
					options[1] = 2;
					options[2] = 4;
					if(!level.canvas.columns.get(options[0]).clicked)
						 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
					 else if(!level.canvas.columns.get(options[1]).clicked)
						 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
					 else if(!level.canvas.columns.get(options[2]).clicked)
						 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
					 else
						 System.out.println("bug");
		}else if(level.canvas.columns.get(2).clicked && case3 && case11) {
					options[0] = 1;
					options[1] = 4;
					options[2] = 5;
					if(!level.canvas.columns.get(options[0]).clicked)
						 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
					 else if(!level.canvas.columns.get(options[1]).clicked)
						 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
					 else if(!level.canvas.columns.get(options[2]).clicked)
						 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
					 else
						 System.out.println("bug");
		}else if(level.canvas.columns.get(3).clicked && case4 && case12) {
					options[0] = 0;
					options[1] = 4;
					options[2] = 6;
					if(!level.canvas.columns.get(options[0]).clicked)
						 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
					 else if(!level.canvas.columns.get(options[1]).clicked)
						 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
					 else if(!level.canvas.columns.get(options[2]).clicked)
						 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
					 else
						 System.out.println("bug");
		}else if(level.canvas.columns.get(4).clicked && case17) {
					while (true) {
						System.out.println("looping5");
						int randomColumn = random.nextInt(9);
						if (!level.canvas.columns.get(randomColumn).clicked && !level.win) {
							level.canvas.columns.get(randomColumn).columnListener.action(level.canvas.columns.get(randomColumn));
							break;
						}
					}
		}else if(level.canvas.columns.get(5).clicked && case5 && case13) {
					options[0] = 2;
					options[1] = 4;
					options[2] = 8;
					if(!level.canvas.columns.get(options[0]).clicked)
						 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
					 else if(!level.canvas.columns.get(options[1]).clicked)
						 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
					 else if(!level.canvas.columns.get(options[2]).clicked)
						 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
					 else
						 System.out.println("bug");
		}else if(level.canvas.columns.get(6).clicked && case6 && case14) {
					options[0] = 3;
					options[1] = 4;
					options[2] = 7;
					if(!level.canvas.columns.get(options[0]).clicked)
						 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
					 else if(!level.canvas.columns.get(options[1]).clicked)
						 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
					 else if(!level.canvas.columns.get(options[2]).clicked)
						 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
					 else
						 System.out.println("bug");
		}else if(level.canvas.columns.get(7).clicked && case7 && case15) {
				options[0] = 4;
				options[1] = 6;
				options[2] = 8;
				if(!level.canvas.columns.get(options[0]).clicked)
					 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
				 else if(!level.canvas.columns.get(options[1]).clicked)
					 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
				 else if(!level.canvas.columns.get(options[2]).clicked)
					 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
				 else
					 System.out.println("bug");
		}else if(level.canvas.columns.get(8).clicked && case8 && case16) {
				options[0] = 4;
				options[1] = 5;
				options[2] = 7;
				if(!level.canvas.columns.get(options[0]).clicked)
					 level.canvas.columns.get(options[0]).columnListener.action(level.canvas.columns.get(options[0]));
				 else if(!level.canvas.columns.get(options[1]).clicked)
					 level.canvas.columns.get(options[1]).columnListener.action(level.canvas.columns.get(options[1]));
				 else if(!level.canvas.columns.get(options[2]).clicked)
					 level.canvas.columns.get(options[2]).columnListener.action(level.canvas.columns.get(options[2]));
				 else
					 System.out.println("bug");
		}else {
			System.out.print("another case");
			while (true) {
				System.out.println("loopingcase");
				int randomColumn = random.nextInt(9);
				if (!level.canvas.columns.get(randomColumn).clicked && !level.win) {
					level.canvas.columns.get(randomColumn).columnListener.action(level.canvas.columns.get(randomColumn));
					break;
				}
			}
		}
	}
}
