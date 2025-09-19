package springboot_ecommerce.project.e_commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Product saveProduct(Long id, Product product){
        return productRepository.save(product);
    }
    public Optional<Product> updateProduct(Long id, Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            Product saved = productRepository.save(existingProduct);
            return Optional.of(saved);
        } else {
            return Optional.empty();
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
