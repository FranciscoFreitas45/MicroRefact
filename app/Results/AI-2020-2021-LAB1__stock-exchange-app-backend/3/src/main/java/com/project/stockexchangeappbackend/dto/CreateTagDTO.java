package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Tag's object to create.")
public class CreateTagDTO {

@ApiModelProperty(notes = "The tag's name.")
@Size(max = 255, message = "Length of the name can be {max} characters long.")
@NotBlank(message = "This field is required.")
 private  String name;


}