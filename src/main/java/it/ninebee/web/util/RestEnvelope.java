package it.ninebee.web.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.ninebee.web.exceptions.RestException;


public class RestEnvelope implements Serializable {

	private static final long serialVersionUID = -3897061694196921363L;
	private Integer status;
	@JsonInclude(Include.NON_NULL)
	private Object data;
	@JsonInclude(Include.NON_NULL)
	private RestException error;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public RestException getError() {
		return error;
	}
	public void setError(RestException error) {
		this.error = error;
	}
	
	
}
