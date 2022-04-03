package com.hmm.logistics.stock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.roomClean.entity.RoomClean;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.room.dto.DailyNecessaryDto;
@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Long>, JpaSpecificationExecutor<Stock>{


@Query("FROM Stock sc WHERE sc.stockType=?1")
public List<Stock> findByStockType(StockType stockType)
;

@Query("FROM Stock sc WHERE sc.goodsNo=?1")
public Stock findByGoodsNo(String goodsNo)
;

public void setAmount(Long id,float amount);

}