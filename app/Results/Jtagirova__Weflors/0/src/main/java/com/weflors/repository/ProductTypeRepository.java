package com.weflors.repository;
 import com.weflors.entity.ProductTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
public interface ProductTypeRepository extends JpaRepository<ProductTypesEntity, Integer>{


@Query("select pt.productTypeName from ProductTypesEntity pt")
public List<String> getAllProductType()
;

@Query("select pt from ProductTypesEntity pt where pt.productTypeName = :productTypeName")
public Optional<ProductTypesEntity> findByProductTypeName(String productTypeName)
;

@Modifying
@Transactional
@Query("delete from ProductTypesEntity where productTypeId = :productTypeId")
public void deleteProductTypeById(Integer productTypeId)
;

@Modifying
@Transactional
@Query("update ProductTypesEntity set productTypeName = :productTypeName where productTypeId = :productTypeId")
public void updateProductTypeById(String productTypeName,Integer productTypeId)
;

}