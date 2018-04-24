package cn.com.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *图书实体
 */
@Entity
@Table(name="lms_book")
public class Book {
	
	@Id
	@GeneratedValue
	private int id ;
	
	@Column(name="bookname")
	private String bookname ;
	
	@Column(name="book_poster")
	private String bookPoster;
	
	@Column(name="book_url")
	private String bookUrl;
	
	/**
	 * 作者
	 */
	@Column(name="author")
	private String author;
	
	/**
	 * 出版社
	 */
	@Column(name="press")
	private String press ;
	
	/**
	 * 出版时间
	 */
	@Column(name="publication_date")
	private Integer publicationDate;
	
	/**
	 * 简介
	 */
	@Column(name="description")
	private String description ;
	
	private Date createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Integer getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Integer publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getBookPoster() {
		return bookPoster;
	}

	public void setBookPoster(String bookPoster) {
		this.bookPoster = bookPoster;
	}
	
	
}
