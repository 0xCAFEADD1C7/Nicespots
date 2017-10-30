package org.Dao;

import java.util.List;

import org.Entite.Spot;
import org.hibernate.Session;

import util.HibernateUtil;

public class SpotDaoImpl extends DaoImpl implements SpotDao{

	public void addSpot(Spot spot) {
		super.add(spot);
		
	}

	public Spot getSpot(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Spot spot =session.get(Spot.class,id);
		session.close();
		return spot;
	}

	public List<Spot> getAllSpot() {
		// TODO Auto-generated method stub
		return null;
	}

}
