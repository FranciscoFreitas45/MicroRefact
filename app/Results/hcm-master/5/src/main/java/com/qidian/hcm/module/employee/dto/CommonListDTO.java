package com.qidian.hcm.module.employee.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@ApiModel(value = "公用ID，Name实体")
@AllArgsConstructor
public class CommonListDTO {

@ApiModelProperty(value = "id", name = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "名称", name = "content", required = true)
 private  String name;


}