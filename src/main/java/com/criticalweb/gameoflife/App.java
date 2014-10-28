package com.criticalweb.gameoflife;

import com.criticalweb.gameoflife.objects.Cell;
import com.criticalweb.gameoflife.services.GateKeeper;
import com.criticalweb.gameoflife.services.impl.DefaultGateKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by flevesque on 27/10/14.
 */
public class App {

	static GateKeeper gateKeeper = new DefaultGateKeeper();

	private static Logger LOG = LoggerFactory.getLogger(App.class);

	private static int COLUMNS = 10;
	private static int ROWS = 10;
	private static int TICKS = 10;

    public static void main(String[] args) {

		int[][] start = new int[3][];
		start[0] = new int[]{2, 1};
		start[1] = new int[]{2, 2};
		start[2] = new int[]{2, 3};

		Cell[][] grid = new Cell[ROWS][];

		for (int x=0; x<ROWS; x++) {
			grid[x] = new Cell[COLUMNS];
			for (int y=0; y<COLUMNS; y++) {
				grid[x][y] = new Cell(x, y);
			}
		}

		for (int x=0; x < start.length; x++) {
			grid[start[x][0]][start[x][1]].setState(Constants.STATE.ALIVE);
			grid[start[x][0]][start[x][1]].setOriginalState(Constants.STATE.ALIVE);
		}

		for (int x=0; x<ROWS; x++) {
			for (int y=0; y<COLUMNS; y++) {
				grid[x][y].setNeighbors(getNeighbors(grid, x, y));
			}
		}

		draw(grid);

		for (int i=0; i<TICKS; i++) {
			tick(grid);
			draw(grid);

		}

    }

	private static Collection<Cell> getNeighbors(Cell[][] grid, int x, int y) {
		Collection<Cell> neighbors = new ArrayList<>();

		try {
			neighbors.add(getNeighborAtPosition(grid, x - 1, y - 1));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x, y-1));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x+1, y-1));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x-1, y));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x+1, y));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x-1, y+1));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x, y+1));
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			neighbors.add(getNeighborAtPosition(grid, x+1, y+1));
		} catch (ArrayIndexOutOfBoundsException e) {}

		return neighbors;
	}

	private static Cell getNeighborAtPosition(Cell[][] grid, int x, int y) {
		return grid[x][y];
	}

	private static void tick(Cell[][] grid) {
		for (int x=0; x<ROWS; x++) {
			for (int y=0; y<COLUMNS; y++) {
				grid[x][y].setState(gateKeeper.determineFate(grid[x][y]));
			}
		}

		for (int x=0; x<ROWS; x++) {
			for (int y=0; y<COLUMNS; y++) {
				grid[x][y].update();
			}
		}
	}

	private static void draw(Cell[][] grid) {

		LOG.debug("##########");
		for (int x=0; x<ROWS; x++) {
			StringBuffer sb = new StringBuffer();
			for (int y=0; y<COLUMNS; y++) {
				if (Constants.STATE.ALIVE.equals(grid[x][y].getState())) {
					sb.append("X");
				} else {
					sb.append("-");
				}
			}
			LOG.debug(sb.toString());
		}
		LOG.debug("##########\n");

	}

}
