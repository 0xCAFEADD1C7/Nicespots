package org.Servlets;

import org.Dao.interfaces.SpotReviewDao;
import org.Entite.SpotReview;
import org.utils.DAOFactory;

public class SpotReviewServlet extends SimpleAbstractServlet<SpotReview> {
	private static final long serialVersionUID = 2L;
       
    public SpotReviewServlet() {
        super();
    }

	@Override
	protected SpotReviewDao getDAO() {
		return DAOFactory.getSpotReview();
	}
}
