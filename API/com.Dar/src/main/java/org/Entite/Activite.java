package org.Entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="activite")
public class Activite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAcitivte;
	
	@Column
	private String nom;

	public Activite(String nom) {
		super();
		this.nom = nom;
	}

	public int getIdAcitivte() {
		return idAcitivte;
	}

	public void setIdAcitivte(int idAcitivte) {
		this.idAcitivte = idAcitivte;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
