package org.Servlets;

import org.Dao.interfaces.EventDao;
import org.Entite.Event;
import org.utils.DAOFactory;

public class EventServlet extends SimpleAbstractServlet<Event> {
	private static final long serialVersionUID = 2L;
    
    public EventServlet() {
        super();
        klass = Event.class;
    }

	@Override
	protected EventDao getDAO() {
		return DAOFactory.getEvent();
	}
}
