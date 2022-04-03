package com.example.demo.Dao;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entity.Product;
public interface ProductDAO extends JpaRepository<Product, Integer>{


@Query("SELECT p FROM Product p WHERE p.category.id=:categoryid")
public Page<Product> findByCategoryId(String categoryid,Pageable pageable)
;

}