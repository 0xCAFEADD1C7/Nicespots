package org.Servlets;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.CommentDao;
import org.Entite.Comment;
import org.json.JSONObject;
import org.utils.DAOFactory;


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
		System.out.println(">>> eventId: "+eventId);
		JSONObject body = getBody(request);
		Comment obj = klass.newInstance();
		try {
			Map<String, Object> collect = collectInfos(request);
			collect.put("eventId", eventId);
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
