package com.qidian.hcm.module.employee.request;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "员工调岗的请求")
public class TransferEmployeeRequest {

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@NotNull
@ApiModelProperty(value = "调岗日期", name = "date", required = true)
 private  Date date;

@ApiModelProperty(value = "调岗原因", name = "reason", required = true)
 private  String reason;

@NotNull
@ApiModelProperty(value = "新的公司", name = "company_id", required = true)
 private  Long companyId;

@NotNull
@ApiModelProperty(value = "新的部门", name = "department_id", required = true)
 private  Long departmentId;

@NotNull
@ApiModelProperty(value = "新的岗位", name = "position_id", required = true)
 private  Long positionId;

@NotNull
@ApiModelProperty(value = "新的直接主管", name = "master_id", required = true)
 private  Long masterId;


}