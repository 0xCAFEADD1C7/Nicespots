package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	 public HibernateUtil() {
	 }

	 public static SessionFactory getSessionFactory() {
	  if (sessionFactory != null) {
	   return sessionFactory;
	  }

	  synchronized (HibernateUtil.class) {
	   if (sessionFactory == null) {
	    StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
	      .configure("hibernate.cfg.xml").build();
	    Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
	    sessionFactory = metadata.getSessionFactoryBuilder().build();
	   }
	  }

	  return sessionFactory;
	 }
}
