package com.qidian.hcm.module.employee.response;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.module.employee.dto.ResignHandoverManDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@ApiModel("获取离职员工信息")
public class ResignedEmployeeResponse {

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty(value = "离职日期", name = "date", required = true)
 private  Date date;

@ApiModelProperty(value = "离职交接人id和姓名", name = "handover_man_name", required = true)
 private  ResignHandoverManDTO handoverMan;

@ApiModelProperty(value = "离职原因", name = "reason", required = true)
 private  String reason;

@ApiModelProperty(value = "离职信在oss上的地址", name = "attachment", required = true)
 private  String attachment;

@ApiModelProperty(value = "离职信的原始名称", name = "filename", required = true)
 private  String fileName;

@ApiModelProperty(value = "fileId", name = "fileId", required = true)
 private  Long fileId;


}