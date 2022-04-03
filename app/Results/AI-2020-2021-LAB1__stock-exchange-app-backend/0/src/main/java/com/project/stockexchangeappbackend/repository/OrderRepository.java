package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.Order;
import com.project.stockexchangeappbackend.entity.OrderType;
import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.entity.User;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaSpecificationExecutor<Order>, JpaRepository<Order, Long>{


@Override
@DBQueryMeasureTime
public List<S> saveAll(Iterable<S> var1)
;

@DBQueryMeasureTime
public List<Order> findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(Stock stock,User user,OrderType orderType,OffsetDateTime dateExpiration)
;

@Override
@DBQueryMeasureTime
public Optional<Order> findById(Long id)
;

@Override
@DBQueryMeasureTime
public S save(S s)
;

@Override
@DBQueryMeasureTime
public void deleteAll(Iterable<? extends Order> var1)
;

@DBQueryMeasureTime
public List<Order> findByStockAndOrderTypeAndPriceIsLessThanEqualAndDateExpirationIsAfterAndDateClosingIsNullOrderByPrice(Stock stock,OrderType orderType,BigDecimal price,OffsetDateTime expirationDate)
;

@DBQueryMeasureTime
public List<Order> findByDateExpirationIsBeforeOrRemainingAmountOrDateClosingIsNotNull(OffsetDateTime offsetDateTime,int remainingAmount)
;

@DBQueryMeasureTime
public List<Order> findByOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(OrderType orderType,OffsetDateTime expirationDate)
;

@DBQueryMeasureTime
public List<Order> findByStock(Stock stock)
;

}