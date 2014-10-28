package com.criticalweb.gameoflife.objects;

import com.criticalweb.gameoflife.services.GateKeeper;

import javax.annotation.Resource;
import java.util.Collection;

import static com.criticalweb.gameoflife.Constants.STATE;

/**
 * Created by flevesque on 27/10/14.
 */
public class Cell {

    private int x;
    private int y;

	private Collection<Cell> neighbors;

	private STATE state;
	private STATE originalState;

	public Cell() {
		setState(STATE.DEAD);
		setOriginalState(STATE.DEAD);
	}

	public Cell(int x, int y) {
		this();
		setX(x);
		setY(y);
	}

	public Cell(int x, int y, STATE state) {
		this(x, y);
		setState(state);
		setOriginalState(state);
	}

	public void update() {
		setOriginalState(getState());
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

	public Collection<Cell> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(Collection<Cell> neighbors) {
		this.neighbors = neighbors;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public STATE getOriginalState() {
		return originalState;
	}

	public void setOriginalState(STATE originalState) {
		this.originalState = originalState;
	}
}
