package com.qidian.hcm.module.employee.dto;
 import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.module.employee.enums.Degree;
import com.qidian.hcm.module.employee.enums.Education;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "教育信息")
public class EmployeeEducationDTO {

@ApiModelProperty(value = "只有更新时需要传参数")
 private  Long id;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "开始时间", required = true)
@NotNull(message = "开始时间不能为空")
 private  Date startTime;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "结束时间", required = true)
@NotNull(message = "结束时间不能为空")
 private  Date endTime;

@ApiModelProperty(value = "学校", required = true)
@NotBlank(message = "学校不能为空")
 private  String school;

@ApiModelProperty(value = "学历", required = true)
@NotNull(message = "学历不能为空")
 private  Education education;

@ApiModelProperty(value = "是否全日制", required = true)
@NotNull(message = "是否全日制不能为空")
 private  Boolean fullTime;

@ApiModelProperty(value = "是否最高学历", required = true)
@NotNull(message = "是否最高学历不能为空")
 private  Boolean highest;

@ApiModelProperty(value = "学位")
 private  Degree degree;

@ApiModelProperty(value = "自定义字段,配置了必填")
 private  JSONObject customizedFields;


}