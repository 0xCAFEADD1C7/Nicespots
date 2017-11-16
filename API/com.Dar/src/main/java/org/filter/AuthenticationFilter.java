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

import org.Dao.interfaces.UserDao;
import org.Entite.User;
import org.utils.DAOFactory;
import org.utils.DateUtil;

public class AuthenticationFilter implements Filter {

	public void destroy() {
		
	}
	
	// the filter will only be used against forMethods http methods
	private String[] forMethods;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		boolean mandatory = false;
		String currentMeth = request.getMethod();
		System.out.println("Current method is "+currentMeth);
		for (String meth : forMethods) {
			if (meth.equals(currentMeth)) {
				mandatory = true;
				break;
			}
		}

		String token = request.getHeader("x-auth-token");
		if (token == null) {
			if (mandatory) {
				fail(response);
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
			return;
		}
	
		UserDao udao = DAOFactory.getUser();
		User user =	udao.getByToken(token);
		
		// utilisateur trouvé : parfait !
		if(user != null && user.isValidToken()) {
			// report expiration date to 1h
			user.setTokenExpirationDate(DateUtil.addSecs(user.getTokenExpirationDate(), 3600));
			udao.update(user);
			
			// pass userId to Servlet
			request.setAttribute("userId", user.getIdUser());
			
			// go to next filter
			chain.doFilter(servletRequest, servletResponse);
		} else if (!mandatory) {
			chain.doFilter(servletRequest, servletResponse);
		} else {
			fail(response);
		}
	}
	
	public void fail(HttpServletResponse response) throws IOException {
		response.setStatus(403);
		PrintWriter out = response.getWriter();
		out.println("{\"error\" : \""+"Token is not valid"+"\"}");
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
