package org.Dao;

import java.util.List;

import org.Entite.User;
import org.hibernate.Session;

import util.HibernateUtil;

public class UserDaoImpl extends DaoImpl implements UserDao{

	public void addUser(User user) {
		super.add(user);
		
	}

	public User getUser(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User bb =session.get(User.class,id);
		session.close();
		return bb;
		
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
