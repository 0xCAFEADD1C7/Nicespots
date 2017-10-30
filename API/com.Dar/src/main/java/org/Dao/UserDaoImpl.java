package org.Dao;

import java.util.List;

import org.Entite.User;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

public class UserDaoImpl extends DaoImpl implements UserDao{

	public void addUser(User user) {
		super.add(user);
		
	}

	public User getUser(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user =session.get(User.class,id);
		session.close();
		return user;
		
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByMail(String mail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from user where name = :name");
		query.setParameter("name", "abc");
		List list = query.list(); // List of users
		User usr = (User) query.uniqueResult(); // Single user
		
		return usr;
	}

}
