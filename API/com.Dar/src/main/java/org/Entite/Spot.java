package org.Entite;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;
import org.utils.JSONable;
import org.utils.MapApiClient;
import org.utils.TransporationFinder;
import org.utils.WeatherApiClient;


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
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "images")
	private Set<String> images;
	 
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "activities")
	private Set<String> activities;
	
	

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

	public Set<String> getActivities() {
		return activities;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public Set<String> getImages() {
		return images;
	}
	
	public String toJson() throws Exception {
		
		String weather = WeatherApiClient.getWeather(latitude, longitude, 1);
		String transport = TransporationFinder.getInstance().getNearest((double)latitude,(double)longitude);
		return new JSONObject()
				.put("id", idSpot)
				.put("name", name)
				.put("creator", new JSONObject(creator.toJson()))
				.put("longitude",longitude)
				.put("latitude",latitude)
				.put("images", images)
				.put("activities", activities)
				.put("address", address)
				.put("weather", weather)
				.put("transport",transport)
				.toString();	
	}
	
	public void fromJson(JSONObject body, Map<String,Object> infos) throws Exception {
		System.out.println(">>>fromJson Spot");
		name = body.getString("name");
		longitude = Float.parseFloat(body.getString("longitude"));
		latitude = Float.parseFloat(body.getString("latitude"));
		// TODO address = ...
		address = MapApiClient.getAddress(latitude, longitude);

		// TODO images on POST ?
		// images = JSONUtil.getStringArray(body, "images");
		images = new HashSet<>();
		
		activities = new HashSet<String>(JSONUtil.getStringArray(body, "activities"));
		int creatorId = (int) infos.get("userId");
		creator = DAOFactory.getUser().getById(creatorId);
	}

}
