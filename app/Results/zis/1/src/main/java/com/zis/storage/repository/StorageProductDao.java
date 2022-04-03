package com.zis.storage.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StorageProduct;
public interface StorageProductDao extends JpaRepository<StorageProduct, Integer>{


public StorageProduct findBySkuIdAndRepoId(Integer skuId,Integer repoId)
;

@Query("from StorageProduct where skuId in (:skuIds) and repoId=:repoId")
public List<StorageProduct> findBySkuIdsAndRepoId(List<Integer> skuIds,Integer repoId)
;

public List<StorageProduct> findByRepoIdAndProductIdInOrderBySkuIdAsc(Integer repoId,List<Integer> productIds)
;

@Query(nativeQuery = true, value = "select p.* from storage_product p inner join " + "(select tmp.sku_id, tmp.product_id, sum(tmp.amount) as total " + "from " + "(SELECT d.product_id, d.sku_id, " + "case d.io_detail_type " + "when 'inwarehouse' then d.amount " + "when 'outwarehouse' then (0-d.amount) " + "end as amount " + "FROM storage_io_detail d " + "WHERE d.detail_status IN ('success','lackness') " + "AND d.repo_id = :repoId " + "AND d.gmt_modify BETWEEN :startTime AND :endTime " + ") tmp " + "group by tmp.product_id " + "having total != 0) " + "alterData " + "on alterData.product_id=p.product_id")
public List<StorageProduct> findByUpdateTimeBetweenStartTimeAndEndTimeAndRepoId(Date startTime,Date endTime,Integer repoId)
;

}