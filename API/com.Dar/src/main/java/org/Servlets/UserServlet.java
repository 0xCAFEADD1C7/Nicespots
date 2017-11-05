package org.Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.Entite.User;
import org.exceptions.NotFoundException;
import org.exceptions.NotImplementedException;
import org.json.JSONObject;

public class UserServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 1L;
  
    public UserServlet() {
        super();
    }

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		return User.addUser(body);
	}

	@Override
	protected String getOne(HttpServletRequest req) throws Exception {
		String uid = getParam(req, 3);
		User user = User.getUser(Integer.parseInt(uid));
		
		if (user == null) {
			throw new NotFoundException("User not found");
		}
		
		return user.toJson();
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		throw new NotImplementedException();
	}
}
