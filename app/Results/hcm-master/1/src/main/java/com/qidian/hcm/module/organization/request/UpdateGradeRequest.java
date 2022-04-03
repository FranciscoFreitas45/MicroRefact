package com.qidian.hcm.module.organization.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "更新职级的请求类")
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGradeRequest extends CreateGradeRequest{

@ApiModelProperty(value = "主键（职级 ID）", hidden = true)
 private  Long id;


}