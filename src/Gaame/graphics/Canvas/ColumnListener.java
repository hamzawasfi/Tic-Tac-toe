package Gaame.graphics.Canvas;

import Gaame.Game;

public class ColumnListener {

	public void entered(Column column) {
		if (!column.clicked) {
			if (PlayingCanvas.columnClicks % 2 == 0)
				Game.getLevel().warning.setText("press to add X");
			else
				Game.getLevel().warning.setText("press to add O");
		} else
			Game.getLevel().warning.setText("you can't change this column");
		column.setColor(0xeeeeee);
	}

	public void exited(Column column) {
		Game.getLevel().warning.setText("you can't change this column");
		if (!Game.getLevel().win)
			column.setColor(0xffffff);
		else
			column.setColor(0);
	}

	public void pressed(Column column) {
		column.XO = " ";
		column.setChar(column.XO);
		column.setColor(0xcccccc);
		column.clicked = true;
	}

	public void released(Column column) {
		if (PlayingCanvas.columnClicks > 4) {
			Game.getLevel().checkWin();
		}
		if (PlayingCanvas.columnClicks > 8) {
			if (!Game.getLevel().win)
				column.clearCanvas();
		}
		if (PlayingCanvas.columnClicks > 4) {
			Game.getLevel().checkWin();
		}
		if (PlayingCanvas.columnClicks > 8) {
			if (!Game.getLevel().win)
				Game.getLevel().canvas.clearCanvas();
		}
		if (!column.pc)
			column.setColor(0xeeeeee);
	}

	public void action(Column column) {
		if (!Game.getLevel().win) {
			if(!Game.getLevel().ai.hint) {
			entered(column);
			pressed(column);
			released(column);
			exited(column);
			}else {
				column.setColor(0xffff00);
				Game.getLevel().ai.hint = false;
			}
		}
	}
}
