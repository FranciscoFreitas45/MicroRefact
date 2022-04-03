package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Tag's object in database.")
public class TagDTO {

@ApiModelProperty(notes = "The tag's id.")
 private  Long id;

@ApiModelProperty(notes = "The tag's name.")
 private  String name;


}