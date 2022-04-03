package com.project.stockexchangeappbackend.dto;
 import com.project.stockexchangeappbackend.DTO.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "User object stored in database.")
public class UserDTO {

@ApiModelProperty(notes = "The user's id.")
 private  Long id;

@ApiModelProperty(notes = "The user's first name.")
 private  String firstName;

@ApiModelProperty(notes = "The user's last name.")
 private  String lastName;

@ApiModelProperty(notes = "The user's email.")
 private  String email;

@ApiModelProperty(notes = "The user's role.")
 private  Role role;

@ApiModelProperty(notes = "The user's account balance.")
 private  BigDecimal money;

@ApiModelProperty(notes = "The user's tag.")
 private  String tag;

@ApiModelProperty(notes = "The user's status.")
 private  Boolean isActive;


}