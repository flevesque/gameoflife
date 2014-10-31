package com.criticalweb.gameoflife.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by flevesque on 28/10/14.
 */
public class GridNeighborAwareListTest {

	private static final Logger LOG = LoggerFactory.getLogger(GridNeighborAwareListTest.class);

	@Test
	public void testGetNeighbors() {

		GridNeighborAware grid = new GridNeighborAwareList<>(5, 5);

		for (int x=0; x<25; x++) {
			grid.add(x);
		}

		//		18 = 19 20 21 22 23   24
		//      == = == == == == == = ==
		//		24 | 0  1  2  3  4  | 5
		//		4  | 5  6  7  8  9  | 10
		//		9  | 10 11 12 13 14 | 15
		//		14 | 15 16 17 18 19 | 20
		//		19 | 20 21 22 23 24 | 0
		//		== = == == == == == = ==
		//		0    1  2  3  4  5    6

		assertTrue(grid.getNeighbors(3).containsAll(CollectionUtils.arrayToList(new int[]{2, 4, 7, 8, 9, 21, 22, 23})));

		assertTrue(grid.getNeighbors(13).containsAll(CollectionUtils.arrayToList(new int[]{7, 8, 9, 12, 14, 17, 18, 19})));

		LOG.debug(grid.getNeighbors(0).toString());
		assertTrue(grid.getNeighbors(0).containsAll(CollectionUtils.arrayToList(new int[]{18, 19, 20, 24, 1, 4, 5, 6})));

		assertTrue(grid.getNeighbors(4).containsAll(CollectionUtils.arrayToList(new int[]{22, 23, 24, 3, 5, 8, 9, 10})));

		assertTrue(grid.getNeighbors(20).containsAll(CollectionUtils.arrayToList(new int[]{14, 15, 16, 19, 21, 0, 1, 2})));

		assertTrue(grid.getNeighbors(24).containsAll(CollectionUtils.arrayToList(new int[]{18, 19, 20, 23, 0, 4, 5, 6})));

	}

	@Test
	public void testGetGridPosition() {

		GridNeighborAware grid = new GridNeighborAwareList(5, 5);

		for (int x = 0; x < 25; x++) {
			grid.add(x);
		}

		assertTrue(new GridPosition(1, 1).equals(grid.getGridPosition(6)));
		assertTrue(new GridPosition(4, 4).equals(grid.getGridPosition(24)));
		assertTrue(new GridPosition(3, 3).equals(grid.getGridPosition(18)));

	}

	@Test
	public void getIndexFromPosition () {

		GridNeighborAware grid = new GridNeighborAwareList(5, 5);

		for (int x = 0; x < 25; x++) {
			grid.add(x);
		}

		assertEquals(6, grid.getIndexAtPosition(new GridPosition(1, 1)));
		assertEquals(24, grid.getIndexAtPosition(new GridPosition(4, 4)));
		assertEquals(18, grid.getIndexAtPosition(new GridPosition(3, 3)));

	}

}