package org.Dao;

import java.util.List;

import org.Entite.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoImpl extends DaoImpl<User> implements UserDao {

	public void addUser(User user) {
		super.add(user);
	}

	public User getById(int id) {
		Session session = getSession();
		session.beginTransaction();
		User user = session.get(User.class,id);
		session.close();
		return user;
	}

	public List<User> getAll() {
		Query<User> q = query("from User");
		return super.getAll(q);
	}

	public User getByMail(String mail) {
		Query<User> q = query("from User where mail = :m");
		q.setParameter("m", mail);
		return getOne(q);
	}

	public void updateTokenUSer(String token, User user) {
		Session session = getSession();
		session.beginTransaction();
		
		String qs = "update User set token = :tok where idUser = :id";
		Query<User> q = query(qs);
		q.setParameter("tok", token);
		q.setParameter("id", user.getIdUser());
		q.executeUpdate();
		System.out.println("Updating token for user "+user.getIdUser());
		session.getTransaction().commit();

		session.close();
		
	}

}
