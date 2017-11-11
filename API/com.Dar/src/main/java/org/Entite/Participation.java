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
@Table(name="participation",uniqueConstraints = {@UniqueConstraint(columnNames = {"user","event"})})
public class Participation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column	
	private int idParticipation;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User user ;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event")
	private Event event;

	public Participation(User user, Event event) {
		super();
		this.user = user;
		this.event = event;
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
