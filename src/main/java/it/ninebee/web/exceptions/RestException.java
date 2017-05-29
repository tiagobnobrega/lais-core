package it.ninebee.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "stackTrace", "localizedMessage", "httpRequest", "includeCause" })
public class RestException extends Exception {

	private static final long serialVersionUID = 3887835014504168338L;
	public static boolean CLEAR_STACK_TRACE = true;
	public static boolean DEFAULT_INCLUDE_CAUSE = false;
	
	private HttpServletRequest httpRequest;
	private String causedBy;
	private boolean includeCause = DEFAULT_INCLUDE_CAUSE;

	public RestException() {
		super();
		init();
	}

	public RestException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		init(cause);
	}

	public RestException(String message, Throwable cause) {
		super(message, cause);
		init(cause);
	}

	public RestException(String message) {
		super(message);
		init();
	}

	public RestException(Throwable cause) {
		super(cause.getMessage());
		init(cause);
	}

	private void init(){
		init(this);
	}
	private void init(Throwable cause){
		defineCausedBy(cause);
		clearStackTrace(this);
	}
	
	protected void defineCausedBy(Throwable cause){
		if (cause==null) cause = this; 
		this.causedBy = cause.getClass().getCanonicalName();	
	}
	
	protected void clearStackTrace(Throwable cause) {
		if (!CLEAR_STACK_TRACE)
			return;
		StackTraceElement el = cause.getStackTrace()[0];
		cause.setStackTrace(new StackTraceElement[] { el });
		Throwable chidlCause = cause.getCause();
		if (chidlCause != null) {
			clearStackTrace(chidlCause);
		}
	}

	public Map<String, Object> getRequest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contentType", httpRequest.getContentType());
		map.put("contentLength", httpRequest.getContentLength());
		map.put("url", httpRequest.getRequestURL());
		map.put("remoteAddr", httpRequest.getRemoteAddr());
		map.put("localAddr", httpRequest.getLocalAddr());
		map.put("remoteHost", httpRequest.getRemoteHost());
		map.put("queryString", httpRequest.getQueryString());
		return map;
	}

	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public boolean isIncludeCause() {
		return includeCause;
	}

	public void setIncludeCause(boolean includeCause) {
		this.includeCause = includeCause;
	}

	public String getCausedBy() {
		return causedBy;
	}
	
	@Override
	public synchronized Throwable getCause() {
		if (includeCause == false)
			return null;
		return super.getCause();
	}
}