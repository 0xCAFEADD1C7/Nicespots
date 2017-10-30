package org.Entite;

import java.io.PrintWriter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.Dao.SpotDaoImpl;
import org.Dao.UserDaoImpl;
import org.json.JSONException;
import org.json.JSONObject;


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
	@Lob
	@Column(name="spot_img", nullable=false, columnDefinition="mediumblob")
	private byte[] image;
	
	public String toJson() throws JSONException {
		return new JSONObject()
				.put("idSpot",this.idSpot)
				.put("nom",this.nom)
				.put("createur",createur.getIdUser())
				.put("longitude",longitude)
				.put("latitud",latitude)
				.put("aime",aime)
				.put("image",image)
				.toString();
				
	}
	
	public static void addSpot(JSONObject body,PrintWriter out) {
		
		UserDaoImpl userDao = new UserDaoImpl();
		
		
		Spot spot = new Spot();
		try {
			spot.setAime(body.getInt("aime"));
			User user  = userDao.getUser(Integer.parseInt(body.getString("createur")));

			spot.setCreateur(user);
			spot.setLatitude(Float.parseFloat(body.getString("latitude")));
			spot.setLongitude(Float.parseFloat(body.getString("longitude")));
			spot.setNom(body.getString("nom"));
		//	spot.setImage("image",body.get);
			
			SpotDaoImpl spotDao = new SpotDaoImpl();
			spotDao.add(spot);
			out.println(spot.toJson());
			System.out.println(spot.toJson());
			
		} catch (JSONException e) {
			e.printStackTrace();
			out.println("{\"error\" : \""+e.getMessage()+"\"}");
		}
		
	}
	
	public static Spot getSpot(PrintWriter out,int id) {
		SpotDaoImpl spotDao = new SpotDaoImpl();
		Spot spot = new Spot();
		spot = spotDao.getSpot(id);
		
		try {
			out.println(spot.toJson());
		} catch (JSONException e) {
			out.println("{\"error\" : \""+e.getMessage()+"\"}");
			e.printStackTrace();
		}
		System.out.println(spot);

		return spot;
	}
	
	
	

	public Spot(String nom, User createur, float longitude, float latitude, int aime) {
		super();
		this.nom = nom;
		this.createur = createur;
		this.longitude = longitude;
		this.latitude = latitude;
		this.aime = aime;
	}

	public Spot() {
		// TODO Auto-generated constructor stub
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
