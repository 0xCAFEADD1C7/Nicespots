package org.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.exceptions.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract CRUD servlet :
 * used as a base for CRUD servlets. It forces the inherited servlet to implement
 * CRUD operations + handles some basic tasks such as exception handling, GET routing, etc
 */

public abstract class AbstractCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AbstractCrudServlet() {
		super();
	}


	/** These functions take the HTTPRequest as a param, and must return the string that 
	 * will be sent to the client.
	 * If something goes wrong, 
	 * @param request
	 * @return
	 */
	// TODO : pass Headers, JSON body, Query and Context as parameters
	// rather than default servlet req ???
	protected abstract String create(HttpServletRequest request) throws Exception;
	protected abstract String getOne(HttpServletRequest request) throws Exception;    
	protected abstract String getAll(HttpServletRequest request) throws Exception;    
	protected abstract String update(HttpServletRequest request) throws Exception;
	protected abstract String delete(HttpServletRequest request) throws Exception;
	
	// NOTE : override this function do finer route handling for get
	protected boolean isGetOne(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int nbSlash = uri.length() - uri.replace("/", "").length();
		return nbSlash == 3;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			if (isGetOne(request)) {
				out.println(getOne(request));
			} else {
				out.println(getAll(request));
			}
		} catch(Exception e) {
			handleError(e, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().println(create(request));
		} catch(Exception e) {
			handleError(e, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().println(update(request));
		} catch(Exception e) {
			handleError(e, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().println(delete(request));
		} catch(Exception e) {
			handleError(e, response);
		}
	}

	// needs to be improved to better describe the error + better status code handling
	protected void handleError(Throwable err, HttpServletResponse res) throws IOException {
		err.printStackTrace();
		
		PrintWriter out = res.getWriter();
		String errJson = "{ \"error\" : \"%s\" }";
		String message = null;
		int status;
		
		if (err instanceof NullPointerException || err instanceof ArrayIndexOutOfBoundsException) {
			status = 500;
			message = "Internal Server Error";
		} else if (err instanceof NotFoundException) {
			status = 404;
		} else if (err instanceof ConstraintViolationException) {
			status = 409;
		} else {
			status = 400;
		}	
		
		if (message == null) {
			message = err.getMessage();
		}
		
		String escapedError = (message != null ? message : "")
				.replaceAll("\"", "\\\\\"");
		res.setStatus(status);
		out.format(errJson, escapedError);
	}

	// HELPERS
	//TODO move this in another class
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

	public boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
	}

	public String getParam(HttpServletRequest req, int i) {
		return req.getRequestURI().split("/")[i];
	}
	
	public int getIDParam(HttpServletRequest request) {
		String uid = getParam(request, 3);
		return Integer.parseInt(uid);
	}
}
