package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Stock object to edit stock's names.")
public class EditStockNameDTO {

@ApiModelProperty(notes = "The stock's name.")
@Size(max = 255, message = "Length of the name can be {max} characters long.")
@NotBlank(message = "This field is required.")
 private  String name;

@Size(min = 3, max = 3, message = "Size of the abbreviation must be exact {max} characters long.")
@ApiModelProperty(notes = "The stock's abbreviation.")
@NotBlank(message = "This field is required.")
 private  String abbreviation;


}