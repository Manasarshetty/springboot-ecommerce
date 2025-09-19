package springboot_ecommerce.project.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot_ecommerce.project.e_commerce.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

}
