package org.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Dao.GenericDao;
import org.Dao.interfaces.SpotReviewDao;
import org.Entite.SpotReview;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;

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
	
	protected String getBySpot(HttpServletRequest request) throws Exception {
		int spotId = getIDParam(request);
		List<SpotReview> objs = getDAO().getAll();
		List<SpotReview> res = new ArrayList<SpotReview>();
		for(SpotReview elem : objs) {
			if(elem.getSpot().getIdSpot() == spotId) {
				res.add(elem);
			}
		}
		
		return JSONUtil.ofList(res);
	}
	
	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParamUid(request);
		SpotReview obj = getDAO().getById(uid);
		
		if (obj == null) {
			throw new NotFoundException(klass.getName()+" not found");
		}
		
		return obj.toJson();
	}
	
	@Override
	protected String update(HttpServletRequest request) throws Exception {
		int spotId = getIDParam(request);
		JSONObject body = getBody(request);
		SpotReview obj = klass.newInstance();
		try {
			Map<String, Object> collect = collectInfos(request);
			collect.put("spotId", spotId);
			obj.fromJson(body, collect);
			obj.setReviewId(getIDParamUid(request));
			System.out.println(">>>");
			System.out.println(obj.toJson());
			System.out.println("<<<");
			getDAO().update(obj);

		} catch (Exception e) {
			throw e;
		}
		
		return obj.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParamUid(request);
		GenericDao<SpotReview> obj = getDAO();
		obj.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	public int getIDParamUid(HttpServletRequest request) {
		String uid = getParam(request, 4);
		return Integer.parseInt(uid);
	}

	
	
	protected int nbSlash(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int nbSlash = uri.length() - uri.replace("/", "").length();
		if (uri.charAt(uri.length()-1) == '/') {
			nbSlash--;
		}
		return nbSlash;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			if (nbSlash(request) == 3) {
				out.println(getBySpot(request));
			} else if (nbSlash(request) == 4) {
				out.println(getOne(request));
			} else {
				//Not Logic
				//out.println(getAll(request));
				throw new Exception("Action Not Valid");
			}
		} catch(Exception e) {
			handleError(e, response);
		}
	}
	
}
