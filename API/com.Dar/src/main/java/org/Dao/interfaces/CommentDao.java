package org.Dao.interfaces;

import java.util.List;

import org.Dao.GenericDao;
import org.Entite.Comment;
import org.Entite.Event;
import org.Entite.User;

public interface CommentDao extends GenericDao<Comment> {
	
	public List<Comment> getCommentsByUser(User user);
	public List<Comment> getCommentsByEvent(Event evt);	
}
