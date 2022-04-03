package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Stock object to create.")
public class CreateStockDTO {

@ApiModelProperty(notes = "The stock's name.")
@NotBlank(message = "This field is required.")
@Size(max = 255, message = "Length of the name can be {max} characters long.")
 private  String name;

@ApiModelProperty(notes = "The stock's abbreviation.")
@Size(min = 3, max = 3, message = "Size of the abbreviation must be exact {max} characters long.")
@NotBlank(message = "This field is required.")
 private  String abbreviation;

@ApiModelProperty(notes = "The stock's initial current price per unit.")
@NotNull(message = "This field is required.")
@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than {value}.")
 private  BigDecimal currentPrice;

@ApiModelProperty(notes = "The stock's amount of units.")
@Min(value = 1, message = "Amount must be greater or equal {value}.")
@NotNull(message = "This field is required.")
 private  Integer amount;

@ApiModelProperty(notes = "The owners' list of new stocks.")
@NotNull(message = "This field is required.")
@Size(min = 1, message = "This list must include at least {min} element.")
@Valid
 private  List<OwnerDTO> owners;


}