package data;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import model.BookConcrete;

public class HibernateTest {
	static Session session = null;
			
	@BeforeClass
	public static void setUp() throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		HibernateUtil.shutdown();
	}	

	@Test
	public void testBookInHibernate() {
		//Add
		session.beginTransaction();
		BookConcrete b = new BookConcrete(2,"GodFather","Mario P.");
		//b.setBookId(2);
		//b.setBookName("GodFather");
		//b.setAuthorName("Mario P.");
        session.save(b);
        session.getTransaction().commit();
        
        //Read
		Query query=session.createQuery("from BookConcrete");    
	    List<BookConcrete> list=query.list();   
	    assertTrue(list.size()>0);
        
        //Delete
		session.beginTransaction();
		query=session.createQuery("delete from BookConcrete where id=2");  
		//specifying class name (BookConcrete) not tablename  
		query.executeUpdate();
        session.getTransaction().commit();
 	}	
	
	
}
