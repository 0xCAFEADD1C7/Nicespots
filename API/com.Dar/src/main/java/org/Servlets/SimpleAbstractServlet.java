package org.Servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.GenericDao;
import org.exceptions.NotFoundException;
import org.json.JSONException;
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
		obj.fromJson(body);
		getDAO().add(obj);
		
		return obj.toJson();
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		T obj = getDAO().getById(uid);
		
		if (obj == null) {
			throw new NotFoundException("User not found");
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
		obj.fromJson(body);
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
	
	public String toJsonSafe(String in, String[] outs) throws Exception {
		JSONObject res = new JSONObject();
		res.getJSONObject(in);
		
		for(String out : outs) {
			JSONObject obj = new JSONObject();
			try {
				obj.getJSONObject(out);
				res.put(out, toJsonSafe(obj.toString(), outs));
			} catch (JSONException e) {
				res.remove(out);
			}
		}
		
		return res.toString();
	}
}
