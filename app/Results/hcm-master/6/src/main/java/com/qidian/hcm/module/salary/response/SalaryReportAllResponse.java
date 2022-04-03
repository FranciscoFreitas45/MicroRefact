package com.qidian.hcm.module.salary.response;
 import com.qidian.hcm.module.salary.dto.SalaryReportItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@ApiModel(value = "薪资项值报表明细Response")
public class SalaryReportAllResponse {

@ApiModelProperty(value = "员工ID", required = true)
 private  Long employeeId;

@ApiModelProperty(value = "员工名称")
 private  String employeeName;

@ApiModelProperty(value = "员工编号")
 private  String employeeNo;

@ApiModelProperty(value = "薪资项")
 private  List<SalaryReportItemDTO> salaryItemDTO;


}