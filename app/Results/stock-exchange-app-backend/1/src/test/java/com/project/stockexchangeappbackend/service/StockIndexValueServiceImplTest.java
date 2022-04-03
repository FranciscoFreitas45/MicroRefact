package com.project.stockexchangeappbackend.service;

import com.project.stockexchangeappbackend.dto.StockIndexValueDTO;
import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.entity.StockIndexValue;
import com.project.stockexchangeappbackend.repository.StockIndexValueRepository;
import com.project.stockexchangeappbackend.repository.StockRepository;
import com.project.stockexchangeappbackend.util.StockIndexTimeProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.project.stockexchangeappbackend.service.StockServiceImplTest.assertStock;
import static com.project.stockexchangeappbackend.service.StockServiceImplTest.getStocksList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockIndexValueServiceImplTest {

    @InjectMocks
    StockIndexValueServiceImpl stockIndexValueService;

    @Mock
    StockRepository stockRepository;

    @Mock
    StockIndexValueRepository stockIndexValueRepository;

    @Mock
    StockIndexTimeProperties stockIndexTimeProperties;

    @Test
    @DisplayName("Appending current stocks' prices")
    void shouldAppendValue() {
        Stock stock = getStocksList().get(0);
        List<StockIndexValue> stockIndexValue = Collections.singletonList(
                createCustomStockIndexValue(stock, stock.getCurrentPrice(), OffsetDateTime.now()));

        when(stockIndexTimeProperties.getFixingPriceCycle()).thenReturn(30000);
        when(stockIndexTimeProperties.getMaxPriceHistoryPeriod()).thenReturn(24);
        when(stockIndexValueRepository.findStockWithExceedHistory(any(Integer.class)))
                .thenReturn(Collections.emptyList());
        assertAll(() -> stockIndexValueService.appendValues(stockIndexValue));
    }

    @Test
    @DisplayName("Appending current stock's prices when list of stock price is full")
    void shouldAppendValueWhenMaxRecordsExceed() {
        Stock stock = getStocksList().get(0);
        List<StockIndexValue> stockIndexValue = Collections.singletonList(
                createCustomStockIndexValue(stock, stock.getCurrentPrice(), OffsetDateTime.now()));
        StockIndexValue prevStockIndexValue =
                createCustomStockIndexValue(stock, stock.getCurrentPrice(), OffsetDateTime.now());
        List<Long> stocksId = Collections.singletonList(stock.getId());
        when(stockIndexTimeProperties.getFixingPriceCycle()).thenReturn(30000);
        when(stockIndexTimeProperties.getMaxPriceHistoryPeriod()).thenReturn(1);
        when(stockIndexValueRepository.findStockWithExceedHistory(any(Integer.class)))
                .thenReturn(stocksId);
        when(stockIndexValueRepository.findByStockIdInAndTimestampIsBeforeOrderByTimestampAsc(
                eq(stocksId), any(OffsetDateTime.class)))
                .thenReturn(Collections.singletonList(prevStockIndexValue));
        assertAll(() -> stockIndexValueService.appendValues(stockIndexValue));
    }

    @Test
    @DisplayName("Getting stock price in OHLC format")
    void shouldReturnStockIndexValues() {
        Integer interval = 1;
        Stock stock = getStocksList().get(0);
        Long stockId = stock.getId();
        OffsetDateTime now = OffsetDateTime.now();
        List<StockIndexValue> results = new ArrayList<>(List.of(
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.ZERO), now),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.ONE), now.minusSeconds(15)),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.TEN), now.minusSeconds(2*15)),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.ONE), now.minusSeconds(3*15)),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.ZERO), now.minusSeconds(4*15)),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.ONE), now.minusSeconds(5*15)),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.TEN), now.minusSeconds(6*15)),
            createCustomStockIndexValue(stock, stock.getCurrentPrice().add(BigDecimal.ONE), now.minusSeconds(7*15))));
        List<StockIndexValueDTO> expected = List.of(
                new StockIndexValueDTO(results.subList(5, 8)),
                new StockIndexValueDTO(results.subList(0, 6)));

        when(stockRepository.findByIdAndIsDeletedFalse(stockId)).thenReturn(Optional.of(stock));
        when(stockIndexValueRepository.findAll(any(Specification.class), any(Sort.class)))
                .thenReturn(results);
        List<StockIndexValueDTO> output = stockIndexValueService.getStockIndexValues(stockId, null, interval);
        assertEquals(expected.size(), output.size());
        for (int i=0; i<expected.size(); i++) {
            assertStockIndexValueDTO(expected.get(i), output.get(i));
        }
    }

    @Test
    @DisplayName("Getting stock price in OHLC format when history data not found")
    void shouldReturnStockIndexValuesEmptyList() {
        Integer interval = 1;
        Stock stock = getStocksList().get(0);
        Long stockId = stock.getId();

        when(stockRepository.findByIdAndIsDeletedFalse(stockId)).thenReturn(Optional.of(stock));
        when(stockIndexValueRepository.findAll(any(Specification.class), any(Sort.class)))
                .thenReturn(Collections.emptyList());
        assertEquals(0, stockIndexValueService.getStockIndexValues(stockId, null, interval).size());
    }

    @Test
    @DisplayName("Getting stock price in OHLC format when stock not found")
    void shouldThrowExceptionNotFoundWhenGettingStockIndexHistory() {
        Long stockId = 1L;
        Integer interval = 1;
        when(stockRepository.findByIdAndIsDeletedFalse(stockId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> stockIndexValueService.getStockIndexValues(stockId, null, interval));
    }

    @Test
    @DisplayName("Getting value before specified period of time")
    void shouldReturnFirstHistoryValueBefore() {
        int minutes = 1;
        Stock stock = getStocksList().get(0);
        StockIndexValue stockIndexValue = createCustomStockIndexValue(stock, BigDecimal.TEN,
                OffsetDateTime.now().minusMinutes(minutes));

        when(stockIndexValueRepository.findFirstByStockAndTimestampBeforeOrderByTimestampDesc(
                eq(stock), any(OffsetDateTime.class)))
                .thenReturn(Optional.of(stockIndexValue));
        assertStockIndexValue(stockIndexValue,
                stockIndexValueService.getFirstStockIndexValueBeforeMinutesAgo(stock, minutes).get());
    }

    public void assertStockIndexValueDTO(StockIndexValueDTO expected, StockIndexValueDTO output) {
        assertAll(() -> assertEquals(expected.getTimestamp(), output.getTimestamp()),
                () -> assertEquals(expected.getOpen(), output.getOpen()),
                () -> assertEquals(expected.getMin(), output.getMin()),
                () -> assertEquals(expected.getMax(), output.getMax()),
                () -> assertEquals(expected.getClose(), output.getClose()));
    }

    public void assertStockIndexValue(StockIndexValue expected, StockIndexValue output) {
        assertAll(() -> assertEquals(expected.getTimestamp(), output.getTimestamp()),
                () -> assertEquals(expected.getValue(), output.getValue()),
                () -> assertStock(output.getStock(), expected.getStock()),
                () -> assertEquals(expected.getId(), output.getId()));
    }

    public StockIndexValue createCustomStockIndexValue(Stock stock, BigDecimal value, OffsetDateTime timestamp) {
        return StockIndexValue.builder()
                .stock(stock)
                .value(value)
                .timestamp(timestamp)
                .build();
    }

}
