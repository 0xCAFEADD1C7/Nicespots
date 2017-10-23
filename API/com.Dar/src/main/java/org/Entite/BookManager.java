package org.Entite;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {

	protected SessionFactory sessionFactory;
	
	protected void setUp() {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	public void sessionStop() {
		sessionFactory.close();
	}
	public void create() {
		Book b = new Book(0,"titre 2","ziko",(float) 19.6);
		Session  session = (Session) sessionFactory.openSession();
		session.beginTransaction();
		session.save(b);
		session.getTransaction().commit();
		session.close();
	}
	
	public void recup() {
		Session session = sessionFactory.openSession();
	    
		Book bb= new Book();
		bb.setId(1);
		Book b =session.get(Book.class,bb.getId());
		System.out.println("Title : "+b.getTitle());
		System.out.println("Author : "+b.getAuthor());
		System.out.println("Price : "+b.getPrice());
		
		
		
		session.close();
	}
	
	public static void main(String[] args) {
		BookManager bm = new BookManager();
		bm.setUp();
		bm.recup();
		//bm.create();
		
		bm.sessionStop();
		
		
		

	}

}
