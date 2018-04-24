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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.Mapping;

/**
 事件表 
 *
 */
@Entity
@Table(name="lms_event")
public class Event {
	
	@Id
	@GeneratedValue
	private int id ;
	
	/**
	 * 事件类型;
		1:新闻
		2：动态
		3：公告
	 */
	@Column(name="type")
	private int type;
	
	/**
	 * 是否为视频
	 *
	 */
	@Column(name="isVideo")
	private int isVideo;
	
	@Column(name="title")
	private String title;
	
	/**
	 * 内容
	 */
	@Column(name="content")
	private String content;
	
	/**
	 * 概要
	 */
	@Column(name="outline")
	private String outline;
	
	@ManyToOne
	@JoinColumn(name="school_id")
	private School school;
	
	/**
	 * 资源内容
	 */
	@OneToMany(cascade=CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name="event_id")
	private List<Media> medias;
	
	@Column(name="createTime")
	private Date createTime;
	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(int isVideo) {
		this.isVideo = isVideo;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}
	
	
	
	
	

}
