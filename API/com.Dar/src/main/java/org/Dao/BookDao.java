package org.Dao;

import java.util.ArrayList;

import org.Entite.Book;

public interface BookDao {

	public void addBook(Book book);
	public Book getBook(int id);
	public ArrayList<Book> listBook();
}
