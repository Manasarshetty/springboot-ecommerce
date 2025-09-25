package springboot_ecommerce.project.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot_ecommerce.project.e_commerce.model.Product;
import springboot_ecommerce.project.e_commerce.service.PricingClient;
import springboot_ecommerce.project.e_commerce.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final PricingClient pricingClient;
    private final ProductService productService;
    private Long id;

    @Autowired
    public ProductController(PricingClient pricingClient, ProductService productService) {
        this.pricingClient = pricingClient;
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
        return productService.saveProduct( product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);

    }
    @GetMapping("/{id}/price")
    public ResponseEntity<String> getProductPrice(@PathVariable Long id,
                                                  @RequestParam(defaultValue = "1") int quantity) {
        BigDecimal price = pricingClient.getQuote(id, quantity);
        return ResponseEntity.ok("Total price for product " + id + ": " + price);
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
