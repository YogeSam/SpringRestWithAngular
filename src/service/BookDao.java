package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import data.HibernateUtil;
import model.BookConcrete;


public class BookDao implements IBookList{
	

	@Override
	public ArrayList<BookConcrete> findBookById(int id) {
		ArrayList<BookConcrete> list = new ArrayList<BookConcrete>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		if(id == -1){
			Query query=session.createQuery("from BookConcrete order by bookName");    
		    list= new ArrayList<BookConcrete>(query.list());
		}else{
			Query query=session.createQuery("from BookConcrete where Id=" + id);    
		    list= new ArrayList<BookConcrete>(query.list());
		}
		HibernateUtil.shutdown();
		return list;
	}

	@Override
	public ArrayList<BookConcrete> findBookByName(String bookname) {
		ArrayList<BookConcrete> list = new ArrayList<BookConcrete>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=session.getNamedQuery("findBookByName");      
		query.setParameter("bookName","%" + bookname + "%" );
		list= new ArrayList<BookConcrete>(query.list());
		HibernateUtil.shutdown();
		return list;
	}

	@Override
	public ArrayList<BookConcrete> findBookByAuthor(String authorName) {
		ArrayList<BookConcrete> list = new ArrayList<BookConcrete>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Query query=session.createQuery("from BookConcrete where authorname like :authorname");
		Query query=session.getNamedQuery("findBookByAuthor");   
		query.setParameter("authorname","%" + authorName + "%" );
	    list= new ArrayList<BookConcrete>(query.list());
		HibernateUtil.shutdown();
		return list;
	}

	@Override
	public ArrayList<BookConcrete> deleteBookById(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if(id == -1){
			Query query=session.createQuery("delete from BookConcrete");  
			query.executeUpdate();
		}else{
			Query query=session.createQuery("delete from BookConcrete where id=" + id);  
			query.executeUpdate();
		}
        session.getTransaction().commit();
		HibernateUtil.shutdown();
		return findBookById(-1);		
	}

	@Override
	public BookConcrete updateBook(int bookId, String bookName, String authorName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		BookConcrete b = new BookConcrete();
		b.setBookId(bookId);
		b.setAuthorName(authorName);
		b.setBookName(bookName);
		
		session.beginTransaction();
		session.update(b);
        session.getTransaction().commit();
		HibernateUtil.shutdown();
		return b;		
		
	}

	@Override
	public BookConcrete addBookToList(BookConcrete b) throws DuplicateBookException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		if(findBookByName(b.getBookName()).size() >= 1)
			throw new DuplicateBookException(b.getBookName() + " already present.");
		
		session.beginTransaction();
		session.save(b);
        session.getTransaction().commit();
		HibernateUtil.shutdown();
		return b;
	}
	
	public long bookCount(){
		long count = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=session.createQuery("select count(id) from BookConcrete");  
		List<Long> list=query.list();  
		count =  list.get(0);  
		return count;
	}

	@Override
	public BookConcrete updateBook(BookConcrete b) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(b);
        session.getTransaction().commit();
		HibernateUtil.shutdown();
		return b;
	}

}
