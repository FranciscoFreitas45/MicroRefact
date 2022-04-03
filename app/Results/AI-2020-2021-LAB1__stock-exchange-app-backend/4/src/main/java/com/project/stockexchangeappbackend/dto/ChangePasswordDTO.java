package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Change password object.")
public class ChangePasswordDTO {

@NotBlank(message = "This field is required.")
@Length(min = 8, message = "This field must contain at least {min} characters.")
@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])" + "[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,}$", message = "This field must contain: minimum eight characters, at least one uppercase letter, " + "one lowercase letter, one number and one special character.")
@ApiModelProperty(notes = "The user's new password. This field must contain: minimum eight characters, " + "at least one uppercase letter, one lowercase letter, one number and one special character.", required = true, allowableValues = "range[8, infinity]")
 private  String newPassword;

@NotBlank(message = "This field is required.")
@Length(min = 8, message = "This field must contain at least {min} characters.")
@ApiModelProperty(notes = "The current user's password.")
 private  String oldPassword;


}