package rest.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import factory.BookImplFactory;
import factory.BookImplFactory.BookImplStyle;
import model.BookConcrete;
import service.DuplicateBookException;
import service.IBookList;

@RestController
@RequestMapping("/rest")
public class RestBookController {
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "getBookListService")
	IBookList b;
	
	@RequestMapping(value = "/Book", method = RequestMethod.GET)
	public ArrayList<BookConcrete> getBooks(){
		return b.findBookById(-1);
	}
	
	@RequestMapping(value = "/Book/{bookid}", method = RequestMethod.GET)
	public ArrayList<BookConcrete> getBooks(@PathVariable("bookid") int bookid){
		return  b.findBookById(bookid);
	}
	
	@RequestMapping(value = "/Book/{bookname}/{author}", method = RequestMethod.POST)
	public void addBook(@PathVariable("bookname") String bookName, @PathVariable("author") String authorName) throws DuplicateBookException{
		//int d = 6 / 0;
		BookConcrete book = new BookConcrete();
		book.setBookId(0);
		book.setBookName(bookName);
		book.setAuthorName(authorName);
		b.addBookToList(book);
	}	
		

	//@RequestMapping(value = "/Book", method = RequestMethod.POST)
	@PostMapping(path = "/Book")
	public void addBook(@RequestBody BookConcrete book)throws DuplicateBookException{
		//int d = 6 / 0;
		b.addBookToList(book);
	}		
	
	@RequestMapping(value = "/Book/{bookid}/{bookname}/{author}", method = RequestMethod.PUT)
	public void updateBook(@PathVariable("bookid") int bookid, @PathVariable("bookname") String bookname, @PathVariable("author") String author){
		b.updateBook(bookid, bookname, author);
	}	
	
	@PutMapping(path = "/Book")
	public void updateBook(@RequestBody BookConcrete book){
		b.updateBook(book);
	}		
	
	@RequestMapping(value = "/Book/{bookid}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("bookid")  int bookid){
		b.deleteBookById(bookid);
	}
	
	@RequestMapping(value = "/Book/Search/BookName/{bookname}", method = RequestMethod.GET)
	public ArrayList<BookConcrete> searchBooksByName( @PathVariable("bookname") String bookname){
		ArrayList<BookConcrete> list = null;
		list = b.findBookByName(bookname);
		//Search in author list
		list.addAll(b.findBookByAuthor(bookname));
		return list;
	}	
	
	@Bean
	private IBookList getBookListService(){
			return BookImplFactory.createBookImplementation(BookImplStyle.DAO);
	}
	
}
