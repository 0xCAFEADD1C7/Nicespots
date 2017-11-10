package org.Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.Entite.User;
import org.exceptions.NotFoundException;
import org.exceptions.NotImplementedException;
import org.json.JSONObject;
import org.utils.DAOFactory;

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
		User user = User.getUser(uid);
		
		if (user == null) {
			throw new NotFoundException("User not found");
		}
		
		return user.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		return User.getAll().toString();
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	protected String delete(HttpServletRequest req) throws Exception {
		int uid = getIDParam(req);
		User.delete(uid);
		return "{ \"deleted\" : true }";
	}
	
	public int getIDParam(HttpServletRequest req) {
		String uid = getParam(req, 3);
		return Integer.parseInt(uid);
	}
}
