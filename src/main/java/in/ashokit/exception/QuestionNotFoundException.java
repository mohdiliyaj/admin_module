package in.ashokit.exception;

public class QuestionNotFoundException extends RuntimeException {
	
	public QuestionNotFoundException() {
	}
	
	public QuestionNotFoundException(String msg) {
		super(msg);
	}
}
