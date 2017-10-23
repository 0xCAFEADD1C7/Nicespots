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


@Entity
@Table(name="spot")
public class Spot {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idSpot;
	
	@Column
	private String nom;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User createur;
	
	@Column
	private float longitude;
	
	@Column
	private float latitude;
	
	@Column
	private int aime ;
	
	
	
	

	public Spot(String nom, User createur, float longitude, float latitude, int aime) {
		super();
		this.nom = nom;
		this.createur = createur;
		this.longitude = longitude;
		this.latitude = latitude;
		this.aime = aime;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public User getCreateur() {
		return createur;
	}

	public void setCreateur(User createur) {
		this.createur = createur;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getAime() {
		return aime;
	}

	public void setAime(int aime) {
		this.aime = aime;
	}
	
	
	

}