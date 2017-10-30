package org;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /** 
     * Default constructor. 
     */
    public MainServlet() {
    		
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	ClassLoader classLoader = getClass().getClassLoader();
    	
    	File f = new File(classLoader.getResource("images/test.txt").getFile());
    	Scanner sc = new Scanner(f);
    	System.out.println(sc.next());
    	sc.close();
    
		
//    	
//    	if(requete[1].equals("spot")) {
//    			Spot.getSpot(out, Integer.parseInt(requete[2]));
//    	}
	}
    
    
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		try {
			JSONObject body = getBody(request);
			
		
		
		String[] requete = request.getPathInfo().split("/");
    	
    	if(requete[1].equals("user")) 

    			User.addUser(body, out);

    	
    	else if(requete[1].equals("spot"))
    			Spot.addSpot(body, out);

	

	

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			out.println("{\"error\" : \""+e.getMessage()+"\"}");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		JSONObject body = getBody(request);
//		
//		Document doc = new Document().append("name", body.get("name")).append("age", body.get("age"));
//		
//		ObjectId id = new ObjectId(body.getString("id"));
//		BasicDBObject query = new BasicDBObject();
//	    query.put("_id", id);
//		
//		test.replaceOne(query, doc);
	}
	
	public  boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

}
