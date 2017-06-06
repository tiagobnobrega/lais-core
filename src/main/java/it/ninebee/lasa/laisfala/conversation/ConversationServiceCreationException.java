package it.ninebee.lasa.laisfala.conversation;

public class ConversationServiceCreationException extends Exception {

	private static final long serialVersionUID = 2999964850639941820L;

	public ConversationServiceCreationException() {
		super();
	}

	public ConversationServiceCreationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConversationServiceCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConversationServiceCreationException(String message) {
		super(message);
	}

	public ConversationServiceCreationException(Throwable cause) {
		super(cause);
	}
}
