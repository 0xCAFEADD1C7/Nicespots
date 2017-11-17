package org.Servlets;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
//	@Override
//	protected String getOne(HttpServletRequest request) throws Exception {
//		// EventId not use for searching
//		//int eventId = getIDParam(request);
//		int uid = getIDParamUid(request);
//		Comment obj = getDAO().getById(uid);
//		
//		if (obj == null) {
//			throw new NotFoundException(klass.getName()+" not found");
//		}
//		
//		return obj.toJson();
//	}
	
	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		// EventId not use for deleting
		//int eventId = getIDParam(request);
		int uid = getIDParamUid(request);
		GenericDao<Comment> obj = getDAO();
		obj.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
//	@Override
//	protected String getAll(HttpServletRequest request) throws Exception {
//		// EventId not use for all
//		//int eventId = getIDParam(request);
//		int uid = getIDParamUid(request);
//		
//		DAOFactory.getEvent().getById(uid);
//		
//		List<Comment> objs = getDAO().getAll();
//		return JSONUtil.ofList(objs);
//	}
	
	public int getIDParamUid(HttpServletRequest request) {
		String uid = getParam(request, 4);
		return Integer.parseInt(uid);
	}
    
}
