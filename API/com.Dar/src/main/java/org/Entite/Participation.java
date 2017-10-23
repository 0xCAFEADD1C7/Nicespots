package org.Entite;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="participation",uniqueConstraints = {@UniqueConstraint(columnNames = {"user","evenement"})})
public class Participation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column	
	private int idParticipation;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User user ;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "evenement")
	private Evenement evenement;

	public Participation(User user, Evenement evenement) {
		super();
		this.user = user;
		this.evenement = evenement;
	}

	public int getIdParticipation() {
		return idParticipation;
	}

	public void setIdParticipation(int idParticipation) {
		this.idParticipation = idParticipation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}
	
	
	
		
	
}
