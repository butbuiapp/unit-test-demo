package miu.waa.productapplication.repository;

import miu.waa.productapplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByTitle(String title);
    List<Product> findAllByTitle(String title);
}
