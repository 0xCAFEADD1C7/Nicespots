package org.Dao;

import java.util.List;

import org.Entite.Commentaire;
import org.Entite.Evenement;
import org.Entite.User;

public interface CommentaireDao {

	public void addCommentaire(Commentaire commentaire);
	public Commentaire getCommentaire(int id);
	public List<Commentaire> getCommentaireByUser(User user);
	public List<Commentaire> getCommentaireByEvenement(Evenement evenement);
	
}
