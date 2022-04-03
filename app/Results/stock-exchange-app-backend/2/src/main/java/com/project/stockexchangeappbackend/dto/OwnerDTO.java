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
@ApiModel(description = "Stock ownership object.")
public class OwnerDTO {

@ApiModelProperty(notes = "The stock's amount of units or change in case update stock's amount")
@NotNull(message = "This field is required.")
@Min(value = 1, message = "Amount must be greater or equal {value}.")
 private  Integer amount;

@ApiModelProperty(notes = "The owner of given amount of stocks.")
@NotNull(message = "This field is required.")
@RequiredFields(value = { "id" }, message = "This JSON Object must require field id.")
 private  UserDTO user;


}