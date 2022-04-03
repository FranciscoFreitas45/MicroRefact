package com.qidian.hcm.module.employee.dto;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "工作经历")
public class EmployeeWorkExperienceDTO {

@ApiModelProperty(value = "只有更新时需要传参数", name = "id")
 private  Long id;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "开始时间", required = true)
@NotNull(message = "开始时间不能为空")
 private  Date startTime;

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "结束时间", required = true)
@NotNull(message = "结束时间不能为空")
 private  Date endTime;

@ApiModelProperty(value = "工作单位", required = true)
@NotBlank(message = "工作单位不能为空")
 private  String workUnit;

@ApiModelProperty(value = "任职岗位", required = true)
@NotBlank(message = "任职岗位不能为空")
 private  String jobPosition;

@ApiModelProperty(value = "离职原因", required = true)
@NotBlank(message = "离职原因不能为空")
 private  String leaveReason;


}