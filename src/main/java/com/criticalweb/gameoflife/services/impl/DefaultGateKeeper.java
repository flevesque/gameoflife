package com.criticalweb.gameoflife.services.impl;

import com.criticalweb.gameoflife.Constants;
import com.criticalweb.gameoflife.objects.Cell;
import com.criticalweb.gameoflife.services.GateKeeper;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by flevesque on 27/10/14.
 */
@Component
public class DefaultGateKeeper implements GateKeeper {

	@Override
	public Constants.STATE determineFate(Cell cell) {

		Collection<Cell> neighbors = cell.getNeighbors();

		short numAlive = 0;
		for (Cell c : neighbors) {
			if (Constants.STATE.ALIVE.equals(c.getOriginalState())) {
				numAlive++;
			}
		}

		if (Constants.STATE.ALIVE.equals(cell.getState()) && (numAlive == 2 || numAlive == 3) ) {
			return Constants.STATE.ALIVE;
		}

		if (Constants.STATE.DEAD.equals(cell.getState()) && (numAlive == 3)) {
			return Constants.STATE.ALIVE;
		}

		return Constants.STATE.DEAD;

	}

}
