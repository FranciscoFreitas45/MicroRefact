package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface MarketService {


public Page<Market> queryGoodsByGoodsName(String goodsName,Pageable pageable)
;

public Market getById(String id)
;

public Page<Market> queryGoodsById(String id,String goodsName,Pageable pageable)
;

public Market save(Market market)
;

public void delGoodsById(String id)
;

}