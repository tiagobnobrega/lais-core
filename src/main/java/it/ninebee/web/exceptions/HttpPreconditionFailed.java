package it.ninebee.web.exceptions;

import org.springframework.http.HttpStatus;

public class HttpPreconditionFailed extends HttpException {

	private static final long serialVersionUID = 239548840053193218L;

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.PRECONDITION_FAILED;
	}

	public HttpPreconditionFailed() {
		super();
	}

	public HttpPreconditionFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HttpPreconditionFailed(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpPreconditionFailed(String message) {
		super(message);
	}

	public HttpPreconditionFailed(Throwable cause) {
		super(cause);
	}
	

}
