package cn.com.lms.query;

import java.util.List;
import java.util.Map;

public class LibraryQueryForApi {
	
	
	private Integer type;
	
	private String title;
	
	private Integer schoolId;
	
	private Integer kind;
	
	private Integer pageStartNum ;
	
	private Integer pageSize;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getPageStartNum() {
		return pageStartNum;
	}

	public void setPageStartNum(Integer pageStartNum) {
		this.pageStartNum = pageStartNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	
	

}
