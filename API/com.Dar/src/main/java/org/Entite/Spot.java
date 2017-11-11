package org.Entite;

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
	
	public String toJson() throws JSONException {
		return new JSONObject()
				.put("idSpot", idSpot)
				.put("name", name)
				.put("creator", creator.toJson())
				.put("longitude",longitude)
				.put("latitude",latitude)
				.toString();	
	}
	
	public void fromJson(JSONObject body) throws Exception {
		this.setIdSpot(Integer.getInteger(body.getString("idSpot")));
		this.setName(body.getString("name"));
		JSONObject jsUser = new JSONObject();
		User user = new User();
		user.fromJson(jsUser.getJSONObject(body.getString("creator")));
		this.setCreator(user);
		this.setLongitude(Integer.getInteger(body.getString("longitude")));
		this.setLatitude(Integer.getInteger(body.getString("latitude")));
	}

}
