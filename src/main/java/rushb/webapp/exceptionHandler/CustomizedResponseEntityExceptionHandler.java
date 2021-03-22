package rushb.webapp.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rushb.webapp.exception.*;

import java.util.Date;

@ControllerAdvice
@Controller
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception, WebRequest webRequest){
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                new Date(),
//                exception.getMessage(),
//                webRequest.getDescription(false)
//        );
//        // true or false includeClientInfo - whether to include client-specific information such as session id and user name
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(UserAlreadyExistException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBlogNotFoundException(BlogNotFoundException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTagNotFoundException(TagNotFoundException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }




}
