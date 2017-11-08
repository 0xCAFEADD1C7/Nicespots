package org.Servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.UserDao;
import org.Entite.User;
import org.exceptions.NotFoundException;
import org.json.JSONObject;
import org.utils.DAOFactory;
import org.utils.JSONUtil;

public class UserServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 1L;
  
    public UserServlet() {
        super();
    }

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		User user = User.fromJson(body);
		DAOFactory.getUser().add(user);
		
		return user.toJson();
	}

	@Override
	protected String getOne(HttpServletRequest req) throws Exception {
		int uid = getIDParam(req);
		User user = DAOFactory.getUser().getById(uid);
		
		if (user == null) {
			throw new NotFoundException("User not found");
		}
		
		return user.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		List<User> users = DAOFactory.getUser().getAll();
		return JSONUtil.ofList(users);
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		User user = User.fromJson(body);
		DAOFactory.getUser().update(user);
		
		return user.toJson();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		int uid = getIDParam(request);
		UserDao user = DAOFactory.getUser();
		user.delete(uid);
		
		return "{ \"deleted\" : true }";
	}
	
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
}
