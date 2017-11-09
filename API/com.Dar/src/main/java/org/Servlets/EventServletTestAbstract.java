package org.Servlets;

import org.Dao.GenericDao;
import org.Entite.Event;
import org.json.JSONException;
import org.json.JSONObject;
import org.utils.DAOFactory;

public class EventServletTestAbstract extends AbstractServlet<Event> {
	private static final long serialVersionUID = 2L;
    
	//EventServletTestAbstract is equal to EventServlet but use generic code behind, to test
    public EventServletTestAbstract() {
        super();
    }

	@Override
	protected GenericDao get() {
		return DAOFactory.getEvent();
	}

	@Override
	protected Event fromJson(JSONObject body) throws Exception {
		return Event.fromJson(body);
	}

	@Override
	protected String toJson(Event obj) throws JSONException {
		return obj.toJson();
	}

}
