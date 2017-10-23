package org.Dao;

import java.util.ArrayList;

import org.Entite.Book;
import org.hibernate.Session;

import util.HibernateUtil;

public class BookDaoImpl extends DaoImpl implements BookDao{


	public void addBook(Book book) {
				super.add(book);
			
	}

	public Book getBook(int id) {
			
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Book bb =session.get(Book.class,id);
		session.close();
		return bb;
		
	}

	public ArrayList<Book> listBook() {
		// TODO Auto-generated method stub
		return null;
	}

}
