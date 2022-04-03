package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.StockIndexValueDTO;
import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.entity.StockIndexValue;
import com.project.stockexchangeappbackend.repository.StockIndexValueRepository;
import com.project.stockexchangeappbackend.repository.StockRepository;
import com.project.stockexchangeappbackend.util.StockIndexTimeProperties;
import com.project.stockexchangeappbackend.util.timemeasuring.LogicBusinessMeasureTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class StockIndexValueServiceImpl implements StockIndexValueService{

 private  StockRepository stockRepository;

 private  StockIndexValueRepository stockIndexValueRepository;

 private  StockIndexTimeProperties stockIndexTimeProperties;


@Override
@LogicBusinessMeasureTime
@Transactional
public void appendValues(Collection<StockIndexValue> stockIndexValues){
    stockIndexValueRepository.saveAll(stockIndexValues);
    int stockPriceRefreshCycle = stockIndexTimeProperties.getFixingPriceCycle() / 1000;
    int maxPriceHistoryPeriod = stockIndexTimeProperties.getMaxPriceHistoryPeriod() * 3600;
    int records = maxPriceHistoryPeriod / stockPriceRefreshCycle - 1;
    OffsetDateTime timeLimit = OffsetDateTime.now().minusSeconds(maxPriceHistoryPeriod - stockPriceRefreshCycle);
    List<Long> stocksWithExceedHistory = stockIndexValueRepository.findStockWithExceedHistory(records);
    if (!stocksWithExceedHistory.isEmpty()) {
        stockIndexValueRepository.deleteAll(stockIndexValueRepository.findByStockIdInAndTimestampIsBeforeOrderByTimestampAsc(stocksWithExceedHistory, timeLimit));
    }
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Optional<StockIndexValue> getFirstStockIndexValueBeforeMinutesAgo(Stock stock,Integer minutes){
    return stockIndexValueRepository.findFirstByStockAndTimestampBeforeOrderByTimestampDesc(stock, OffsetDateTime.now(ZoneId.systemDefault()).minusMinutes(minutes));
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public List<StockIndexValueDTO> getStockIndexValues(Long stockId,Specification<StockIndexValue> specification,Integer interval){
    Stock stock = stockRepository.findByIdAndIsDeletedFalse(stockId).orElseThrow(() -> new EntityNotFoundException("Stock not found"));
    List<List<StockIndexValue>> results = new ArrayList<>();
    results.add(new ArrayList<>());
    List<StockIndexValue> stockIndexValues = stockIndexValueRepository.findAll(getSpecificationById(stock.getId()).and(specification), Sort.by("timestamp").descending());
    if (stockIndexValues.isEmpty()) {
        return Collections.emptyList();
    }
    stockIndexValues.forEach(stockIndexValue -> {
        results.get(results.size() - 1).add(stockIndexValue);
        if (stockIndexValue.getTimestamp().isBefore(results.get(results.size() - 1).get(0).getTimestamp().minusMinutes(interval))) {
            results.add(new ArrayList<>());
            results.get(results.size() - 1).add(stockIndexValue);
        }
    });
    Collections.reverse(results);
    return results.parallelStream().map(StockIndexValueDTO::new).collect(Collectors.toList());
}


public Specification<StockIndexValue> getSpecificationById(Long stockId){
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("stock").get("id"), stockId);
}


}