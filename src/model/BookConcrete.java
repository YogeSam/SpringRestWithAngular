package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries(  
		{  
	        @NamedQuery(  
	        name = "findBookByName",  
	        query = "from BookConcrete b where b.bookName = :bookName"  
	        )  
	    }  
)  


@Entity
@Table(name = "book")
public class BookConcrete implements Serializable{

	public BookConcrete(int bookId, String bookName, String authorName) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
	}
	
	public BookConcrete(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")	
	private int bookId;
	
	@Column(name = "bookName")
	private String bookName;
	
	@Column(name = "authorName")
	private String authorName;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
