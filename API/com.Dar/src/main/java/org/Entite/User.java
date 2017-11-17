package org.Entite;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;
import org.utils.CryptoUtils;
import org.utils.JSONable;

@Entity
@Table(name="user")
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
	private Date tokenExpirationDate;

	@Column
	private String lastName;

	@Column
	private String firstName;

	@Column(name ="password")
	private String password;


	public User() {
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public Date getTokenExpirationDate() {
		return tokenExpirationDate;
	}

	public void setTokenExpirationDate(Date tokenExperiationDate) {
		this.tokenExpirationDate = tokenExperiationDate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = CryptoUtils.toSHA256(password.getBytes());
	}

	public String toJson() throws Exception {
		return new JSONObject()
				.put("id", idUser)
				.put("lastName", lastName)
				.put("firstName", firstName)
				.put("pseudo", pseudo)
				.put("email", mail)
				.toString();
	}
	
	public void fromJson(JSONObject body, Map<String,Object> infos) throws Exception {
		mail = body.getString("email");
		firstName = body.getString("firstName");
		lastName = body.getString("lastName");
		pseudo = body.getString("pseudo");
		setPassword(body.getString("password"));
	}

	public boolean isValidToken() {
		return (tokenExpirationDate != null)
				? new Date().before(tokenExpirationDate)
				: false;
	}
}
