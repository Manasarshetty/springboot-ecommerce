package springboot_ecommerce.project.e_commerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springboot_ecommerce.project.e_commerce.exception.ProductValidationException;
import springboot_ecommerce.project.e_commerce.model.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorCode("PRODUCT_NOT_FOUND");
        // timestamp is set in ErrorResponse constructor
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<ErrorResponse> handleProductValidationException(ProductValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorCode("PRODUCT_VALIDATION_ERROR");
        // timestamp is set in ErrorResponse constructor
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
