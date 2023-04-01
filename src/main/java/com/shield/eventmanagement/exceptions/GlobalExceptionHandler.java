package com.shield.eventmanagement.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shield.eventmanagement.exceptions.attendee.AttendeeException;
import com.shield.eventmanagement.exceptions.event.EventNotFoundException;
import com.shield.eventmanagement.exceptions.location.LocationNotFoundException;
import com.shield.eventmanagement.exceptions.organizer.OrganizerNotFoundException;
import com.shield.eventmanagement.exceptions.user.UserNotFoundException;
import com.shield.eventmanagement.payload.ValidationsErrorResponse;
import com.shield.eventmanagement.payload.organizer.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(field, message);
		});

		ValidationsErrorResponse response = new ValidationsErrorResponse(errors, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrganizerNotFoundException.class)
	public ResponseEntity<?> organizerNotFound(OrganizerNotFoundException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> userNameNotFoundHadler(UsernameNotFoundException ex) {
		ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<?> invalidExceptionHadler(InvalidException ex) {
		ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST)
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFound(UserNotFoundException ex) {
		ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AttendeeException.class)
	public ResponseEntity<?> attendeeRequestException(AttendeeException ex) {
		ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST)
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<?> eventNotFoundExceptionHandler(EventNotFoundException ex) {
		ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LocationNotFoundException.class)
	public ResponseEntity<?> locationNotFoundExceptionHandler(LocationNotFoundException ex) {
		ErrorResponse response = ErrorResponse.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
