package org.Dao;

import java.util.List;

import org.Entite.AvisSpot;
import org.Entite.Spot;
import org.Entite.User;
import org.hibernate.Query;
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Spot");
		List<Spot> list = query.list(); // List of users
		session.close();
		return list;
	}
	
	public List<AvisSpot> getAvisSpot(Spot spot){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from avisSpot where spot = :spotId");
		query.setParameter("spotId",spot.getIdSpot());
		List<AvisSpot> list = query.list(); // List of users
		session.close();
		return list;
	}

}
