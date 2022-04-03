package com.project.stockexchangeappbackend.dto;
 import com.project.stockexchangeappbackend.util.validation.RequiredFields;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Stock object to move stocks from one user to another.")
public class MoveStockDTO {

@ApiModelProperty(notes = "The stock's amount of units to move.")
@Min(value = 1, message = "Amount must be greater or equal {value}.")
@NotNull(message = "This field is required.")
 private  Integer amount;

@ApiModelProperty(notes = "The owner of given amount of stocks.")
@NotNull(message = "This field is required.")
@RequiredFields(value = { "id" }, message = "This JSON Object must require field id.")
 private  UserDTO userSource;

@ApiModelProperty(notes = "The new owner of given amount of stocks.")
@NotNull(message = "This field is required.")
@RequiredFields(value = { "id" }, message = "This JSON Object must require field id.")
 private  UserDTO userDestination;


}