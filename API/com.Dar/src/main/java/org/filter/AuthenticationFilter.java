package org.filter;

import java.io.BufferedReader;
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

import org.Dao.UserDaoImpl;
import org.Entite.User;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		 HttpServletRequest request = (HttpServletRequest) servletRequest;

		    HttpServletResponse response = (HttpServletResponse) servletResponse;
			PrintWriter out = response.getWriter();
		
		System.out.println("authentification ");
			
				//JSONObject body = getBody(request);
				
				System.out.println("got body ");

				String token = request.getHeader("x-auth");
				
				UserDaoImpl userDao = new UserDaoImpl();
				
				User user =	userDao.getUserByToken(token);
				
				if(user != null && user.isValidToken() || token==null)
					chain.doFilter(servletRequest, servletResponse);
				
				else {
					response.setStatus(400);
					out.println("{\"error\" : \""+"Token is not valid"+"\"}");
				}
				
			
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out
        .println("------------------------------------------------------");
System.out.println(" init method is called in "
        + this.getClass().getName());
System.out
        .println("------------------------------------------------------");
		
	}
	 public JSONObject getBody(HttpServletRequest request) throws IOException, JSONException {
	 		BufferedReader in = request.getReader();
			StringBuilder bodyBuilder = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				bodyBuilder.append(line);
			}
			String body = bodyBuilder.toString();
			return new JSONObject(body);
	 }
	 
}
