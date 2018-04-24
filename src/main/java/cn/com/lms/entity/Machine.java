package cn.com.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="lms_machine")
public class Machine {
	
	
	@Id
	@GeneratedValue
	private int id ;
	
	@Column(name="mac")
	private String mac;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
	
	@Column(name="macNo")
	private String macNo;
	
	@Column(name="disabled")
	private boolean disabled;
	
	@Column(name="createTime")
	private Date createTime ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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

	public String getMacNo() {
		return macNo;
	}

	public void setMacNo(String macNo) {
		this.macNo = macNo;
	}
	
}
