package com.project.stockexchangeappbackend.dto;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.stockexchangeappbackend.entity.OrderType;
import com.project.stockexchangeappbackend.entity.PriceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import com.project.stockexchangeappbackend.Interface.UserDTO;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Order object stored in database.")
public class OrderDTO {

@ApiModelProperty(notes = "The order's id.")
 private  Long id;

@ApiModelProperty(notes = "Amount of stock desired by order's maker.")
 private  int amount;

@ApiModelProperty(notes = "Remaining amount of stock to process.")
 private  int remainingAmount;

@ApiModelProperty(notes = "Order type (SELLING_ORDER | BUYING_ORDER).")
 private  OrderType orderType;

@ApiModelProperty(notes = "Price type (EQUAL | GREATER_OR_EQUAL | LESS_OR_EQUAL).")
 private  PriceType priceType;

@ApiModelProperty(notes = "Price desired by order's maker.")
 private  BigDecimal price;

@ApiModelProperty(notes = "Creation date of order.")
 private  OffsetDateTime dateCreation;

@ApiModelProperty(notes = "Expiration date of order.")
 private  OffsetDateTime dateExpiration;

@ApiModelProperty(notes = "Closing date of order.")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  OffsetDateTime dateClosing;

@ApiModelProperty(notes = "Stock's object related with this order.")
 private  StockDTO stock;

@ApiModelProperty(notes = "Principal of this order. (Present if data were requested by admin.")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  UserDTO user;


}