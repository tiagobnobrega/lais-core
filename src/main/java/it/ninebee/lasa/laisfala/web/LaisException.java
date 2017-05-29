package it.ninebee.lasa.laisfala.web;

public class LaisException extends Exception {

	private static final long serialVersionUID = 1389049386712035123L;

	public LaisException() {
		super();
	}

	public LaisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LaisException(String message, Throwable cause) {
		super(message, cause);
	}

	public LaisException(String message) {
		super(message);
	}

	public LaisException(Throwable cause) {
		super(cause);
	}

	
	
}
