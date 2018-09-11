package data;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controller.BookController;

public class HibernateUtil {
	final static Logger logger = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory = null;
	 
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
        	logger.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory =  buildSessionFactory();
    }
 
    public static void shutdown() {
        // Close caches and connection pools
    	sessionFactory.close();
    }
}
