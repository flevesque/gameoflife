package com.criticalweb.gameoflife.util;

import java.util.List;

/**
 * Created by flevesque on 28/10/14.
 */
public interface GridNeighborAware<T> extends List<T> {

	public T getAtPosition(int x, int y);

	public T getAtPosition(GridPosition pos);

	public List<T> getNeighbors(int index);

	public GridPosition getGridPosition(int index);

	public int getIndexAtPosition(GridPosition pos);

}
