package org.Entite;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="evenement")
public class Evenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idEvent;
	
	@Column
	private String nom;
	@Column
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "spot")
	private Spot spot;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User createur;
	
	@Column
	private Date date;

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public User getCreateur() {
		return createur;
	}

	public void setCreateur(User createur) {
		this.createur = createur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
