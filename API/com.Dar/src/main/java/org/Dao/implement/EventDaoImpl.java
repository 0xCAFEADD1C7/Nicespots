package org.Dao.implement;

import java.util.List;

import org.Dao.DaoImpl;
import org.Dao.interfaces.EventDao;
import org.Entite.Event;
import org.Entite.User;

public class EventDaoImpl extends DaoImpl<Event> implements EventDao {

	public List<Event> getEventsByUser(User user) {
		return getAllBy("user", user.getIdUser());
	}

}
