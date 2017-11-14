package org.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Dao.implement.UserDaoImpl;
import org.Entite.User;
import org.exceptions.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/login")
public class LoginServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	 public static JSONObject getBody(HttpServletRequest request) throws IOException, JSONException {
	 		BufferedReader in = request.getReader();
			StringBuilder bodyBuilder = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				bodyBuilder.append(line);
			}
			String body = bodyBuilder.toString();
			return new JSONObject(body);
	 }
	 
	 
	 public static String toSHA256(byte[] convertme) {
		    MessageDigest md = null;
		    try {
		        md = MessageDigest.getInstance("SHA-256");
		    }
		    catch(NoSuchAlgorithmException e) {
		        e.printStackTrace();
		    } 
		    byte[] mdbytes =  md.digest(convertme);
		    StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        return sb.toString();

		}

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		JSONObject body = getBody(request);
		
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.getByMail(body.getString("mail"));
		System.out.println(toSHA256(body.getString("password").getBytes()));
		if(user != null) {
			if(user.getPassword().equals(toSHA256(body.getString("password").getBytes()))) {
				System.out.println("true connected");
				Random random = new Random();
				String token =toSHA256(new String(user.getMail()+random.nextDouble()).getBytes());
				userDao.updateTokenUSer(token, user);
				return new JSONObject().put("token", token).toString();
			}
		}else {
			System.out.println("{\"error\" : \""+"Le mot de passe ou le username est incorrect"+"\"}");
		}
		throw new NotFoundException("Invalide token");
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Root");
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Root");
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Root");
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		throw new NotFoundException("Invalide Root");
	}
}
