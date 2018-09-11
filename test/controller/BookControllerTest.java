package controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import service.Book;
import uri.IBookURI;



public class BookControllerTest {
	@Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;    
    
    @Mock 
    ServletContext sc;
    
    
    Book b;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        b = new Book(1,"GodFather", "Mario P.");
    }	
	
	
	@Test
	public void testBookList() throws IOException, ServletException {
		    when(request.getRequestURI()).thenReturn("/Book");
		    when(request.getRequestDispatcher(IBookURI.VIEW_LISTBOOKURI)).thenReturn(rd);
		    when(request.getSession()).thenReturn(session);
		    
		 	//when(request.getParameter("fn")).thenReturn("Vinod");
	        //when(request.getParameter("ln")).thenReturn("Kashyap");

//	        StringWriter sw = new StringWriter();
//	        PrintWriter pw = new PrintWriter(sw);
	        
//	        when(response.getWriter()).thenReturn(pw);
		
	        BookController bc = new BookController();
	        bc.doGet(request, response);
	        
	        verify(rd).forward(request, response);
	}
	
	@Test
	public void testBookAdd() throws IOException, ServletException {
		    when(request.getRequestURI()).thenReturn(IBookURI.CTRL_ADDBOOKURI);
		    when(request.getRequestDispatcher(IBookURI.VIEW_ADDBOOKURI)).thenReturn(rd);
		
	        BookController bc = new BookController();
	        bc.doGet(request, response);
	        
	        verify(rd).forward(request, response);
	}	

	@Test
	public void testBookDelete() throws IOException, ServletException {
		    when(request.getRequestURI()).thenReturn(IBookURI.CTRL_DELBOOKURI);
		    when(request.getRequestDispatcher(IBookURI.VIEW_LISTBOOKURI)).thenReturn(rd);
		    when(request.getSession()).thenReturn(session);
		    when(session.getAttribute("Book")).thenReturn(b);
		    when(request.getParameter("bookid")).thenReturn("1");
		    
		    assertTrue(b.findBookById(1).size()==1);
		    
	        BookController bc = new BookController();
	        bc.doGet(request, response);
	        
	        verify(rd).forward(request, response);
	        assertTrue(b.findBookById(1).size()==0);	        
	        
	}		
	
	@Test
	public void testSaveforAdd() throws IOException, ServletException {
		    when(request.getRequestURI()).thenReturn(IBookURI.CTRL_SAVEBOOKURI);
		    when(request.getRequestDispatcher(IBookURI.VIEW_LISTBOOKURI)).thenReturn(rd);
		    when(request.getSession()).thenReturn(session);
		    when(session.getAttribute("Book")).thenReturn(b);
		    when(request.getParameter("bookid")).thenReturn("0");
		    when(request.getParameter("bookname")).thenReturn("ABC");
		    when(request.getParameter("authorname")).thenReturn("XYZ");
		    
		    assertTrue(b.findBookById(1).size()==1);

		    BookController bc = new BookController();
	        bc.doGet(request, response);
	        
	        verify(rd).forward(request, response);
	        assertTrue(b.findBookById(-1).size()==2);	        
	        
	}		
	
	@Test
	public void testSaveforUpdate() throws IOException, ServletException {
		    when(request.getRequestURI()).thenReturn(IBookURI.CTRL_UPDATEBOOKURI);
		    when(request.getRequestDispatcher(IBookURI.VIEW_UPDATEBOOKURI)).thenReturn(rd);
		    when(request.getSession()).thenReturn(session);
		    when(session.getAttribute("Book")).thenReturn(b);
		    when(request.getParameter("bookid")).thenReturn("1");
		    when(request.getParameter("bookname")).thenReturn("ABC");
		    when(request.getParameter("authorname")).thenReturn("XYZ");
		    
		    assertTrue(b.findBookById(1).size()==1);

		    BookController bc = new BookController();
	        bc.doGet(request, response);
	        
	        verify(rd).forward(request, response);
	        assertTrue(b.findBookById(-1).size()==1);	        
	        
	}	
	
	@Test
	public void testForException() throws IOException, ServletException {
		    when(request.getRequestURI()).thenReturn(IBookURI.CTRL_UPDATEBOOKURI);
		    when(request.getRequestDispatcher(IBookURI.VIEW_ERRORURI)).thenReturn(rd);
		    when(request.getSession()).thenReturn(session);
		    when(session.getAttribute("Book")).thenReturn(b);
		    when(request.getParameter("bookid")).thenReturn("");
		    when(request.getParameter("bookname")).thenReturn("ABC");
		    when(request.getParameter("authorname")).thenReturn("XYZ");
		    
		    assertTrue(b.findBookById(-1).size()==1);

		    BookController bc = new BookController();
	        bc.doGet(request, response);
	        
	        verify(rd).forward(request, response);
	        assertTrue(b.findBookById(-1).size()==1);	        
	        
	}	
}
