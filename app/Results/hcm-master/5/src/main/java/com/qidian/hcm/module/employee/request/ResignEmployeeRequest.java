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
@ApiModel(value = "员工离职的请求")
public class ResignEmployeeRequest {

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@NotNull
@ApiModelProperty(value = "离职日期", name = "date", required = true)
 private  Date date;

@ApiModelProperty(value = "离职原因", name = "reason", required = true)
 private  String reason;

@NotNull
@ApiModelProperty(value = "交接人", name = "handover_man_id", required = true)
 private  Long handoverManId;

@ApiModelProperty(value = "附件名称", name = "file_name", required = true)
 private  String fileName;

@ApiModelProperty(value = "离职附件base64编码", name = "attachment", required = true)
 private  String attachment;


}