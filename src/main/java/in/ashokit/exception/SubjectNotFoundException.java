package in.ashokit.exception;


public class SubjectNotFoundException extends RuntimeException{
	
	public SubjectNotFoundException() {
	}
	
	public SubjectNotFoundException(String msg) {
		super(msg);
	}
}
