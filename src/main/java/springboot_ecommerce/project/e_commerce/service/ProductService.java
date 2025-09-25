package springboot_ecommerce.project.e_commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_ecommerce.project.e_commerce.exception.ProductNotFoundException;
import springboot_ecommerce.project.e_commerce.exception.ProductValidationException;
import springboot_ecommerce.project.e_commerce.model.Product;
import springboot_ecommerce.project.e_commerce.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }
    public Product saveProduct( Product product) throws ProductValidationException {
        if(product.getName()!=null){
            return productRepository.save(product);
        }else{
            throw new ProductValidationException("Product cannot be null");
        }

    }
    public Product updateProduct(Long id, Product productDetails) throws ProductNotFoundException {
        try {
            if(id<=0){
                throw new ProductNotFoundException(id);
            }
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(id);
        }Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            Product saved = productRepository.save(existingProduct);
            return saved;
        } else {
             throw new RuntimeException();
        }
    }
    public String deleteProductById(Long id){
        productRepository.deleteById(id);
        return "Succesfully deleted id"+id;
    }
    public String deleteAllProducts(Boolean confirm){
        if (Boolean.TRUE.equals(confirm)) {
            productRepository.deleteAll();
            return "All products deleted";
        } else {
            return "Deletion of all products requires `?confirm=true`";
        }
    }
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }


}
