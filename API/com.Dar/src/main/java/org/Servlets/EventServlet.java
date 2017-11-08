package org.Servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.EventDao;
import org.Entite.Event;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;

public class EventServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 2L;
       
    public EventServlet() {
        super();
    }

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		Event event = Event.fromJson(body);
		DAOFactory.getEvent().add(event);
		
		return event.toJson();
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		Event event = DAOFactory.getEvent().getById(uid);
		
		if (event == null) {
			throw new NotFoundException("User not found");
		}
		
		return event.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		List<Event> events = DAOFactory.getEvent().getAll();
		return JSONUtil.ofList(events);
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		Event event = Event.fromJson(body);
		DAOFactory.getEvent().update(event);
		
		return event.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		EventDao event = DAOFactory.getEvent();
		event.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	//TODO already use in UserServlet, do generic function ?
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
}
