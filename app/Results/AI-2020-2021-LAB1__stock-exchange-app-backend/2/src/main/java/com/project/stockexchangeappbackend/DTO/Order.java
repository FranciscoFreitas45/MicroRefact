package com.project.stockexchangeappbackend.DTO;
 import lombok;
import javax.persistence;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.project.stockexchangeappbackend.Request.StockRequest;
import com.project.stockexchangeappbackend.Request.Impl.StockRequestImpl;
import com.project.stockexchangeappbackend.DTO.Stock;
public class Order {

 private  Long id;

 private  User user;

 private  Stock stock;

 private  Integer amount;

 private  Integer remainingAmount;

 private  OrderType orderType;

 private  PriceType priceType;

 private  BigDecimal price;

 private  OffsetDateTime dateCreation;

 private  OffsetDateTime dateExpiration;

 private  OffsetDateTime dateClosing;


}