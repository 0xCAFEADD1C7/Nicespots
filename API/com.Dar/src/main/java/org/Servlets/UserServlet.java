package org.Servlets;

import org.Dao.interfaces.UserDao;
import org.Entite.User;
import org.utils.DAOFactory;

public class UserServlet extends SimpleAbstractServlet<User> {
	private static final long serialVersionUID = 1L;
  
    public UserServlet() {
        super();
        klass = User.class;
    }

	@Override
	protected UserDao getDAO() {
		return DAOFactory.getUser();
	}
	
}
