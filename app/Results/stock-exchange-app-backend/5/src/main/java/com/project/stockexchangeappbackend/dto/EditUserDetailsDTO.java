package com.project.stockexchangeappbackend.dto;
 import com.project.stockexchangeappbackend.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "User details to edit.")
public class EditUserDetailsDTO {

@NotBlank(message = "This field is required.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's last name.", required = true, allowableValues = "range[1, 255]")
 private  String firstName;

@NotBlank(message = "This field is required.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's last name.", required = true, allowableValues = "range[1, 255]")
 private  String lastName;

@NotNull(message = "This field is required.")
@ApiModelProperty(notes = "The user's role.", required = true, allowableValues = "ADMIN | USER")
 private  Role role;

@NotNull(message = "This field is required.")
@ApiModelProperty(notes = "The user's state.", required = true, allowableValues = "ADMIN | USER")
 private  Boolean isActive;


}