package rest.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import factory.BookImplFactory;
import factory.BookImplFactory.BookImplStyle;
import model.BookConcrete;
import service.IBookList;

@RestController
@RequestMapping("/")
public class RestBookController {
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/Book", method = RequestMethod.GET)
	public ArrayList<BookConcrete> getBooks(){
		ArrayList<BookConcrete> list = null;
		IBookList b = getBookListService();
		list = b.findBookById(-1);
		return list;
	}
	
	@RequestMapping(value = "/Book/{bookid}", method = RequestMethod.GET)
	public ArrayList<BookConcrete> getBooks(@PathVariable("bookid") int bookid){
		ArrayList<BookConcrete> list = null;
		IBookList b = getBookListService();
		list = b.findBookById(bookid);
		return list;
	}
	
	@RequestMapping(value = "/Book/{bookname}/{author}", method = RequestMethod.POST)
	public void addBook(@PathVariable("bookname") String bookName, @PathVariable("author") String authorName){
		//int d = 6 / 0;
		IBookList b = getBookListService();
		BookConcrete book = new BookConcrete();
		book.setBookId(0);
		book.setBookName(bookName);
		book.setAuthorName(authorName);
		b.addBookToList(book);
	}	
		

	//@RequestMapping(value = "/Book", method = RequestMethod.POST)
	@PostMapping(path = "/Book")
	public void addBook(@RequestBody BookConcrete book){
		//int d = 6 / 0;
		IBookList b = getBookListService();
		b.addBookToList(book);
	}		
	
	@RequestMapping(value = "/Book/{bookid}/{bookname}/{author}", method = RequestMethod.PUT)
	public void updateBook(@PathVariable("bookid") int bookid, @PathVariable("bookname") String bookname, @PathVariable("author") String author){
		IBookList b = getBookListService();
		b.updateBook(bookid, bookname, author);
	}	
	
	@RequestMapping(value = "/Book/{bookid}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("bookid")  int bookid){
		IBookList b = getBookListService();
		b.deleteBookById(bookid);
	}
	
	private IBookList getBookListService(){
		HttpSession session = request.getSession();
		IBookList b = (IBookList)session.getAttribute("Book");
		if(b == null){
			b = BookImplFactory.createBookImplementation(BookImplStyle.DAO);
			session.setAttribute("Book",b);
		}
		return b;
	}
	
}
