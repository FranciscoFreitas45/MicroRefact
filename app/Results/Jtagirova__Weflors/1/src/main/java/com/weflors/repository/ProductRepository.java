package com.weflors.repository;
 import com.weflors.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{


@Modifying
@Transactional
@Query("delete from ProductEntity where productId = :productId")
public void deleteByProductId(Integer productId)
;

@Query("select b.productId from ProductEntity b")
public List<Integer> getAllProductId()
;

@Query("select prod from ProductEntity prod where prod.productId = :productID")
public ProductEntity findByProductID(Integer productID)
;

@Query("select prod, prodDetails from ProductEntity prod, ProductDetailsEntity prodDetails where prod.productId = :productID")
public ProductEntity findProductDetailsByProductID(String productID)
;

@Query("select prod from ProductEntity prod where prod.articul = :articul")
public ProductEntity findByArticul(String articul)
;

}