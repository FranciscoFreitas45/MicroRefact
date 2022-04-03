package com.qidian.hcm.module.organization.response;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.common.enums.YesNo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@ApiModel
public class CompanyResponse {

@ApiModelProperty(value = "主键（公司ID）", name = "id", required = true)
 private  Long id;

@ApiModelProperty(value = "公司代码", name = "code", required = true)
 private  String code;

@ApiModelProperty(value = "公司名称", name = "name", required = true)
 private  String name;

@ApiModelProperty(value = "公司简称", name = "alias", required = true)
 private  String alias;

@ApiModelProperty(value = "公司地址", name = "address", required = true)
 private  String address;

@ApiModelProperty(value = "公司注册地址", name = "register_address", required = true)
 private  String registerAddress;

@ApiModelProperty(value = "是否有效", name = "enable", required = true)
 private  YesNo enable;

@ApiModelProperty(value = "生效日期", name = "enable_time", required = true)
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date enableTime;

@ApiModelProperty(value = "上级公司ID", name = "parent_id", required = true)
 private  Long parentId;

@ApiModelProperty(value = "上级公司名称", name = "parent_name", required = true)
 private  String parentName;

@ApiModelProperty(value = "公司总负责人", name = "master")
 private  String master;

@ApiModelProperty(value = "公司法人代表", name = "legal_person")
 private  String legalPerson;

@ApiModelProperty(value = "自定义字段", name = "custom_field", required = true)
 private  JSONObject customField;

@ApiModelProperty(value = "是否允许编辑")
 private  boolean enableEdit;


}