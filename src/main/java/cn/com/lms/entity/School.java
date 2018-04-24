package cn.com.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lms_school")
public class School {

	@Id
	@GeneratedValue
	private int id ;
	
	@Column(name="name")
	private String name;
	
	@Column(name="head_posturl")
	private String headPostUrl ;
	
	@Column(name="foot_posturl")
	private String footPostUrl;
	
	/**
	 * 校训
	 */
	@Column(name="school_motto")
	private String schoolMotto;
	
	@Column(name="createtime")
	private Date createtime;
	
	@Column(name="updatetime")
	private Date updatetime;
	
	@Column(name="isDelete")
	private boolean isDelete;

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

	public String getHeadPostUrl() {
		return headPostUrl;
	}

	public void setHeadPostUrl(String headPostUrl) {
		this.headPostUrl = headPostUrl;
	}

	public String getFootPostUrl() {
		return footPostUrl;
	}

	public void setFootPostUrl(String footPostUrl) {
		this.footPostUrl = footPostUrl;
	}

	public String getSchoolMotto() {
		return schoolMotto;
	}

	public void setSchoolMotto(String schoolMotto) {
		this.schoolMotto = schoolMotto;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
