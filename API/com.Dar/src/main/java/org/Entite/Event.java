package org.Entite;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONable;

@Entity
@Table(name="event")
public class Event implements JSONable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idEvent;
	
	@Column
	private String name;
	@Column
	private String description;
	
	@OneToOne()
	@JoinColumn(name = "spot")
	private Spot spot;
	
	@OneToOne()
	@JoinColumn(name = "user")
	private User creator;
	
	@Column
	private Date date;

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toJson() throws Exception {
		return new JSONObject()
				.put("idEvent", idEvent)
				.put("name", name)
				.put("description", description)
				.put("creator", new JSONObject(creator.toJson()))
				.put("spot",  new JSONObject(spot.toJson()))
				.put("date", date)
				.toString();	
	}
	
	public void fromJson(JSONObject body, Map<String,Object> infos) throws Exception {
        name = body.getString("name");
        description = body.getString("description");
        date = new Date(body.getInt("date"));
        
        int spotId = body.getInt("spot");
        spot = DAOFactory.getSpot().getById(spotId);
        
        if (spot == null) {
        		throw new NotFoundException("No spot with id "+spotId);
        }
        
        int creatorId = (int) infos.get("userId");
        creator = DAOFactory.getUser().getById(creatorId);
        
        System.out.println("CREATOR :"+creator+ " - " + creatorId);
        System.out.println("SPOT :"+spot);
    }
	
}
