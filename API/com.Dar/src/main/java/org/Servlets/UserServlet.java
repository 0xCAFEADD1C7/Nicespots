package org.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Entite.Spot;
import org.Entite.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PrintWriter out = response.getWriter();
    	String[] requete = request.getPathInfo().split("/");
    	
    	System.out.println(request.getPathInfo());
    	
    	if(!requete[1].isEmpty())
    	     		User.getUser(out, Integer.parseInt(requete[1]));
        		 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			JSONObject body = getBody(request);
        			User.addUser(body, out);
	} catch (Exception e) {
			e.printStackTrace();
			out.println("{\"error\" : \""+e.getMessage()+"\"}");
		}
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
