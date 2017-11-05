package org.Dao;

import java.util.List;

import org.Entite.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoImpl extends DaoImpl<User> implements UserDao {
	
	public UserDaoImpl () {
		super();
		
		this.klass = User.class;
		this.klassName = "User";
	}

	public User getByMail(String mail) {
		// TODO create a function that takes a map in argument and fetch it (as in Mongo)
		Session session = getSession();
		session.beginTransaction();
		
		Query<User> q = query("from User where mail = :m");
		q.setParameter("m", mail);
		
		User u = q.getSingleResult();
		
		session.close();
		return u;
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
