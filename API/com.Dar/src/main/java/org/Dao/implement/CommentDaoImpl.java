package org.Dao.implement;

import java.util.List;

import org.Dao.DaoImpl;
import org.Dao.interfaces.CommentDao;
import org.Entite.Comment;
import org.Entite.Event;
import org.Entite.User;

public class CommentDaoImpl extends DaoImpl<Comment> implements CommentDao {

	public List<Comment> getCommentsByUser(User user) {
		return getAllBy("user", user.getIdUser());
	}

	public List<Comment> getCommentsByEvent(Event evenement) {
		return getAllBy("evenement", evenement.getIdEvent());
	}

}
