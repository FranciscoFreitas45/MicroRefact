package com.qidian.hcm.module.organization.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "更新岗位的请求类")
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePositionRequest extends CreatePositionRequest{

@ApiModelProperty(value = "主键（岗位ID）", required = true)
 private  Long id;


}