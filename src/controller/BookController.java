package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

import factory.BookImplFactory;
import factory.BookImplFactory.BookImplStyle;
import model.BookConcrete;
import service.BookDao;
import service.DuplicateBookException;
import service.IBookList;
import uri.IBookURI;

/**
 * Servlet implementation class BookController
 */
@WebServlet({ "/Book", "/Book/Add", "/Book/Delete", "/Book/Update", "/Book/Save" })
public class BookController extends HttpServlet {
	final static Logger logger = Logger.getLogger(BookController.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ServletContext ctx = getServletContext();
	String strOp = IBookURI.VIEW_LISTBOOKURI;
	try{		
		String strURI = request.getRequestURI();
		if(strURI.indexOf(IBookURI.CTRL_ADDBOOKURI) >= 0){
			strOp =  IBookURI.VIEW_ADDBOOKURI;
		}else if(strURI.indexOf(IBookURI.CTRL_DELBOOKURI) >= 0){
			deleteBook(request);
		}else if(strURI.indexOf(IBookURI.CTRL_SAVEBOOKURI) >= 0){
			saveBook(request);
		}else if(strURI.indexOf(IBookURI.CTRL_UPDATEBOOKURI) >= 0){
			updateBook(request);			
			strOp =  IBookURI.VIEW_UPDATEBOOKURI;
		}else{
			addBook(request);
		}
	}catch(Exception e){
		logger.error(e);
		strOp =  IBookURI.VIEW_ERRORURI;
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher(strOp);
	dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void addBook(HttpServletRequest request){
		HttpSession session = request.getSession();
		IBookList b = (IBookList)session.getAttribute("Book");
		if(b == null){
			b = BookImplFactory.createBookImplementation(BookImplStyle.DAO);
		}
		session.setAttribute("Book",b);
	}

	private void saveBook(HttpServletRequest request) throws DuplicateBookException{
		HttpSession session = request.getSession();
		IBookList b = (IBookList)session.getAttribute("Book");
		
		int bookId = 0;
		boolean bAdd = false;
		String strBookId = (String)request.getParameter("bookid");
		if(strBookId == null || "0".equals(strBookId)){
			bAdd = true;
			bookId = (int)(Math.random()*1000%100);
		}else{
			bookId = Integer.parseInt(strBookId);
		}
		String bookName = (String)request.getParameter("bookname");
		String authorName = (String)request.getParameter("authorname");
	
		if(bAdd){
			BookConcrete book = new BookConcrete();
			book.setBookId(bookId);
			book.setAuthorName(authorName);
			book.setBookName(bookName);
			b.addBookToList(book);
		}else{
			b.updateBook(bookId,bookName,authorName);
		}
		
		session.setAttribute("Book",b);
	}	
	
	private void deleteBook(HttpServletRequest request){
		HttpSession session = request.getSession();
		IBookList b = (IBookList)session.getAttribute("Book");
		int bookId = Integer.parseInt((String)request.getParameter("bookid"));
		b.deleteBookById(bookId);
		session.setAttribute("Book",b);
	}	
	
	private void updateBook(HttpServletRequest request){
		HttpSession session = request.getSession();
		IBookList b = (IBookList)session.getAttribute("Book");
		int bookId = Integer.parseInt((String)request.getParameter("bookid"));
		String bookname = "";
		String authorname = "";
		ArrayList<BookConcrete> list = b.findBookById(bookId);
		if(list.size() > 0){
			bookname = list.get(0).getBookName();
			authorname = list.get(0).getAuthorName();
		}
		request. setAttribute("bookname", bookname);
		request. setAttribute("authorname", authorname);
	}	
	
	
}
