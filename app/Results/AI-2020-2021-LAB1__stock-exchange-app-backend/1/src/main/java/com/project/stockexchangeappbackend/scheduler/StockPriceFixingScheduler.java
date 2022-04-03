package com.project.stockexchangeappbackend.scheduler;
 import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.entity.StockIndexValue;
import com.project.stockexchangeappbackend.service.StockIndexValueService;
import com.project.stockexchangeappbackend.service.StockService;
import com.project.stockexchangeappbackend.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import com.project.stockexchangeappbackend.Interface.StockService;
import com.project.stockexchangeappbackend.Interface.TransactionService;
@Component
@Slf4j
@AllArgsConstructor
public class StockPriceFixingScheduler {

 private  StockService stockService;

 private  TransactionService transactionService;

 private  StockIndexValueService stockIndexValueService;


@Scheduled(fixedDelayString = "${application.stock.fixingPriceCycle}")
public void run(){
    log.info("Stocks' price fixing started.");
    long start = System.nanoTime();
    OffsetDateTime timestamp = OffsetDateTime.now(ZoneId.systemDefault());
    List<Stock> stocks = stockService.getAllStocks();
    ForkJoinPool threadPool = new ForkJoinPool(Math.max(1, Runtime.getRuntime().availableProcessors() / 2));
    Collection<StockIndexValue> stockIndexValues = threadPool.submit(() -> stocks.parallelStream().map(stock -> {
        BigDecimal newPrice = BigDecimal.valueOf(transactionService.getTransactionsByStockIdForPricing(stock.getId(), stock.getAmount()).stream().mapToDouble(transaction -> transaction.getUnitPrice().doubleValue()).average().orElseGet(() -> stock.getCurrentPrice().doubleValue()));
        stock.setCurrentPrice(newPrice);
        return StockIndexValue.builder().timestamp(timestamp).value(newPrice).stock(stock).build();
    }).collect(Collectors.toList())).get();
    try {
        stockService.updateStocks(stocks);
        stockIndexValueService.appendValues(stockIndexValues);
    } catch (DataIntegrityViolationException exc) {
        log.error("Cannot update stocks' price - non-existing tag");
    }
    threadPool.shutdown();
    long stop = (System.nanoTime() - start) / 1000000;
    log.info("Stocks' price fixing stopped. Execution time: " + stop + " ms.");
}


}