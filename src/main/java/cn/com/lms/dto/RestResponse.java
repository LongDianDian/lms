package cn.com.lms.dto;

import java.util.HashMap;
import java.util.Map;

public class RestResponse {
	private int statusCode;
	private String message;
	private Map<String, Object> header;
	private Map<String, Object> body;

	public RestResponse() {
		this.header = new HashMap();
		this.body = new HashMap();
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getHeader() {
		return this.header;
	}

	public Map<String, Object> getBody() {
		return this.body;
	}

}
