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
public class DepartmentResponse {

@ApiModelProperty(value = "主键（部门ID）")
 private  Long id;

@ApiModelProperty(value = "部门名称")
 private  String name;

@ApiModelProperty(value = "部门代码")
 private  String code;

@ApiModelProperty(value = "部门简称")
 private  String alias;

@ApiModelProperty(value = "所属公司ID")
 private  Long parentCompanyId;

@ApiModelProperty(value = "上级部门ID")
 private  Long parentDepartmentId;

@ApiModelProperty(value = "编制")
 private  Integer formation;

@ApiModelProperty(value = "是否有效")
 private  YesNo enable;

@ApiModelProperty(value = "生效日期")
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
 private  Date enableTime;

@ApiModelProperty(value = "自定义字段")
 private  JSONObject customField;

@ApiModelProperty(value = "是否删除")
 private  YesNo delete;

@ApiModelProperty(value = "上级公司名称")
 private  String parentCompanyName;

@ApiModelProperty(value = "上级部门名称")
 private  String parentDepartmentName;

@ApiModelProperty(value = "部门负责人")
 private  String master;

@ApiModelProperty(value = "是否允许编辑")
 private  boolean enableEdit;


}