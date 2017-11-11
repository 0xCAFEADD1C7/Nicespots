package org.Entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;
import org.utils.JSONable;

@Entity
@Table(name="comment")
public class Comment implements JSONable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idComment;
	
	@OneToOne
	@JoinColumn(name ="user")
	private User poster;
	
	@OneToOne
	@JoinColumn(name ="idEvent")
	private Event event;
	
	@Column
	private String message;
	
	@Column
	private Date createdAt;

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String toJson() {
        try {
            return new JSONObject()
                    .put("id", idComment)
                    .put("poster Last name",poster.getLastName())
                    .put("poster first name", poster.getFirstName())
                    .put("poster pseudo ",poster.getPseudo() )
                    .put("evenement", event.getIdEvent())
                    .put("Date de creation", createdAt )
                    .toString();
        } catch (JSONException e) {
            // this should not happen, but this is a hack not to be annoyed by
            // JSONObject and its useless throw declaration...
            return "{ \"wtf\" : true }";
        }
    }
    
    
    public void fromJson(JSONObject body) throws Exception {
        this.setMessage(body.getString("comment"));
        Event event = new Event();
        event.fromJson(body);
        this.setEvent(event);
        User user = new User();
        JSONObject jsUser = new JSONObject();
        user.fromJson(jsUser.getJSONObject(body.getString("poster")));
        this.setPoster(new User());
        
		@SuppressWarnings("deprecation")
		Date date = new Date(body.getString("createdAt"));
		this.setCreatedAt(date);
    }
    
}
