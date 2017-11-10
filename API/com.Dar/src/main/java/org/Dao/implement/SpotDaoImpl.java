package org.Dao.implement;

import java.util.List;

import org.Dao.DaoImpl;
import org.Dao.interfaces.SpotDao;
import org.Entite.Spot;
import org.Entite.User;

public class SpotDaoImpl extends DaoImpl<Spot> implements SpotDao {

	public List<Spot> getSpotsByUser(User user) {
		return getAllBy("user", user.getIdUser());
	}
}
