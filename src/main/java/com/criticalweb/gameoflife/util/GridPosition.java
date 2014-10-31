package com.criticalweb.gameoflife.util;

/**
 * Created by flevesque on 29/10/14.
 */
public class GridPosition {

	private int x;
	private int y;

	public GridPosition(final int x, final int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(GridPosition pos) {
		return this.x == pos.getX() && this.y == pos.getY();
	}

	public String toString() {
		return "X: " + x + " | Y: " + y;
	}

}
