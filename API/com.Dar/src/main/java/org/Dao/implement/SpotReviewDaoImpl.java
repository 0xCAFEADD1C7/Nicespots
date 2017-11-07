package org.Dao.implement;

import java.util.List;

import org.Dao.DaoImpl;
import org.Dao.interfaces.SpotReviewDao;
import org.Entite.Spot;
import org.Entite.SpotReview;

public class SpotReviewDaoImpl extends DaoImpl<SpotReview> implements SpotReviewDao {

	public List<SpotReview> getSpotsReviewBySpot(Spot spot) {
		return getAllBy("spot", spot.getIdSpot());
	}

}
