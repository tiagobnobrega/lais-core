package it.ninebee.web.exceptions;

import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends HttpException {
	private static final long serialVersionUID = 1L;

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.TOO_MANY_REQUESTS;
	}
	
	public TooManyRequestsException() {
		super();
	}

	public TooManyRequestsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TooManyRequestsException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooManyRequestsException(String message) {
		super(message);
	}

	public TooManyRequestsException(Throwable cause) {
		super(cause);
	}
}
