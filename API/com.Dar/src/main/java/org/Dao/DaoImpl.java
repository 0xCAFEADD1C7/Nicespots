package org.Dao;

import org.hibernate.Session;

import util.HibernateUtil;

public class DaoImpl {

	public void add(Object o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
