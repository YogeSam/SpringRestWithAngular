package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@NamedQueries(  
		{  
	        @NamedQuery(  
	        name = "findBookByName",  
	        query = "from BookConcrete b where b.bookName like :bookName"  
	        ),
	        @NamedQuery(  
	        name = "findBookByAuthor",  
	        query = "from BookConcrete b where b.authorName like :authorname"
	        )
	    }  
)  


@Entity

@Table(name = "book")
public class BookConcrete implements Serializable{
	
	public BookConcrete() {
		// TODO Auto-generated constructor stub
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
	
	@Temporal(TemporalType.DATE)
	@JsonProperty("publisheddate")	
	@Column(name = "publisheddate")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="EST")
	private Date publisheddate = new java.util.Date();

	
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
	
	
	public Date getPublishedDate() {
		return publisheddate;
	}
	
	public void setPublishedDate(Date dateofpublished) throws Exception {
		this.publisheddate =dateofpublished;
	}
	
}
