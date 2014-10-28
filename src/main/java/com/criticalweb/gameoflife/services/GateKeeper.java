package com.criticalweb.gameoflife.services;

import com.criticalweb.gameoflife.Constants;
import com.criticalweb.gameoflife.objects.Cell;

/**
 * Created by flevesque on 27/10/14.
 */
public interface GateKeeper {

	public Constants.STATE determineFate(Cell cell);

}
