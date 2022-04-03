package com.project.stockexchangeappbackend.repository;

import com.project.stockexchangeappbackend.entity.*;
import com.project.stockexchangeappbackend.DTO.*;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Override
    @DBQueryMeasureTime
    <S extends Order> S save(S s);

    @Override
    @DBQueryMeasureTime
    <S extends Order> List<S> saveAll(Iterable<S> var1);

    @Override
    @DBQueryMeasureTime
    void deleteAll(Iterable<? extends Order> var1);

    @Override
    @DBQueryMeasureTime
    Optional<Order> findById(Long id);

    @DBQueryMeasureTime
    List<Order> findByOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(OrderType orderType, OffsetDateTime expirationDate);

    @DBQueryMeasureTime
    List<Order> findByStockAndOrderTypeAndPriceIsLessThanEqualAndDateExpirationIsAfterAndDateClosingIsNullOrderByPrice(
            Stock stock, OrderType orderType, BigDecimal price, OffsetDateTime expirationDate);

    @DBQueryMeasureTime
    List<Order> findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(Stock stock, User user,
                                                               OrderType orderType, OffsetDateTime dateExpiration);

    @DBQueryMeasureTime
    List<Order> findByDateExpirationIsBeforeOrRemainingAmountOrDateClosingIsNotNull(
            OffsetDateTime offsetDateTime, int remainingAmount);

    @DBQueryMeasureTime
    List<Order> findByStock(Stock stock);

}
