package org.Dao.implement;

import java.util.List;

import org.Dao.DaoImpl;
import org.Dao.interfaces.SpotReviewDao;
import org.Entite.Spot;
import org.Entite.SpotReview;

public class SpotReviewDaoImpl extends DaoImpl<SpotReview> implements SpotReviewDao {
	
	public SpotReviewDaoImpl () {
        super();
        
        this.klass = SpotReview.class;
        this.klassName = "Comment";
    }

	public List<SpotReview> getSpotsReviewBySpot(Spot spot) {
		return getAllBy("spot", spot.getIdSpot());
	}

}
