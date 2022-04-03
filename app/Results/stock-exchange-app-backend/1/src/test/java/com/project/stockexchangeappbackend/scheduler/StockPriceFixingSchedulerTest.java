package com.project.stockexchangeappbackend.scheduler;

import com.project.stockexchangeappbackend.DTO.ArchivedOrder;
import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.DTO.Transaction;
import com.project.stockexchangeappbackend.DTO.User;
import com.project.stockexchangeappbackend.service.StockIndexValueService;
import com.project.stockexchangeappbackend.Interface.StockService;
import com.project.stockexchangeappbackend.Interface.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.stockexchangeappbackend.service.OrderServiceImplTest.createBuyingArchivedOrder;
import static com.project.stockexchangeappbackend.service.OrderServiceImplTest.createSellingArchivedOrder;
import static com.project.stockexchangeappbackend.service.StockServiceImplTest.getStocksList;
import static com.project.stockexchangeappbackend.service.UserServiceImplTest.getUsersList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockPriceFixingSchedulerTest {

    @InjectMocks
    StockPriceFixingScheduler stockPriceFixingScheduler;

    @Mock
    StockService stockService;

    @Mock
    TransactionService transactionService;

    @Mock
    StockIndexValueService stockIndexValueService;


    @Test
    @DisplayName("Fixing stock price")
    void testScheduler() {
        List<Stock> stockList = getStocksList();
        List<List<Transaction>> stockTransactions = stockList.stream()
                .map(stock -> {
                    User user1 = getUsersList().get(0);
                    User user2 = getUsersList().get(2);
                    ArchivedOrder o1 = createBuyingArchivedOrder(2*stock.getId() + 1L, 100,  BigDecimal.ONE,
                            OffsetDateTime.now(), user1, stock);
                    ArchivedOrder o2 = createSellingArchivedOrder(2*stock.getId() + 2L, 100,  BigDecimal.ONE,
                            OffsetDateTime.now(), user2, stock);
                    return Collections.singletonList(
                            new Transaction(1L, OffsetDateTime.now(), o1.getAmount(), o1.getPrice(), o1, o2)
                    );
                })
                .collect(Collectors.toList());

        when(stockService.getAllStocks()).thenReturn(stockList);
        when(transactionService.getTransactionsByStockIdForPricing(stockList.get(0).getId(), stockList.get(0).getAmount()))
                .thenReturn(stockTransactions.get(0));
        when(transactionService.getTransactionsByStockIdForPricing(stockList.get(1).getId(), stockList.get(1).getAmount()))
                .thenReturn(stockTransactions.get(1));
        assertAll(() -> stockPriceFixingScheduler.run());
    }

    @Test
    @DisplayName("Fixing stock price when cannot perform database operation")
    void testSchedulerWhenCannotPerformDatabaseOperation() {
        List<Stock> stockList = getStocksList();
        List<List<Transaction>> stockTransactions = stockList.stream()
                .map(stock -> {
                    User user1 = getUsersList().get(0);
                    User user2 = getUsersList().get(2);
                    ArchivedOrder o1 = createBuyingArchivedOrder(2*stock.getId() + 1L, 100,  BigDecimal.ONE,
                            OffsetDateTime.now(), user1, stock);
                    ArchivedOrder o2 = createSellingArchivedOrder(2*stock.getId() + 2L, 100,  BigDecimal.ONE,
                            OffsetDateTime.now(), user2, stock);
                    return Collections.singletonList(
                            new Transaction(1L, OffsetDateTime.now(), o1.getAmount(), o1.getPrice(), o1, o2)
                    );
                })
                .collect(Collectors.toList());

        when(stockService.getAllStocks()).thenReturn(stockList);
        when(transactionService.getTransactionsByStockIdForPricing(stockList.get(0).getId(), stockList.get(0).getAmount()))
                .thenReturn(stockTransactions.get(0));
        when(transactionService.getTransactionsByStockIdForPricing(stockList.get(1).getId(), stockList.get(1).getAmount()))
                .thenReturn(stockTransactions.get(1));
        doThrow(new DataIntegrityViolationException("Database error"))
                .when(stockService)
                .updateStocks(any(List.class));
        assertAll(() -> stockPriceFixingScheduler.run());
    }

}