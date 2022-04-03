package com.project.stockexchangeappbackend.scheduler;
 import com.project.stockexchangeappbackend.entity.StockIndexValue;
import com.project.stockexchangeappbackend.service.StockIndexValueService;
import com.project.stockexchangeappbackend.service.StockService;
import com.project.stockexchangeappbackend.util.StockIndexTimeProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import com.project.stockexchangeappbackend.Interface.StockService;
@Component
@Slf4j
@AllArgsConstructor
public class StockPriceChangeRatioScheduler {

 private  StockService stockService;

 private  StockIndexValueService stockIndexValueService;

 private  StockIndexTimeProperties stockIndexTimeProperties;


@Scheduled(initialDelayString = "${application.stock.fixingPriceCycle}", fixedRateString = "${application.stock.stockPriceChangeRatioPeriod}")
public void run(){
    log.info("Stocks' price change ratio fixing started.");
    long start = System.nanoTime();
    ForkJoinPool threadPool = new ForkJoinPool(Math.max(1, Runtime.getRuntime().availableProcessors() / 2));
    try {
        stockService.updateStocks(threadPool.submit(() -> stockService.getAllStocks().parallelStream().peek(stock -> {
            Optional<StockIndexValue> stockIndexValue = stockIndexValueService.getFirstStockIndexValueBeforeMinutesAgo(stock, stockIndexTimeProperties.getStockPriceChangeRatioPeriod() / 60000);
            if (stockIndexValue.isPresent() && !stockIndexValue.get().getValue().equals(BigDecimal.ZERO)) {
                double priceChangeRatio = stock.getCurrentPrice().subtract(stockIndexValue.get().getValue()).divide(stockIndexValue.get().getValue(), RoundingMode.CEILING).doubleValue();
                stock.setPriceChangeRatio(priceChangeRatio);
            }
        }).collect(Collectors.toList())).get());
    } catch (DataIntegrityViolationException exc) {
        log.error("Cannot update stocks' price change ratio - non-existing tag");
    }
    threadPool.shutdown();
    long stop = (System.nanoTime() - start) / 1000000;
    log.info("Stocks' price change ratio fixing fixing stopped. Execution time: " + stop + " ms.");
}


}