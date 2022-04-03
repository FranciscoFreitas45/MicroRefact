package com.project.stockexchangeappbackend.DTO;
 import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
public class ResourceDTO {

 private  Long id;

 private  String name;

 private  String abbreviation;

 private  Integer amount;

 private  Integer amountAvailableForSale;

 private  BigDecimal currentPrice;


}