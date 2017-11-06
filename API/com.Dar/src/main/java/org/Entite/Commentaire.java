package org.Entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="commentaire")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idCommentaire;
	
	@OneToOne
	@JoinColumn(name ="user")
	private User user;
	
	@OneToOne
	@JoinColumn(name ="idEvenement")
	private Evenement evenement;
	
	@Column
	private String message;

	public int getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public User getCommentateur() {
		return user;
	}

	public void setCommentateur(User commentateur) {
		this.user = commentateur;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
