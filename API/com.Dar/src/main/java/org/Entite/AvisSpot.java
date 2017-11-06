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

import org.json.JSONException;
import org.json.JSONObject;


@Entity
@Table(name="avisSpot",uniqueConstraints = {@UniqueConstraint(columnNames = {"user","spot"})})
public class AvisSpot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAvis;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "spot")
	private Spot spot;
	
	@Column
	private String avis;
	
	
	@Column
	private boolean aime;

	

	public AvisSpot(User user, Spot spot, String avis, boolean aime) {
		super();
		this.user = user;
		this.spot = spot;
		this.avis = avis;
		this.aime = aime;
	}

	
	public JSONObject toJson() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("user",user.toJson())
				.put("spot",spot.toJson())
				.put("avis",avis)
				.put("aime",aime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;
	}

	public int getIdAvis() {
		return idAvis;
	}


	public void setIdAvis(int idAvis) {
		this.idAvis = idAvis;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Spot getSpot() {
		return spot;
	}


	public void setSpot(Spot spot) {
		this.spot = spot;
	}


	public String getAvis() {
		return avis;
	}


	public void setAvis(String avis) {
		this.avis = avis;
	}


	public boolean isAime() {
		return aime;
	}


	public void setAime(boolean aime) {
		this.aime = aime;
	}
	

}
