package in.ashokit.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = SubjectNotFoundException.class)
	public ResponseEntity<ExInfo> subjectNotFound(SubjectNotFoundException e){
		ExInfo info = new ExInfo();
		info.setExCode("404");
		info.setExMsg(e.getMessage());
		info.setExDate(LocalDate.now());
		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<ExInfo> categoryNotFound(CategoryNotFoundException e){
		ExInfo info = new ExInfo();
		info.setExCode("404");
		info.setExMsg(e.getMessage());
		info.setExDate(LocalDate.now());
		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = QuestionNotFoundException.class)
	public ResponseEntity<ExInfo> questionNotFound(QuestionNotFoundException e){
		ExInfo info = new ExInfo();
		info.setExCode("404");
		info.setExMsg(e.getMessage());
		info.setExDate(LocalDate.now());
		return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
	}
}
