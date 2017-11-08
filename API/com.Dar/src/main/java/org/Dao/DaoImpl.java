package org.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;

public abstract class DaoImpl<T> implements GenericDao<T> {

	protected Class<T> klass; 
	protected String klassName;

	/** Returns the Hibernate current session. Used as a shorthand. **/
	public static Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/** Used to generate a query without the type-safety warning **/
	@SuppressWarnings("unchecked")
	public Query<T> query(String q) {
		return getSession().createQuery(q);
	}

	/** Retrieves one object matching the query **/
	//TODO see how to adapt this according to use-cases
	// since query to be created inside a transaction
	//	public T getOne(Query<T> query) {
	//		Session session = getSession();
	//		session.beginTransaction();
	//		
	//		T obj = query.uniqueResult(); // Single user
	//		session.close();
	//		return obj;
	//	}

	/** Retrieves the object with the given ID **/
	public T getById(Serializable id) {
		Session session = getSession();
		session.beginTransaction();
		T user = session.get(klass, id);
		session.close();
		return user;
	}

	/** Retrieves all objects matching the query **/
	//TODO see how to adapt this according to use-cases
	// since query to be created inside a transaction
	//	public List<T> getAll(Query<T> query) {		
	//		Session session = getSession();
	//		session.beginTransaction();
	//		
	//		List<T> list = query.list();
	//		
	//		session.close();
	//		return list;
	//	}

	/** Retrieves all objects **/
	public List<T> getAll() {
		Session session = getSession();
		session.beginTransaction();

		Query<T> q = query("from "+klassName);
		List<T> list = q.list();

		session.close();
		return list;
	}

	/** Insert an object **/
	public void add(T o) {
		Session session = getSession();
		session.beginTransaction();
		try {
			session.save(o);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	/** Delete an object **/
	public void delete(Serializable id) {
		Session session = getSession();
		session.beginTransaction();
		try {
			T entity = session.load(klass, id);
			session.delete(entity);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	/** Update an object **/
	public void update(T o) {
		Session session = getSession();
		session.beginTransaction();
		try {
			session.update(o);	//If not working update(KlassName, o)
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	/** Get all objects that has `field` = value
	 * 
	 * **/
	public List<T> getAllBy(String field, Serializable value) {
		Session session = getSession();
		session.beginTransaction();

		try {
			Query<T> q = query("from "+klassName+" where "+field+" = :fieldValue");
			q.setParameter("fieldValue", value);
			List<T> list = q.list();
			return list;
		} finally {
			session.close();
		}
	}
	
	/** Get one object that has `field` = value
	 * 
	 * **/
	public T getOneBy(String field, Serializable value) {
		Session session = getSession();
		session.beginTransaction();
		
		try {
			Query<T> q = query("from "+klassName+" where "+field+" = :fieldValue");
			q.setParameter("fieldValue", value);
			T ret = q.uniqueResult();
			return ret;	
		} finally {
			session.close();
		}	
	}
}
