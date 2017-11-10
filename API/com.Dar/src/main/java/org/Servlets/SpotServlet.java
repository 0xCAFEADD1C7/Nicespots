package org.Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/spot/*")

public class SpotServlet extends AbstractCrudServlet {
	private static final long serialVersionUID = 1L;
       
    public SpotServlet() {
        super();
    }

	@Override
	protected String create(HttpServletRequest request) throws Exception {
		return null;
		
	}

	@Override
	protected String getOne(HttpServletRequest request) throws Exception {
		return null;
		
	}

	@Override
	protected String getAll(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String update(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String delete(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
