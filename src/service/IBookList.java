package service;

import java.util.ArrayList;

import model.BookConcrete;

public interface IBookList {

	public ArrayList<BookConcrete> findBookById(int id);
	public ArrayList<BookConcrete> findBookByName(String bookName);
	public ArrayList<BookConcrete> findBookByAuthor(String authorName);
	public ArrayList<BookConcrete> deleteBookById(int id);
	public BookConcrete updateBook(int id, String bookName, String authorName);
	public BookConcrete addBookToList(BookConcrete b);
	public long bookCount();
	
}
