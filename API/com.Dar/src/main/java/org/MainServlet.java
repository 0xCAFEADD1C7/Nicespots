package org;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Dao.BookDaoImpl;
import org.Entite.Book;
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
		PrintWriter out = response.getWriter();
		
		Book b = new Book(1,"ROBEOB","Ayemeric",125);
		BookDaoImpl imp = new BookDaoImpl();
		imp.addBook(b);
			
//		try {
//			String id = request.getParameter("id");
//			DogBean dog = new DogBean(id);
//			out.println(dog.toJson());
//		} catch (Exception e) {
//			e.printStackTrace();
//			out.println("{\"error\" : \""+e.getMessage()+"\"}");
//		}
			
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
//		try {
//			JSONObject body = getBody(request);
//			
//			DogBean dog = new DogBean();
//			dog.setAge(body.getInt("age"));
//			dog.setName(body.getString("name"));
//			dog.setRace(Race.valueOf(body.getString("race")));
//			dog.setTatooId(body.getString("tatooId"));
//			dog.save();
//			
//			out.println(dog.toJson());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			out.println("{\"error\" : \""+e.getMessage()+"\"}");
//		}
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

}
