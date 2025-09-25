package springboot_ecommerce.project.e_commerce.exception;

public class ProductValidationException extends RuntimeException {
  public ProductValidationException(String message) {

    super(message);
  }
}
