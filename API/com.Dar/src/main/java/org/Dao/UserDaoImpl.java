package org.Dao;

import java.util.List;

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
		// TODO create a function that takes a map in argument and fetch it (as in Mongo)
		Session session = getSession();
		session.beginTransaction();

		Query<User> q = query("from User where mail = :m");
		q.setParameter("m", mail);

		User u = q.getSingleResult();

		session.close();
		return u;
	}


	public List<User> getAllUsers(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from User");
		List<User> list = query.list(); // List of users
		session.close();
		return list;

	}

	public User getUserByToken(String token) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where token = :m");
		query.setParameter("m", token);
		//List list = query.list(); // List of users
		User usr = (User) query.uniqueResult(); // Single user
		session.close();
		return usr;
	}

	public void updateUser(String token, User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("update User as b set token = :tok," +
				" prenom = :prenom,"+
				" nom = :nom, pseudo =:pseudo,"+
				" mail =:mail,"+
				" password =:pass,"+
				" experiationDate =:edate"+
				" where idUser = :id");
		query.setParameter("tok", token);
		query.setParameter("id",user.getIdUser());
		query.setParameter("prenom",user.getPrenom());
		query.setParameter("nom",user.getNom());
		query.setParameter("pseudo",user.getPseudo());
		query.setParameter("mail",user.getMail());
		query.setParameter("pass",user.getPassword());
		query.setParameter("edate",user.getExperiationDate());
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
