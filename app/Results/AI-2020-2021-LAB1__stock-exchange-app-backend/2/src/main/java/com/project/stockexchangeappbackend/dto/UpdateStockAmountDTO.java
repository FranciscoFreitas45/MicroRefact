package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Update amount of stocks object.")
public class UpdateStockAmountDTO {

@ApiModelProperty(notes = "The stock's amount of units change in relation to current value.")
@NotNull(message = "This field is required.")
 private  Integer amount;

@ApiModelProperty(notes = "The changes' list among owners.")
@NotNull(message = "This field is required.")
@Size(min = 1, message = "This list must include at least {min} element.")
@Valid
 private  List<OwnerDTO> owners;


}