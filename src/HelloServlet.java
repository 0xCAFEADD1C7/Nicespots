import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.* ;

public class HelloServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public void service (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter() ;
			out.println ("<!DOCTYPE html>") ;
			out.println ("<title>Bonjour tout le monde !</title>") ;
			out.println ("<p>Hello world!</p>") ;
		} catch (IOException e) {
			e.printStackTrace() ;
		}
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		service ((HttpServletRequest) request, (HttpServletResponse) response);

	}
}