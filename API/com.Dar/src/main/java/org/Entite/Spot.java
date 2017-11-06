package org.Entite;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.Dao.SpotDaoImpl;
import org.Dao.UserDaoImpl;
import org.exceptions.NotImplementedException;
import org.json.JSONException;
import org.json.JSONObject;
import org.utils.JSONable;


@Entity
@Table(name="spot")
public class Spot implements JSONable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idSpot;
	
	@Column
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User creator;
	
	@Column
	private float longitude;
	
	@Column
	private float latitude;
	
	@Column
	private String address;
	
	@OneToMany(mappedBy="Spot")
	private List<String> images;
	
	@OneToMany(mappedBy="Spot")
	private List<String> activities;
	
	public List<SpotReview> getReviews() {
		throw new NotImplementedException();
	}
	
	public String toJson() throws JSONException {
		return new JSONObject()
				.put("idSpot", idSpot)
				.put("name", name)
				.put("creator", creator.getIdUser())
				.put("longitude",longitude)
				.put("latitude",latitude)
				.toString();	
	}
	
	public static String addSpot(JSONObject body, Serializable uid) throws JSONException {
		
		UserDaoImpl userDao = new UserDaoImpl();
		
		Spot spot = new Spot();
	
		User user = userDao.getById(uid);

		spot.setCreator(user);
		spot.setLatitude(Float.parseFloat(body.getString("latitude")));
		spot.setLongitude(Float.parseFloat(body.getString("longitude")));
		spot.setName(body.getString("name"));
		
		SpotDaoImpl spotDao = new SpotDaoImpl();
		spotDao.add(spot);
		
		return spot.toJson();
			
	}

	public Spot() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User createur) {
		this.creator = createur;
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

	public int getIdSpot() {
		return idSpot;
	}

	public void setIdSpot(int idSpot) {
		this.idSpot = idSpot;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getActivities() {
		return activities;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public List<String> getImages() {
		return images;
	}
	
	public static Spot getSpot(int id) {
		SpotDaoImpl spotDao = new SpotDaoImpl();
		Spot spot = spotDao.getById(id);		
		return spot;
	}

}
