package factory;

import service.Book;
import service.BookDao;
import service.IBookList;

public class BookImplFactory {

	public static enum BookImplStyle{
		ARRAY, DAO;
	}
	
	public static IBookList createBookImplementation(BookImplStyle i){
		IBookList booklist = null;
		if(i == BookImplStyle.ARRAY){
			booklist = new Book();
		}else if(i == BookImplStyle.DAO){
			booklist = new BookDao();
		}
		return booklist;
	}
}
