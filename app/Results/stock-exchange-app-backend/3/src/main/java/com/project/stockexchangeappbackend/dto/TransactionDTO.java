package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Transaction object stored in database.")
public class TransactionDTO {

@ApiModelProperty(notes = "The transaction's id.")
 private  Long id;

@ApiModelProperty(notes = "The date of transaction.")
 private  OffsetDateTime date;

@ApiModelProperty(notes = "The amount of stock sold within this transaction.")
 private  Integer amount;

@ApiModelProperty(notes = "The transaction's price per stock's unit.")
 private  BigDecimal unitPrice;

@ApiModelProperty(notes = "The transaction's selling order.")
 private  OrderDTO sellingOrder;

@ApiModelProperty(notes = "The transaction's buying order.")
 private  OrderDTO buyingOrder;


}