package service;

import java.util.ArrayList;

import model.BookConcrete;


public class Book implements IBookList{

	ArrayList<BookConcrete> bookList = new ArrayList<BookConcrete>();
	
	public Book(int bookId, String bookName, String authorName) {
		BookConcrete b = new BookConcrete();
		b.setBookId(bookId);
		b.setAuthorName(authorName);
		b.setBookName(bookName);
		
		bookList.add(b);
	}
	
	public void addBookToList(int bookId, String bookName, String authorName) {
		BookConcrete b = new BookConcrete();
		b.setBookId(bookId);
		b.setAuthorName(authorName);
		b.setBookName(bookName);
		
		bookList.add(b);
	}
	
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BookConcrete addBookToList(BookConcrete b) {
		bookList.add(b);
		return b;
	}
	

	
	@Override
	public ArrayList<BookConcrete> findBookById(int id) {
		// TODO Auto-generated method stub
		ArrayList<BookConcrete> bList = null;
		
		if(id == -1)
			bList = new ArrayList<BookConcrete>(bookList);
		else {
			bList =	new ArrayList<BookConcrete>();
			for(BookConcrete b : bookList){
				if(b.getBookId() == id ){
					bList.add(b);
				}
			}
		}
		return bList;
	}

	@Override
	public ArrayList<BookConcrete> findBookByName(String bookName) {
		// TODO Auto-generated method stub
		ArrayList<BookConcrete> bList = new ArrayList<BookConcrete>();
		for(BookConcrete b : bookList){
			if(b.getBookName().equals(bookName)){
				bList.add(b);
			}
		}
		
		return bList;
	}

	@Override
	public ArrayList<BookConcrete> findBookByAuthor(String authorName) {
		// TODO Auto-generated method stub
		ArrayList<BookConcrete> bList = new ArrayList<BookConcrete>();
		for(BookConcrete b : bookList){
			if(b.getAuthorName().equals(authorName)){
				bList.add(b);
			}
		}
		return bList;
	}

	@Override
	public ArrayList<BookConcrete> deleteBookById(int id) {
		// TODO Auto-generated method stub
		for(BookConcrete b : bookList){
			if(b.getBookId() == id){
				bookList.remove(bookList.indexOf(b));
				break;
			}
		}
		return bookList;
	}

	@Override
	public BookConcrete updateBook(int id, String bookName, String authorName) {
		// TODO Auto-generated method stub
		BookConcrete book = null;
		for(BookConcrete b : bookList){
			if(b.getBookId() == id){
				b.setAuthorName(authorName);
				b.setBookName(bookName);
				book = b;
				break;
			}
		}
		return book;	
	}

	@Override
	public long bookCount() {
		// TODO Auto-generated method stub
		return bookList.size();
	}


	
}
