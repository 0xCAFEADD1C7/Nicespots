package org.Dao;

import java.util.List;

import org.Entite.Comment;
import org.Entite.Event;
import org.Entite.User;

public interface CommentDao {
	public List<Comment> getCommentByUser(User user);
	public List<Comment> getCommentByEvent(Event evt);	
}
