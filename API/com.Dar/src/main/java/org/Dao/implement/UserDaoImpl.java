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
	
	public User getUserByToken(String token) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<?> query = session.createQuery("from User where token = :m");
		query.setParameter("m", token);
		//List list = query.list(); // List of users
		User usr = (User) query.uniqueResult(); // Single user
		session.close();
		return usr;
	}

	public void updateUser(String token, User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query<?> query = session.createQuery("update User as b set token = :tok," +
				" prenom = :prenom,"+
				" nom = :nom, pseudo =:pseudo,"+
				" mail =:mail,"+
				" password =:pass,"+
				" experiationDate =:edate"+
				" where idUser = :id");
		query.setParameter("tok", token);
		query.setParameter("id",user.getIdUser());
		query.setParameter("prenom",user.getFirstName());
		query.setParameter("nom",user.getLastName());
		query.setParameter("pseudo",user.getPseudo());
		query.setParameter("mail",user.getMail());
		query.setParameter("pass",user.getPassword());
		query.setParameter("edate",user.getTokenExperiationDate());
		query.executeUpdate();
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
