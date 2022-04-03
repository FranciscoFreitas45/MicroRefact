package com.qidian.hcm.module.employee.dto;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "岗位信息")
public class EmployeePositionDTO {

@ApiModelProperty(value = "只有更新时需要传参数", name = "id")
 private  Long id;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "开始时间", required = true)
@NotNull(message = "开始时间不能为空")
 private  Date startDate;

@ApiModelProperty(value = "公司ID", required = true)
@NotNull(message = "公司ID不能为空")
 private  Long companyId;

@ApiModelProperty(value = "部门ID", required = true)
@NotNull(message = "部门ID不能为空")
 private  Long departmentId;

@ApiModelProperty(value = "岗位ID", required = true)
@NotNull(message = "岗位ID不能为空")
 private  Long positionId;

@ApiModelProperty(value = "直接主管")
 private  Long leaderId;

@ApiModelProperty(value = "自定义字段")
 private  JSONObject customizedFields;


}