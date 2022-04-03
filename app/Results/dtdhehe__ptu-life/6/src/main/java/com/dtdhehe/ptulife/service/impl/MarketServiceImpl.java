package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.entity.Market;
import com.dtdhehe.ptulife.repository.MarketRepository;
import com.dtdhehe.ptulife.service.MarketService;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.KeyUtils;
import com.dtdhehe.ptulife.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.dtdhehe.ptulife.Interface.RedisUtils;
@Service
public class MarketServiceImpl implements MarketService{

@Autowired
 private  MarketRepository marketRepository;

@Resource
 private  RedisUtils redisUtils;


@Override
public Page<Market> queryGoodsByGoodsName(String goodsName,Pageable pageable){
    return marketRepository.queryGoodsByGoodsName(goodsName, pageable);
}


@Override
public Market getById(String id){
    Market market = (Market) redisUtils.get(id);
    if (market == null) {
        System.out.println("缓存中没有该商品,进入数据库查询");
        market = marketRepository.findById(id).get();
        redisUtils.set(market.getId(), market, 1800);
    }
    return market;
}


@Override
public Page<Market> queryGoodsById(String id,String goodsName,Pageable pageable){
    return marketRepository.queryGoodsById(id, goodsName, pageable);
}


@Override
public Market save(Market market){
    if (market == null) {
        market = new Market();
    }
    market.setId(KeyUtils.getUniqueKey());
    market.setCreateTime(DateUtils.getCurrentDateTime());
    return marketRepository.save(market);
}


@Override
public void delGoodsById(String id){
    marketRepository.deleteById(id);
}


}