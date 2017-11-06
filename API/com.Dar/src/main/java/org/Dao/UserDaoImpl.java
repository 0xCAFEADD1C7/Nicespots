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
		session.beginTransaction();
		Query query = session.createQuery("from User where mail = :m");
		query.setParameter("m", mail);
		//List list = query.list(); // List of users
		User usr = (User) query.uniqueResult(); // Single user
		session.close();
		return usr;
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
				" where idUser = :id");;
		query.setParameter("tok", token);
		query.setParameter("id",user.getIdUser());
		query.setParameter("prenom",user.getPrenom());
		query.setParameter("nom",user.getNom());
		query.setParameter("pseudo",user.getPseudo());
		query.setParameter("mail",user.getMail());
		query.setParameter("pass",user.getPassword());
		query.setParameter("edate",user.getExperiationDate());
		query.executeUpdate();
		session.getTransaction().commit();

		session.close();
		
	}

}
