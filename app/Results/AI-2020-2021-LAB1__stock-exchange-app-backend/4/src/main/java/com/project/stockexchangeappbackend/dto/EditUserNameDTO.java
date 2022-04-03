package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "User details to edit.")
public class EditUserNameDTO {

@NotBlank(message = "This field is required.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's last name.", required = true, allowableValues = "range[1, 255]")
 private  String firstName;

@NotBlank(message = "This field is required.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's last name.", required = true, allowableValues = "range[1, 255]")
 private  String lastName;


}