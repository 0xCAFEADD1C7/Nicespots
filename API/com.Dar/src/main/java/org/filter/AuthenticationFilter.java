package org.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Dao.implement.UserDaoImpl;
import org.Dao.interfaces.UserDao;
import org.Entite.User;

public class AuthenticationFilter implements Filter {

	public void destroy() {
		
	}
	
	// the filter will only be used against forMethods http methods
	private String[] forMethods;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		PrintWriter out = response.getWriter();

		String token = request.getHeader("x-auth");

		UserDao userDao = new UserDaoImpl();

		User user =	userDao.getByToken(token);

		if(user != null && user.isValidToken() || token==null)
			chain.doFilter(servletRequest, servletResponse);

		else {
			response.setStatus(403);
			out.println("{\"error\" : \""+"Token is not valid"+"\"}");
		}
	}

	// methods must be a string containing the methods seperated by a comma
	// ex : POST,PUT,DELETE
	@Override
	public void init(FilterConfig conf) throws ServletException {
		forMethods = conf.getInitParameter("methods").split(",");
		for (int i = 0; i < forMethods.length; i++) {
			forMethods[i] = forMethods[i].toUpperCase().trim();
		}
	}

}
