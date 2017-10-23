package org.Entite;

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
@Table(name="aimeCommentaire",uniqueConstraints = {@UniqueConstraint(columnNames = {"liker","commentaire"})})
public class AimeCommentaire {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAimeCommentaire;
	
	@OneToOne
	@JoinColumn(name="idUser")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "commentaire")
	private Commentaire commentaire ;
	
	
	
	
}
