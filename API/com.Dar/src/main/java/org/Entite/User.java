package org.Entite;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.Dao.UserDao;
import org.Dao.UserDaoImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.utils.JSONUtil;
import org.utils.JSONable;

@Entity
@Table(name="user",uniqueConstraints = {@UniqueConstraint(columnNames = {"nom","prenom"})})
public class User implements JSONable {
	
	
	@Id
	@Column(name="idUser")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	@Column(name="mail", unique=true )
	private String mail;
	
	@Column(name="pseudo", unique = true)
	private String pseudo;
	
	@Column(unique = true)
	private String token;
	
	@Column
	private Date experiationDate;
	
	@Column
	private String nom;
	
	@Column
	private String prenom;
	
	@Column
	private Date dateDeNaissance;
	
	
	@Column(name ="password")
	private String password;

	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private Set<Commentaire> liker = new HashSet<Commentaire>();


	public User(String mail, String nom, String prenom, Date dateDeNaissance, String password) {
		super();
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.password = password;
	}
	
	public User() {
	}

	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}


	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		
		
		this.password = toSHA256(password.getBytes());
	}
	 public static String toSHA256(byte[] convertme) {
		    MessageDigest md = null;
		    try {
		        md = MessageDigest.getInstance("SHA-256");
		    }
		    catch(NoSuchAlgorithmException e) {
		        e.printStackTrace();
		    } 
		    byte[] mdbytes =  md.digest(convertme);
		    StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        return sb.toString();

		}
	 
	public String toJson() {
		try {
			return new JSONObject()
					.put("id", idUser)
					.put("nom", nom)
					.put("prenom", prenom)
					.put("date_naissance", dateDeNaissance.toString())
					.put("email", mail)
					.toString();
		} catch (JSONException e) {
			// this should not happen, but this is a hack not to be annoyed by 
			// JSONObject and its useless throw declaration...
			return "{ \"wtf\" : true }";
		}
	}

	
	public static String addUser(JSONObject body) throws JSONException {
		
		User user = new User();
		user.setMail(body.getString("mail"));
		user.setNom(body.getString("nom"));
		user.setPrenom(body.getString("prenom"));
		user.setPassword(body.getString("password"));
		user.setDateDeNaissance(new Date());
		
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.add(user);
		return user.toJson();
	}
	
	public static User getUser(int id)  {
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.getById(id);
		return user;
	}

	public static void delete(int id) {
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.delete(id);
	}
	
	public static String getAll() throws JSONException {
		UserDao uDao = new UserDaoImpl();
		List<User> users = uDao.getAll();
		
		return JSONUtil.ofList(users);
	}
}
