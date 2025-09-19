package springboot_ecommerce.project.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot_ecommerce.project.e_commerce.model.Product;
import springboot_ecommerce.project.e_commerce.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private Long id;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElse(null);
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.saveProduct(id, productDetails);

    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
       return productService.deleteProductById(id);
    }

    @DeleteMapping
    public Object deleteAllProducts(@RequestParam(required = false) Boolean confirm) {
        return productService.deleteAllProducts(confirm);

    }
}
