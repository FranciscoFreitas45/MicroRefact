package com.hmm.logistics.stock.repository;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.stock.entity.OutDetailed;
@Repository
public interface OutDetailedRepository extends PagingAndSortingRepository<OutDetailed, Long>, JpaSpecificationExecutor<OutDetailed>{


public void setAmount(Long id,float amount);

public void setGoodsName(Long id,String goodsName);

public void setGoodsNo(Long id,String goodsNo);

public void setOutStorage(Long id,OutStorage outStorage);

}