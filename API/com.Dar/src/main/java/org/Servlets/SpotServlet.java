package org.Servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.SpotDao;
import org.Entite.Spot;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;

public class SpotServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 2L;
       
    public SpotServlet() {
        super();
    }

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		Spot spot = new Spot();
		spot.fromJson(body);
		DAOFactory.getSpot().add(spot);
		
		return spot.toJson();
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		Spot spot = DAOFactory.getSpot().getById(uid);
		
		if (spot == null) {
			throw new NotFoundException("User not found");
		}
		
		return spot.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		List<Spot> spots = DAOFactory.getSpot().getAll();
		return JSONUtil.ofList(spots);
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		Spot spot = new Spot();
		spot.fromJson(body);
		DAOFactory.getSpot().update(spot);
		
		return spot.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		SpotDao spot = DAOFactory.getSpot();
		spot.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	//TODO already use in UserServlet, do generic function ?
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
}
