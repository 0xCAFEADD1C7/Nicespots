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
@Table(name="commentLike",uniqueConstraints = {@UniqueConstraint(columnNames = {"liker","comment"})})
public class CommentLike {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idCommentLike;
	
	@OneToOne
	@JoinColumn(name="idUser")
	private User liker;
	
	@OneToOne
	@JoinColumn(name = "comment")
	private Comment comment;

	public int getIdCommentLike() {
		return idCommentLike;
	}

	public void setIdCommentLike(int idCommentLike) {
		this.idCommentLike = idCommentLike;
	}

	public User getLiker() {
		return liker;
	}

	public void setUser(User liker) {
		this.liker = liker;
	}

	public Comment getCommentaire() {
		return comment;
	}

	public void setCommentaire(Comment comment) {
		this.comment = comment;
	}	
	
}
