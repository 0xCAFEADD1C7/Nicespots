package org.Dao;

import java.io.Serializable;
import java.util.List;

import org.exceptions.NotImplementedException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;

public abstract class DaoImpl<T> implements GenericDao<T> {
	
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
	public T getOne(Query<T> query) {
		Session session = getSession();
		session.beginTransaction();
		
		T obj = query.uniqueResult(); // Single user
		session.close();
		return obj;
	}
	
	/** Retrieves the object with the given ID **/
	/* NOTE :
	 * T obj = session.get(T.class, arg1)
	 * would be a nice implem (perf, readability etc)
	 * but would require some changes elsewhere. Need to discuss about it
	 */
	public T getById(Serializable id) {
		throw new NotImplementedException();
	}
	
	/** Retrieves all objects matching the query **/
	//TODO add pagination ??
	public List<T> getAll(Query<T> query) {		
		Session session = getSession();
		session.beginTransaction();
		
		List<T> list = query.list(); // List of users
		
		session.close();
		return list;
	}

	/** Insert an object **/
	public void add(T o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
