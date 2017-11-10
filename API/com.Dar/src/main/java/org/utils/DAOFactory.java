package org.utils;

import org.Dao.implement.CommentDaoImpl;
import org.Dao.implement.EventDaoImpl;
import org.Dao.implement.SpotDaoImpl;
import org.Dao.implement.SpotReviewDaoImpl;
import org.Dao.implement.UserDaoImpl;
import org.Dao.interfaces.CommentDao;
import org.Dao.interfaces.EventDao;
import org.Dao.interfaces.SpotDao;
import org.Dao.interfaces.SpotReviewDao;
import org.Dao.interfaces.UserDao;

public class DAOFactory {
	private static UserDao user = new UserDaoImpl();
	private static SpotDao spot = new SpotDaoImpl();
	private static CommentDao comment = new CommentDaoImpl();
	private static SpotReviewDao spotReview = new SpotReviewDaoImpl();
	private static EventDao event = new EventDaoImpl();
	
	public static UserDao getUser() {
		return user;
	}

	public static SpotDao getSpot() {
		return spot;
	}

	public static CommentDao getComment() {
		return comment;
	}

	public static SpotReviewDao getSpotReview() {
		return spotReview;
	}

	public static EventDao getEvent() {
		return event;
	}
	
}
