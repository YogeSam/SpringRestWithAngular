package service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.BookImplFactory;
import factory.BookImplFactory.BookImplStyle;
import model.BookConcrete;
import service.BookDao;


public class BookDaoTest {

	BookDao b;
	BookConcrete book = new BookConcrete(1, "Silence of Lambs", "Thomas Harris");
	
	@Before
	public void setUp() throws Exception {
		b = (BookDao) BookImplFactory.createBookImplementation(BookImplStyle.DAO);
		b.addBookToList(book);
	}

	@After
	public void tearDown() throws Exception {
		b.deleteBookById(book.getBookId());
		b=null;
		book = null;
	}
	
	@Test
	public void testFindBookById() {
		
		assertTrue(b.findBookByAuthor(book.getAuthorName()).size()==1);
		assertTrue(b.findBookById(-1).size()>=1);
		
	}

	@Test
	public void testFindBookByName() {
		assertTrue(b.findBookByName(book.getBookName()).size()==1);
		assertTrue(b.findBookByName("Wrong Name").size()==0);
	}

	@Test
	public void testFindBookByAuthor() {
		assertTrue(b.findBookByAuthor(book.getAuthorName()).size()==1);
		assertTrue(b.findBookByAuthor("Wrong Name").size()==0);
	}

	@Test
	public void testDeleteBookById() {
		assertTrue(b.findBookById(-1).size()>=1);
	}

	@Test
	public void testUpdateBook() {
		assertTrue(b.findBookById(book.getBookId()).size()==1);
		b.updateBook(book.getBookId(), "Silence of Lambs1", "Thomas Harris1");
		assertTrue(b.findBookByAuthor("Thomas Harris1").size()==1);
		assertTrue(b.findBookByName("Silence of Lambs1").size()==1);
	}
	
	@Test
	public void tesBookCount() {
		assertTrue(b.bookCount() >=1);
	}
	

}
