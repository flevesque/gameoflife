package com.criticalweb.gameoflife;

import com.criticalweb.gameoflife.objects.Cell;
import com.criticalweb.gameoflife.services.GateKeeper;
import com.criticalweb.gameoflife.services.impl.DefaultGateKeeper;
import com.criticalweb.gameoflife.util.GridNeighborAware;
import com.criticalweb.gameoflife.util.GridNeighborAwareList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by flevesque on 27/10/14.
 */
public class ConsoleApp {

	static GateKeeper gateKeeper = new DefaultGateKeeper();

	private static Logger LOG = LoggerFactory.getLogger(ConsoleApp.class);

	private static int COLUMNS = 100;
	private static int ROWS = 100;
	private static int TICKS = 10;

    public static void main(String[] args) {

		GridNeighborAware<Cell> grid = new GridNeighborAwareList<>(ROWS, COLUMNS);
		for (int x=0; x<COLUMNS; x++) {
			for (int y=0; y<ROWS; y++) {
				grid.add(new Cell(x, y, Constants.STATE.DEAD));
			}
		}

		grid.getAtPosition(2, 1).setState(Constants.STATE.ALIVE);
		grid.getAtPosition(2, 2).setState(Constants.STATE.ALIVE);
		grid.getAtPosition(2, 3).setState(Constants.STATE.ALIVE);

		for (int i=0; i<grid.size(); i++) {
			grid.get(i).setNeighbors(grid.getNeighbors(i));
			grid.get(i).update();
		}

		draw(grid);

		for (int i=0; i<TICKS; i++) {
			tick(grid);
			draw(grid);

		}

    }

	private static void tick(GridNeighborAware<Cell> grid) {
		for (int x=0; x<COLUMNS; x++) {
			for (int y=0; y<ROWS; y++) {
				Cell cell = grid.getAtPosition(x, y);
				cell.setState(gateKeeper.determineFate(cell));
			}
		}

		for (int x=0; x<COLUMNS; x++) {
			for (int y=0; y<ROWS; y++) {
				grid.getAtPosition(x, y).update();
			}
		}
	}

	private static void draw(GridNeighborAware<Cell> grid) {

		LOG.debug("##########");
		for (int x=0; x<COLUMNS; x++) {
			StringBuffer sb = new StringBuffer();
			for (int y=0; y<ROWS; y++) {
				Cell cell = grid.getAtPosition(x, y);
				if (Constants.STATE.ALIVE.equals(cell.getState())) {
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
