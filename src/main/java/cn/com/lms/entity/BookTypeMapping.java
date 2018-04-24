package cn.com.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lms_book_type_mapping")
public class BookTypeMapping {

	@Id
	@GeneratedValue
	private int id ;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	/**
	 * 类型id
	 */
	@Column(name="book_type_id")
	private int bookTypeId;
	
	/**
	 * 上架状态：
	 *  1：上架
	 *	0：下架 
	 */
	@Column(name="shelve_status")
	private int shelveStatus;
	
	@Column(name="createtime")
	private Date createtime;
	
	@Column(name="bookOrder")
	private Integer bookOrder;
	

	public Integer getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(Integer bookOrder) {
		this.bookOrder = bookOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(int bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public int getShelveStatus() {
		return shelveStatus;
	}

	public void setShelveStatus(int shelveStatus) {
		this.shelveStatus = shelveStatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
