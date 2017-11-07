package org.Dao.interfaces;

import java.util.List;

import org.Dao.GenericDao;
import org.Entite.Event;
import org.Entite.User;

public interface EventDao extends GenericDao<Event> {
	
	public List<Event> getEventsByUser(User user);

}
