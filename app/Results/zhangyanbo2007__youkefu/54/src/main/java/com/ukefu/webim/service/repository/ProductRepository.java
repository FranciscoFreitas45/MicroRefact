package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Product;
public interface ProductRepository extends JpaRepository<Product, String>{


public Page<Product> findByOrgiAndCate(String orgi,String cate,Pageable page)
;

public Product findByOrgiAndTitle(String orgi,String title)
;

public List<Product> findByOrgi(String cate)
;

public int countByTitleAndOrgi(String title,String orgi)
;

public Product findByIdAndOrgi(String id,String orgi)
;

}