package com.project.stockexchangeappbackend.scheduler;
 import com.project.stockexchangeappbackend.entity.*;
  import com.project.stockexchangeappbackend.DTO.*;
import com.project.stockexchangeappbackend.service.OrderService;
import com.project.stockexchangeappbackend.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
@Component
@Slf4j
@AllArgsConstructor
public class StockExchangeAlgorithmScheduler {

 private  OrderService orderService;

 private  TransactionService transactionService;


public void removeInactiveOrders(){
    log.info("Movement inactive orders started.");
    long start = System.nanoTime();
    orderService.moveInactiveOrders();
    long stop = (System.nanoTime() - start) / 1000000;
    log.info("Movement inactive orders finished. Execution time: " + stop + " ms.");
}


public boolean checkDifferentUsersRule(User buyer,User seller){
    return !buyer.getId().equals(seller.getId());
}


public void executeStockAlgorithm(){
    log.info("Stock exchange algorithm started.");
    long start = System.nanoTime();
    List<Order> activeBuyingOrder = orderService.getActiveBuyingOrders();
    ConcurrentMap<Stock, List<Order>> groupedBuyingOrdersByStock = activeBuyingOrder.parallelStream().collect(Collectors.groupingByConcurrent(Order::getStock));
    groupedBuyingOrdersByStock.forEach((k, v) -> groupedBuyingOrdersByStock.put(k, v.stream().sorted(Comparator.comparing(Order::getPrice)).collect(Collectors.toList())));
    ForkJoinPool threadPool = new ForkJoinPool(Math.max(1, Runtime.getRuntime().availableProcessors()));
    threadPool.submit(() -> groupedBuyingOrdersByStock.entrySet().parallelStream().forEach(entry -> {
        List<Order> buyingOrders = entry.getValue();
        List<Order> sellingOrders = orderService.getActiveSellingOrdersByStockAndPriceLessThanEqual(entry.getKey(), buyingOrders.get(buyingOrders.size() - 1).getPrice());
        int index = 0;
        while (!(buyingOrders.isEmpty() || sellingOrders.isEmpty())) {
            Order buyingOrder = buyingOrders.get(index);
            Order sellingOrder = sellingOrders.get(0);
            if (checkOrderCompatibility(buyingOrder, sellingOrder)) {
                BigDecimal transactionPrice;
                if (buyingOrder.getPriceType() == PriceType.EQUAL) {
                    transactionPrice = buyingOrder.getPrice();
                } else if (sellingOrder.getPriceType() == PriceType.EQUAL) {
                    transactionPrice = sellingOrder.getPrice();
                } else {
                    transactionPrice = BigDecimal.valueOf(buyingOrder.getPrice().doubleValue()).add(sellingOrder.getPrice()).divide(BigDecimal.valueOf(2.));
                }
                OffsetDateTime transactionTime = OffsetDateTime.now(ZoneId.systemDefault());
                int transactionAmount = Math.min(buyingOrder.getRemainingAmount(), sellingOrder.getRemainingAmount());
                buyingOrder.setRemainingAmount(buyingOrder.getRemainingAmount() - transactionAmount);
                sellingOrder.setRemainingAmount(sellingOrder.getRemainingAmount() - transactionAmount);
                if (buyingOrder.getRemainingAmount() == 0) {
                    buyingOrder.setDateClosing(transactionTime);
                }
                if (sellingOrder.getRemainingAmount() == 0) {
                    sellingOrder.setDateClosing(transactionTime);
                }
                index = 0;
                try {
                    transactionService.makeTransaction(buyingOrder, sellingOrder, transactionAmount, transactionPrice);
                    if (buyingOrder.getRemainingAmount() == 0) {
                        buyingOrders.remove(buyingOrder);
                    }
                    if (sellingOrder.getRemainingAmount() == 0) {
                        sellingOrders.remove(sellingOrder);
                    }
                } catch (EntityNotFoundException | DataIntegrityViolationException | ObjectOptimisticLockingFailureException e) {
                    sellingOrder.setRemainingAmount(sellingOrder.getRemainingAmount() + transactionAmount);
                    sellingOrder.setDateClosing(null);
                    buyingOrder.setRemainingAmount(buyingOrder.getRemainingAmount() + transactionAmount);
                    buyingOrder.setDateClosing(null);
                    Optional<Order> refreshedBuyingOrder = orderService.refreshObjectById(buyingOrder.getId());
                    Optional<Order> refreshedSellingOrder = orderService.refreshObjectById(sellingOrder.getId());
                    if (refreshedBuyingOrder.isEmpty()) {
                        buyingOrders.remove(buyingOrder);
                    } else {
                        buyingOrder.setUser(refreshedBuyingOrder.get().getUser());
                    }
                    if (refreshedSellingOrder.isEmpty()) {
                        sellingOrders.remove(sellingOrder);
                    } else {
                        sellingOrder.setUser(refreshedSellingOrder.get().getUser());
                    }
                }
            } else {
                if (index == buyingOrders.size() - 1) {
                    sellingOrders.remove(sellingOrder);
                    index = 0;
                } else if (!checkDifferentUsersRule(buyingOrder.getUser(), sellingOrder.getUser()) || !checkEqualPriceTypeRule(buyingOrder, sellingOrder)) {
                    index++;
                } else {
                    buyingOrders.remove(buyingOrder);
                    index = 0;
                }
            }
        }
    })).join();
    threadPool.shutdown();
    long stop = (System.nanoTime() - start) / 1000000;
    log.info("Stock exchange algorithm stopped. Execution time: " + stop + " ms.");
}


public boolean checkOrderCompatibility(Order buyingOrder,Order sellingOrder){
    return buyingOrder.getPrice().compareTo(sellingOrder.getPrice()) >= 0 && checkDifferentUsersRule(buyingOrder.getUser(), sellingOrder.getUser()) && checkEqualPriceTypeRule(buyingOrder, sellingOrder);
}


@Scheduled(fixedDelayString = "${application.stock.algorithm-cycle}")
public void run(){
    removeInactiveOrders();
    executeStockAlgorithm();
}


public boolean checkEqualPriceTypeRule(Order buyingOrder,Order sellingOrder){
    return ((buyingOrder.getPriceType() != PriceType.EQUAL || sellingOrder.getPriceType() != PriceType.EQUAL) || buyingOrder.getPrice().equals(sellingOrder.getPrice()));
}


}