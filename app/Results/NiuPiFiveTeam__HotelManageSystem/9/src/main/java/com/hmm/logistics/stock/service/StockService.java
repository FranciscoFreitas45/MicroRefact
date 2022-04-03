package com.hmm.logistics.stock.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.repository.StockRepository;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.room.dto.DailyNecessaryDto;
import com.hmm.DTO.DailyNecessaryDto;
@Service
@Transactional
public class StockService implements IStockService{

@Autowired
 private  StockRepository stockRepository;


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return stockRepository.existsById(id);
}


@Override
public Stock findById(Long id){
    // TODO Auto-generated method stub
    return stockRepository.findById(id).get();
}


@Override
public Stock save(Stock entity){
    // TODO Auto-generated method stub
    return stockRepository.save(entity);
}


@Override
public long count(){
    // TODO Auto-generated method stub
    return stockRepository.count();
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    stockRepository.deleteById(id);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
    List<Stock> stock = (List<Stock>) stockRepository.findAllById(idLists);
    if (stock != null) {
        stockRepository.deleteAll(stock);
    }
}


@Override
public List<DailyNecessaryDto> findByStockType(){
    // TODO Auto-generated method stub
    List<DailyNecessaryDto> dailyNecessaryDtos = new ArrayList<DailyNecessaryDto>();
    for (Stock stock : stockRepository.findByStockType(StockType.COMMODITY)) {
        DailyNecessaryDto dailyNecessaryDto = new DailyNecessaryDto();
        dailyNecessaryDto.setId("d" + stock.getId().toString());
        dailyNecessaryDto.setName(stock.getGoodsNo());
        dailyNecessaryDto.setNumber(0);
        dailyNecessaryDto.setShow(stock.getGoodsName());
        dailyNecessaryDtos.add(dailyNecessaryDto);
    }
    return dailyNecessaryDtos;
}


@Override
public Page<Stock> findAll(Specification<Stock> spec,Pageable pageable){
    // TODO Auto-generated method stub
    return stockRepository.findAll(spec, pageable);
}


@Override
public Stock findByGoodsNo(String goodsNo){
    // TODO Auto-generated method stub
    return stockRepository.findByGoodsNo(goodsNo);
}


}