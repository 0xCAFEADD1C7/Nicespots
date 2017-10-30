package org.Dao;

import java.util.List;

import org.Entite.Spot;

public interface SpotDao {

	
	public void addSpot(Spot spot);
	public Spot getSpot(int id);
	public List<Spot> getAllSpot();
	
}
