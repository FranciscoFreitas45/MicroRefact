package com.project.stockexchangeappbackend.DTO;
 import lombok;
import javax.persistence;
import com.project.stockexchangeappbackend.Request.StockRequest;
import com.project.stockexchangeappbackend.Request.Impl.StockRequestImpl;
import com.project.stockexchangeappbackend.DTO.Stock;
public class Resource {

 private  Long id;

 private  User user;

 private  Stock stock;

 private  Integer amount;

 private Long id;

 private StockRequest stockrequest = new StockRequestImpl();;


}