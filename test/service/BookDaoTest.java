package service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import factory.BookImplFactory;
import factory.BookImplFactory.BookImplStyle;
import model.BookConcrete;
import service.BookDao;


public class BookDaoTest {

	BookDao b;
	BookConcrete book = new BookConcrete();
	
	
	
	@Before
	public void setUp() throws Exception {
		book.setBookId(1);
		String bookname = "Silence of Lambs" + Math.random(); 
		book.setBookName(bookname);
		book.setAuthorName("Thomas Harris");
		book.setPublishedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
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
		
		assertTrue(b.findBookByAuthor(book.getAuthorName()).size()>=1);
		assertTrue(b.findBookById(-1).size()>=1);
		
	}

	@Test
	public void testFindBookByName() {
		assertTrue(b.findBookByName(book.getBookName()).size()==1);
		assertTrue(b.findBookByName("Silence").size()>=1);
		assertTrue(b.findBookByName("Wrong Name").size()==0);
	}

	@Test
	public void testFindBookByAuthor() {
		assertTrue(b.findBookByAuthor(book.getAuthorName()).size()>=1);
		assertTrue(b.findBookByAuthor("Thomas").size()>=1);
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
		assertTrue(b.findBookByAuthor("Thomas Harris1").size()>=1);
		assertTrue(b.findBookByName("Silence of Lambs1").size()>=1);
		b.updateBook(book);
		assertTrue(b.findBookByName(book.getBookName()).size()>=1);
	}
	
	@Test
	public void tesBookCount() {
		assertTrue(b.bookCount() >=1);
	}
	
	@Test
	public void testDuplicateBook() {
		try{
			b.addBookToList(book);
			fail("Expected an DuplicateBookException to be thrown");
		}catch(DuplicateBookException ex){
			assertTrue(ex.getMessage().indexOf("present")>=0);
		}
		
	}
	
	
	@Test
	public void testBookContent() {
		BookConcrete b1 = b.findBookById(book.getBookId()).get(0);
		assertTrue(b1.getBookName().equals(book.getBookName()));
		assertTrue(b1.getAuthorName().equals(book.getAuthorName()));
		assertTrue(b1.getPublishedDate().compareTo(book.getPublishedDate()) == 0);
	}
}
