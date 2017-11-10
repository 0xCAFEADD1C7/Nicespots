package org.Dao.interfaces;

import java.util.List;

import org.Dao.GenericDao;
import org.Entite.Spot;
import org.Entite.SpotReview;

public interface SpotReviewDao extends GenericDao<SpotReview> {
	
	public List<SpotReview> getSpotsReviewBySpot(Spot spot);

}
