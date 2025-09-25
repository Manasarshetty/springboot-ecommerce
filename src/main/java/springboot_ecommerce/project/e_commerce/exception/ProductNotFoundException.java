package springboot_ecommerce.project.e_commerce.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Product not found with id="+id);
    }

}
