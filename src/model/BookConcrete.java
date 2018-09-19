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

import com.fasterxml.jackson.annotation.JsonProperty;


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
	
	public BookConcrete() {
		// TODO Auto-generated constructor stub
	}
	
	public BookConcrete(int bookid, String bookname, String authorname){
		this.bookId = bookid;
		this.bookName = bookname;
		this.authorName = authorname;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")	
	@JsonProperty("bookId")	
	private int bookId;
	
	@JsonProperty("bookName")		
	@Column(name = "bookName")
	private String bookName;
	
	@JsonProperty("authorName")	
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
