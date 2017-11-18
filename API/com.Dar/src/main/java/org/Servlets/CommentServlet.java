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
import org.Dao.interfaces.CommentDao;
import org.Entite.Comment;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;


public class CommentServlet extends SimpleAbstractServlet<Comment> {
	private static final long serialVersionUID = 1L;

    public CommentServlet() {
        super();

        klass = Comment.class;
        
    }

	@Override
	protected CommentDao getDAO() {
		return DAOFactory.getComment();
	}
	
	@Override
	protected String create(HttpServletRequest request) throws Exception {
		int eventId = getIDParam(request);
		JSONObject body = getBody(request);
		Comment obj = klass.newInstance();
		Map<String, Object> collect = collectInfos(request);
		collect.put("eventId", eventId);
		obj.fromJson(body, collect);
		getDAO().add(obj);

		return obj.toJson();
	}
	
	protected String getBySpot(HttpServletRequest request) throws Exception {
		int eventId = getIDParam(request);
		List<Comment> objs = getDAO().getAll();
		List<Comment> res = new ArrayList<Comment>();
		for(Comment elem : objs) {
			if(elem.getEvent().getIdEvent() == eventId) {
				res.add(elem);
			}
		}
		
		return JSONUtil.ofList(res);
	}
	
	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParamUid(request);
		Comment obj = getDAO().getById(uid);
		
		if (obj == null) {
			throw new NotFoundException(klass.getName()+" not found");
		}
		
		return obj.toJson();
	}
	
	@Override
	protected String update(HttpServletRequest request) throws Exception {
		int eventId = getIDParam(request);
		JSONObject body = getBody(request);
		Comment obj = klass.newInstance();
		Map<String, Object> collect = collectInfos(request);
		collect.put("eventId", eventId);
		obj.fromJson(body, collect);
		obj.setIdComment(getIDParamUid(request));
		getDAO().update(obj);

		return obj.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParamUid(request);
		GenericDao<Comment> obj = getDAO();
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
				throw new Exception("Action Not Valid");
			}
		} catch(Exception e) {
			handleError(e, response);
		}
	}
    
}
