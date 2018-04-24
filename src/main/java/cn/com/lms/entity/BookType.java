package cn.com.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图书分类
 *
 */
@Entity
@Table(name="lms_book_type")
public class BookType {
	
	@Id
	@GeneratedValue
	private Integer id ;
	
	@Column(name="name")
	private String name;
	
	/**
	 * 排序
	 */
	@Column(name="typeOrder")
	private int order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
	

}
