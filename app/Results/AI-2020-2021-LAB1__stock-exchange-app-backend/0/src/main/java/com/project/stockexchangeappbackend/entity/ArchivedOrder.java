package com.project.stockexchangeappbackend.entity;
 import lombok;
import javax.persistence;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import com.project.stockexchangeappbackend.Request.StockRequest;
import com.project.stockexchangeappbackend.Request.Impl.StockRequestImpl;
import com.project.stockexchangeappbackend.DTO.Stock;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ARCHIVED_ORDERS")
public class ArchivedOrder {

@Id
 private  Long id;

@ManyToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "USER_ID", nullable = false, updatable = false, referencedColumnName = "ID")
 private  User user;

@Transient
 private  Stock stock;

@Column(name = "AMOUNT", updatable = false, nullable = false)
 private  Integer amount;

@Column(name = "REMAINING_AMOUNT", nullable = false)
 private  Integer remainingAmount;

@Column(name = "TYPE", nullable = false)
@Enumerated(EnumType.STRING)
 private  OrderType orderType;

@Column(name = "PRICE_TYPE", nullable = false)
@Enumerated(EnumType.STRING)
 private  PriceType priceType;

@Column(name = "PRICE", precision = 14, scale = 2, updatable = false, nullable = false)
 private  BigDecimal price;

@Column(name = "DATE_CREATION", nullable = false)
 private  OffsetDateTime dateCreation;

@Column(name = "DATE_EXPIRATION", nullable = false)
 private  OffsetDateTime dateExpiration;

@Column(name = "DATE_CLOSING")
 private  OffsetDateTime dateClosing;

@Column(name = "id")
 private Long id;

@Transient
 private StockRequest stockrequest = new StockRequestImpl();;


}