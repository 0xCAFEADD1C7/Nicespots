package org.Servlets;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.SpotReviewDao;
import org.Entite.SpotReview;
import org.json.JSONObject;
import org.utils.DAOFactory;

public class SpotReviewServlet extends SimpleAbstractServlet<SpotReview> {
	private static final long serialVersionUID = 2L;
       
    public SpotReviewServlet() {
        super();
        klass = SpotReview.class;
    }

	@Override
	protected SpotReviewDao getDAO() {
		return DAOFactory.getSpotReview();
	}
	
	@Override
	protected String create(HttpServletRequest request) throws Exception {
		int spotId = getIDParam(request);
		System.out.println(">>> eventId: "+spotId);
		JSONObject body = getBody(request);
		SpotReview obj = klass.newInstance();
		try {
			Map<String, Object> collect = collectInfos(request);
			collect.put("spotId", spotId);
			obj.fromJson(body, collect);

			getDAO().add(obj);

		} catch (Exception e) {
			throw e;
		}
		
		return obj.toJson();
	}
	
	public int getIDParamUid(HttpServletRequest request) {
		String uid = getParam(request, 4);
		return Integer.parseInt(uid);
	}
	
}
