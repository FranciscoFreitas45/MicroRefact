package com.qidian.hcm.module.employee.dto;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.module.employee.enums.ContractPeriod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "合同信息")
public class EmployeeContractDTO {

@ApiModelProperty(value = "只有更新时需要传参数", name = "id")
 private  Long id;

@ApiModelProperty(value = "签约单位", name = "signUnit", required = true)
@NotBlank(message = "签约单位不能为空")
 private  String signUnit;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "开始日期", name = "startDate", required = true)
@NotNull(message = "开始日期不能为空")
 private  Date startDate;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "结束日期", name = "endDate", required = true)
@NotNull(message = "结束日期不能为空")
 private  Date endDate;

@ApiModelProperty(value = "合同期限", name = "period", required = true)
@NotNull(message = "合同期限不能为空")
 private  ContractPeriod period;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "试用期结束日期", name = "probationEndDate")
 private  Date probationEndDate;

@ApiModelProperty(value = "相关自定义字段值", name = "customizedFields", required = true)
 private  JSONObject customizedFields;


}