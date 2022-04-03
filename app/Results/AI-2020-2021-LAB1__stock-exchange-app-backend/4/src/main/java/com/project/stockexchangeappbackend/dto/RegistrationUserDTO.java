package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
@AllArgsConstructor
@Builder
@ApiModel(description = "User object to registration in database.")
public class RegistrationUserDTO {

@NotBlank(message = "This field is required.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's first name.", required = true, allowableValues = "range[1, 255]")
 private  String firstName;

@NotBlank(message = "This field is required.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's last name.", required = true, allowableValues = "range[1, 255]")
 private  String lastName;

@NotBlank(message = "This field is required.")
@Email(regexp = "^\\S+@\\S+\\.\\S+$", message = "Email must be valid.")
@Length(min = 1, max = 255, message = "This field can be {max} characters long")
@ApiModelProperty(notes = "The user's email.", required = true, allowableValues = "range[1, 255]")
 private  String email;

@NotBlank(message = "This field is required.")
@Length(min = 8, message = "This field must contain at least {min} characters.")
@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])" + "[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,}$", message = "This field must contain: minimum eight characters, at least one uppercase letter, " + "one lowercase letter, one number and one special character.")
@ApiModelProperty(notes = "The user's password. This field must contain: minimum eight characters, " + "at least one uppercase letter, one lowercase letter, one number and one special character.", required = true, allowableValues = "range[8, infinity]")
 private  String password;


}