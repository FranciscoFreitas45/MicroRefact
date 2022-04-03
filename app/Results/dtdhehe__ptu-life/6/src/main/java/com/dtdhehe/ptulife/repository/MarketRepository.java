package com.dtdhehe.ptulife.repository;
 import com.dtdhehe.ptulife.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface MarketRepository extends JpaRepository<Market, String>{


@Query(value = "select t from Market t where t.goodsName like %?1%")
public Page<Market> queryGoodsByGoodsName(String goodsName,Pageable pageable)
;

@Query(value = "select t from Market t where t.userId= ?1 and t.goodsName like %?2%")
public Page<Market> queryGoodsById(String id,String goodsName,Pageable pageable)
;

public void setCreateTime(String id,String createTime);

}