package service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.BookImplFactory;
import factory.BookImplFactory.BookImplStyle;
import model.BookConcrete;
import service.Book;

public class BookTest {

	Book  b;
	@Before
	public void setUp() throws Exception {
		b = (Book) BookImplFactory.createBookImplementation(BookImplStyle.ARRAY);
		b.addBookToList(1,"GodFather", "Mario P.");
		b.addBookToList(new BookConcrete(2,"Principles", "Ray dalio."));
		b.addBookToList(new BookConcrete(3,"Black Swan", "Naseem Taleb"));
	}

	@After
	public void tearDown() throws Exception {
		b = null;
	}

	@Test
	public void testGetBookId() {
		assertTrue(b.findBookById(-1).get(0).getBookId() == 1);
	}

	@Test
	public void testGetBookName() {
		assertTrue(b.findBookById(-1).get(0).getBookName().equals("GodFather"));
	}

	@Test
	public void testfindBookById() {
		assertTrue(b.findBookById(1).size()==1);
		assertTrue(b.findBookById(-1).size()==3);
	}
	
	@Test
	public void testfindBookByName() {
		assertTrue(b.findBookByName("Principles").size()==1);
		assertTrue(b.findBookByName("Principles1").size()==0);
	}
	
	@Test
	public void testfindBookByAuthor() {
		assertTrue(b.findBookByAuthor("Ray dalio.").size()==1);
	}	

	@Test
	public void testdeleteBookById() {
		assertTrue(b.deleteBookById(1).size()==2);
		assertTrue(b.deleteBookById(-1).size()==2);
	}
	
	@Test
	public void testupdateBookBy() {
		assertTrue(b.findBookByName("XXX").size() == 0);
		assertTrue(b.updateBook(1,"XXX","YYYY").getBookName().equals("XXX"));
	}
	

	
	@Test
	public void tesBookCount() {
		assertTrue(b.bookCount() >=1);
	}
	
}
