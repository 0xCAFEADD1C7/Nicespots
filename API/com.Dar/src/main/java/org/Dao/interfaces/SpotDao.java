package org.Dao.interfaces;

import java.util.List;

import org.Dao.GenericDao;
import org.Entite.Spot;
import org.Entite.User;

public interface SpotDao extends GenericDao<Spot> {
	
	public List<Spot> getSpotsByUser(User user);
	
}
