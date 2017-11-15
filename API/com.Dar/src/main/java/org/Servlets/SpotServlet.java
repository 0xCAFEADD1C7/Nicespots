package org.Servlets;

import org.Dao.GenericDao;
import org.Entite.Spot;
import org.utils.DAOFactory;

public class SpotServlet extends SimpleAbstractServlet<Spot> {
	private static final long serialVersionUID = 2L;
       
    public SpotServlet() {
        super();
        klass = Spot.class;
    }


	@Override
	protected GenericDao<Spot> getDAO() {
		return DAOFactory.getSpot();
	}
}
