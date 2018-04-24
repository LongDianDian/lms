package cn.com.lms.query;

public class BookMappingQuery {
	
	private String bookname ;
	
	private String author;
	
	private String press;
	
	private Integer publicationDate;
	
	private Integer bookTypeId;
	
	private Integer draw ;
	
	private Integer start ;
	
	private Integer length;

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
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

	public Integer getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
