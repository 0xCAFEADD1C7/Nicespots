package org.Servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.SpotDao;
import org.Dao.interfaces.SpotReviewDao;
import org.Entite.SpotReview;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;

public class SpotReviewServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 2L;
       
    public SpotReviewServlet() {
        super();
    }

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		SpotReview spotReview = SpotReview.fromJson(body);
		DAOFactory.getSpotReview().add(spotReview);
		
		return spotReview.toJson();
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		SpotReview spotReview = DAOFactory.getSpotReview().getById(uid);
		
		if (spotReview == null) {
			throw new NotFoundException("User not found");
		}
		
		return spotReview.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		List<SpotReview> spotReview = DAOFactory.getSpotReview().getAll();
		return JSONUtil.ofList(spotReview);
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		SpotReview spotReview = SpotReview.fromJson(body);
		DAOFactory.getSpotReview().update(spotReview);
		
		return spotReview.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		SpotReviewDao spotReview = DAOFactory.getSpotReview();
		spotReview.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	//TODO already use in UserServlet, do generic function ?
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
}
