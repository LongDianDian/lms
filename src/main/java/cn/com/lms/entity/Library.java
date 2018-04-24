package cn.com.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 *图片、视频库表
 */
@Entity
@Table(name="lms_library")
public class Library {
	
	@Id
	@GeneratedValue
	private int id ;
	
	/*
	 * 事件类型;
		1:图片
		2：视频
	 */
	@Column(name="type")
	private int type;
	
	@Column(name="kind")
	private int kind;
	
	@Column(name="title")
	private String title;
	
	@Column(name="createTime")
	private Date createTime;
	
	@ManyToOne
	@JoinColumn(name="school_id")
	private School school;
	
	@OneToMany(cascade=CascadeType.ALL )
	@JoinColumn(name="library_id")
	private List<Media> medias;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

}
