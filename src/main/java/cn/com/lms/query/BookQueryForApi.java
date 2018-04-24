package cn.com.lms.query;

public class BookQueryForApi {
	
	private Integer bookTypeId ;
	
	private String bookname ;
	
	private Integer shelveStatus ;
	
	private Integer pageStartNum ;
	
	private Integer pageSize;

	public Integer getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public Integer getShelveStatus() {
		return shelveStatus;
	}

	public void setShelveStatus(Integer shelveStatus) {
		this.shelveStatus = shelveStatus;
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

}
