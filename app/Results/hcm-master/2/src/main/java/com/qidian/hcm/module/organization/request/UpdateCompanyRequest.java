package com.qidian.hcm.module.organization.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("公司请求对象")
public class UpdateCompanyRequest extends CreateCompanyRequest{

@ApiModelProperty(value = "主键（公司ID）", name = "id", hidden = true)
 private  Long id;


}