package org.Servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.GenericDao;
import org.exceptions.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractServlet<T> extends AbstractCrudServlet {
	private static final long serialVersionUID = 2L;
       
    public AbstractServlet() {
        super();
    }
    
    protected abstract GenericDao<T> get();
    protected abstract T fromJson(JSONObject body) throws Exception;
    protected abstract String toJson(T obj) throws JSONException;
    
    //TODO Can't be static, check if that change execution
	public String ofList(List<T> objs) throws JSONException {
		StringBuilder sb = new StringBuilder("[\n");
		
		for (int i = 0; i < objs.size(); i++) {
			if (i > 0) {
				sb.append(',');
			}
			sb.append(toJson(objs.get(i)));
		}
		
		sb.append("]");
		return sb.toString();
	}
    

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		T obj = fromJson(body);
		get().add(obj);
		
		return toJson(obj);
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		T obj = get().getById(uid);
		
		if (obj == null) {
			throw new NotFoundException("User not found");
		}
		
		return toJson(obj);
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		List<T> objs = get().getAll();
		return ofList(objs);
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		T obj = fromJson(body);
		get().update(obj);
		
		return toJson(obj);
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		GenericDao<T> obj = get();
		obj.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	//TODO already use in UserServlet, do generic function ?
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
}
