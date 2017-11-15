package org.Servlets;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.Dao.interfaces.UserDao;
import org.Entite.User;
import org.exceptions.NotFoundException;
import org.exceptions.UnauthorizedException;
import org.json.JSONObject;
import org.utils.CryptoUtils;
import org.utils.DAOFactory;
import org.utils.DateUtil;

public class LoginServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		
		UserDao udao = DAOFactory.getUser();
		
		User user = udao.getByMail(body.getString("mail"));	
		if(user == null) { // no user with this email
			throw new UnauthorizedException("Bad credentials");
		}
		
		String hashedPw = CryptoUtils.toSHA256(body.getString("password").getBytes());
		if(user.getPassword().equals(hashedPw)) {
			
			String token = CryptoUtils.randomHash();
			
			user.setToken(token);
			user.setTokenExpirationDate(DateUtil.addSecs(new Date(), 3600));
			
			udao.update(user);
			
			return new JSONObject().put("token", token).toString();
		}
		
		throw new UnauthorizedException("Bad credentials");
	}


	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Route");
	}
}
