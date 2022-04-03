package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.zis.storage.entity.StoragePosStock;
public interface StoragePosStockDao extends JpaRepository<StoragePosStock, Integer>{


@Query("select new com.zis.storage.dto.StockDTO(s.stockId, pos.posId, pos.label, prod.repoId, prod.productId, prod.skuId, s.totalAmt, s.occupyAmt) " + "from StoragePosStock s, StoragePosition pos, StorageProduct prod " + "where s.posId=pos.posId and s.productId=prod.productId " + "and s.productId = :productId " + "order by (s.totalAmt - s.occupyAmt) desc")
public List<com.zis.storage.dto.StockDTO> findAllStock(Integer productId)
;

public Page<StoragePosStock> findByPosId(Integer posId,Pageable page)
;

public StoragePosStock findByPosIdAndProductId(Integer posId,Integer productId)
;

@Query("select new com.zis.storage.dto.StockDTO(s.stockId, pos.posId, pos.label, prod.repoId, prod.productId, prod.skuId, s.totalAmt, s.occupyAmt) " + "from StoragePosStock s, StoragePosition pos, StorageProduct prod " + "where s.posId=pos.posId and s.productId=prod.productId " + "and s.totalAmt > s.occupyAmt and s.productId = :productId " + "order by (s.totalAmt - s.occupyAmt) desc")
public List<com.zis.storage.dto.StockDTO> findAvailableStock(Integer productId)
;

@Query(value = "select stock.* from storage_pos_stock stock, storage_position pos " + "where stock.pos_id=pos.pos_id and pos.repo_id=:repoId and pos.label=:posLabel and stock.product_id=:productId", nativeQuery = true)
public StoragePosStock findByLabelAndProductId(Integer repoId,String posLabel,Integer productId)
;

}