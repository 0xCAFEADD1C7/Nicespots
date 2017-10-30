package org.Dao;

import java.util.List;

import org.Entite.Commentaire;
import org.Entite.Evenement;
import org.Entite.Spot;
import org.Entite.User;
import org.hibernate.Session;

import util.HibernateUtil;

public class CommentaireDaoImpl extends DaoImpl implements CommentaireDao {

	public void addCommentaire(Commentaire commentaire) {
		super.add(commentaire);
		
	}

	public Commentaire getCommentaire(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Commentaire commentaire =session.get(Commentaire.class,id);
		session.close();
		return commentaire;
	}

	public List<Commentaire> getCommentaireByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Commentaire> getCommentaireByEvenement(Evenement evenement) {
		// TODO Auto-generated method stub
		return null;
	}

}
