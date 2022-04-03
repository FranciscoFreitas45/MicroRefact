package com.sprint2.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint2.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{


}