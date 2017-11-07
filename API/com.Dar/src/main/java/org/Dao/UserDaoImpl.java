package org.Dao;

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
		return getOneBy("mail", mail);
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
