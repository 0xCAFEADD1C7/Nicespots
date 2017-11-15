package org.Entite;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

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
import org.utils.DAOFactory;
import org.utils.JSONable;


@Entity
@Table(name="SpotReview",uniqueConstraints = {@UniqueConstraint(columnNames = {"user","spot"})})
public class SpotReview implements JSONable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idReview;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "spot")
	private Spot spot;
	
	@Column
	private String review;
	
	
	@Column
	private int rating;	
	
	@Column
	private Date createdAt;

	public int getReviewId() {
		return idReview;
	}


	public void setReviewId(int idAvis) {
		this.idReview = idAvis;
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


	public String getReview() {
		return review;
	}


	public void setReview(String avis) {
		this.review = avis;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public String toJson() throws Exception {
		return new JSONObject()
				.put("idReview", idReview)
				.put("user", user.toJson())
				.put("spot", spot.toJson())
				.put("review", review)
				.put("rating", rating)
				.put("createdAt", createdAt)
				.toString();	
	}


	public void fromJson(JSONObject body, Map<String,Object> infos) throws Exception {
		review = body.getString("review");
	    rating = body.getInt("rating");
	    
		int spotId = body.getInt("spot");
		spot = DAOFactory.getSpot().getById(spotId);
	    user = DAOFactory.getUser().getById((int)infos.get("userId"));
	    createdAt = DateFormat.getDateInstance().parse(body.getString("createdAt"));
	}
}
