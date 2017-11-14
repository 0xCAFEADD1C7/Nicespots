package org.Dao.implement;

import org.Dao.DaoImpl;
import org.Dao.interfaces.UserDao;
import org.Entite.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;

public class UserDaoImpl extends DaoImpl<User> implements UserDao {
	
	public UserDaoImpl () {
		super();
		
		this.klass = User.class;
		this.klassName = "User";
	}

	public User getByMail(String mail) {
		return getOneBy("mail", mail);
	}
	
	public User getByToken(String token) {
		return getOneBy("token", token);
	}
}
