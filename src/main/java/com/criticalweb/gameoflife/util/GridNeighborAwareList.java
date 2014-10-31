package com.criticalweb.gameoflife.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by flevesque on 28/10/14.
 */
public class GridNeighborAwareList<T> extends ArrayList<T> implements GridNeighborAware<T> {

	private static final Logger LOG = LoggerFactory.getLogger(GridNeighborAwareList.class);

	private int rows = 0;
	private int columns = 0;

	public GridNeighborAwareList(final int rows, final int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	@Override
	public T getAtPosition(int x, int y) {
		return getAtPosition(new GridPosition(x, y));
	}

	@Override
	public T getAtPosition(GridPosition pos) {
		return this.get(getIndexAtPosition(pos));
	}

	/**
	 *
	 *
	 *		18 = 19 20 21 22 23   24
	 *      == = == == == == == = ==
	 *		24 | 0  1  2  3  4  | 5
	 *		4  | 5  6  7  8  9  | 10
	 *		9  | 10 11 12 13 14 | 15
	 *		14 | 15 16 17 18 19 | 20

		for (int x=0; x<ROWS; x++) {
			for (int y=0; y<COLUMNS; y++) {
				grid[x][y].setNeighbors(getNeighbors(grid, x, y));
			}
		}
	 *		19 | 20 21 22 23 24 | 0
	 *		== = == == == == == = ==
	 *		0    1  2  3  4  5    6
	 * @param index
	 * @return
	 */
	@Override
	public List<T> getNeighbors(final int index) {

		final GridPosition pos = getGridPosition(index);

		final List<T> neighbors = new ArrayList<>();

		LOG.debug("Find neighbors for position [" + index + "]");
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				int xx = x;
				int yy = y;
				if (xx != 0 || yy != 0) {
					LOG.debug("* Looking for neighbor at " + xx + ", " + yy);
					int newX;
					int newY = pos.getY() + yy;

					int _index = index + xx + (columns*yy);
					if (newY < 0) { // top edge
						_index = size() + _index - 1;
					}
					if (newY >= rows) { // bottom edge
						_index = _index - size() + 1;
					}
					if (_index < 0) {
						_index = size() + _index;
					}
					if (_index >= size()) {
						_index = size() - _index;
					}
					GridPosition newPos = getGridPosition(_index);

					LOG.debug("* New position: " + newPos.toString() + " (" + _index + ")");

					neighbors.add(this.get(_index));
				}
			}
		}

		return neighbors;
	}

	@Override
	public GridPosition getGridPosition(final int index) {

		final int x = index % columns;

		final int y = (int) Math.floor(((double) index) / columns);

		return new GridPosition(x, y);

	}

	@Override
	public int getIndexAtPosition(final GridPosition pos) {
		return pos.getY() * columns + pos.getX();
	}

}
