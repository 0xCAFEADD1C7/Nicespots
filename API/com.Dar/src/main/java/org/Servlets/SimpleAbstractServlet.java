package org.Servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.Dao.GenericDao;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.JSONUtil;
import org.utils.JSONable;

public abstract class SimpleAbstractServlet<T extends JSONable> extends AbstractCrudServlet {
	private static final long serialVersionUID = 2L;
       
    public SimpleAbstractServlet() {
        super();
    }
    
    protected abstract GenericDao<T> getDAO();
    
    protected Class<T> klass;

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		T obj = klass.newInstance();
		obj.fromJson(body, collectInfos(request));
		getDAO().add(obj);
		
		return obj.toJson();
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		T obj = getDAO().getById(uid);
		
		if (obj == null) {
			throw new NotFoundException(klass.getName()+" not found");
		}
		
		return obj.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		List<T> objs = getDAO().getAll();
		return JSONUtil.ofList(objs);
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		T obj = klass.newInstance();
		obj.fromJson(body, collectInfos(request));
		getDAO().update(obj);
		
		return obj.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		GenericDao<T> obj = getDAO();
		obj.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
	
	protected Map<String, Object> collectInfos(HttpServletRequest request) {
		HashMap<String, Object> infos = new HashMap<>();
		infos.put("userId", request.getAttribute("userId"));
		
		return infos;
	}
}
