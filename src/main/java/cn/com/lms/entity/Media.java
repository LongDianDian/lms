package cn.com.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *资源表 
 *
 */
@Entity
@Table(name="lms_media")
public class Media {
	
	@Id
	@GeneratedValue
	private int id ;
	
	@Column(name="url")
	private String url;
	
	@Column(name="width")
	private Integer width;
	
	@Column(name="height")
	private Integer height;
	
	/**媒体信息类型
		Video :2
		Img:1
	 * 
	 */
	@Column(name="type")
	private int type ;
	
	/**
	 * 媒体资源使用类型
		2：图片/视频库
		1: 新闻/通知
	 * 默认1
	 */
	@Column(name="kind")
	private int kind ;
	
	@Column(name="event_id")
	private Integer eventId;
	
	@Column(name="library_id")
	private Integer libraryId;

	@Column(name="createTime")
	private Date createTime;

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

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}



}
