package com.yalcin.repository;
 import com.yalcin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer>{


public List<Product> findAllByUser_Id(Integer userId)
;

public Product findAllById(Integer productId)
;

public List<Product> findAllByEnabled(boolean enabled)
;

public void setProduct(Integer id,Product product);

public Product getProduct(Integer id);

public void setProduct(Integer id,Product product);

public Product getProduct(Integer id);

public void setShowcaseEnabled(Integer id,boolean showcaseEnabled);

public void setStock(Integer id,Integer stock);

}