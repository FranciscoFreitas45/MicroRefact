package com.project.stockexchangeappbackend.dto;
 import com.project.stockexchangeappbackend.entity.OrderType;
import com.project.stockexchangeappbackend.entity.PriceType;
import com.project.stockexchangeappbackend.util.validation.DateIsAfterNow;
import com.project.stockexchangeappbackend.util.validation.RequiredFields;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Order object to create.")
public class CreateOrderDTO {

@ApiModelProperty(notes = "Amount of stock desired by order's maker.")
@NotNull(message = "This field is required.")
@Min(value = 1, message = "Amount must be greater or equal {value}.")
 private  int amount;

@ApiModelProperty(notes = "Order type (SELLING_ORDER | BUYING_ORDER).")
@NotNull(message = "This field is required.")
 private  OrderType orderType;

@ApiModelProperty(notes = "Price type (EQUAL | GREATER_OR_EQUAL | LESS_OR_EQUAL).")
@NotNull(message = "This field is required.")
 private  PriceType priceType;

@ApiModelProperty(notes = "Price desired by order's maker.")
@NotNull(message = "This field is required.")
@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than {value}.")
 private  BigDecimal price;

@ApiModelProperty(notes = "Expiration date of order.")
@NotNull(message = "This field is required.")
@DateIsAfterNow
 private  OffsetDateTime dateExpiration;

@ApiModelProperty(notes = "Stock's object related with this order.")
@NotNull(message = "This field is required.")
@RequiredFields(value = { "id" }, message = "This JSON Object must require field id.")
 private  StockDTO stock;


}