package com.weflors.repository;
 import com.weflors.entity.ProductStatusEntity;
import com.weflors.entity.ProductStatusEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
public interface ProductStatusRepository extends JpaRepository<ProductStatusEntity, ProductStatusEntityPK>{


@Modifying
@Transactional
@Query(value = "update flowershop.product_status set quantity_warehouse = quantity_warehouse - :quantityWriteoff," + "total_quantity_writeoff = total_quantity_writeoff + :quantityWriteoff where product_id = :productId", nativeQuery = true)
public void updateQuantityWriteoffAndWarehouse(int productId,int quantityWriteoff)
;

@Query("select b from ProductStatusEntity b where b.productId = :productId")
public ProductStatusEntity findOneById(int productId)
;

@Modifying
@Transactional
@Query(value = "update flowershop.product_status set quantity_warehouse = quantity_warehouse - :quantityShopSale," + "quantity_shop_sale = quantity_shop_sale + :quantityShopSale where product_id = :productId", nativeQuery = true)
public void updateQuantityShopSaleAndWarehouse(int productId,int quantityShopSale)
;

@Query("select b.validityDate from ProductStatusEntity b where b.productId = :productId")
public List<Date> getValidityDateByProdictId(Integer productId)
;

}