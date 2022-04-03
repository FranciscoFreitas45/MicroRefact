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
@ApiModel(value = "员工转正的请求")
public class QualifyEmployeeRequest {

@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@NotNull
@ApiModelProperty(value = "转正日期", name = "date", required = true)
 private  Date date;

@ApiModelProperty(value = "转正备注", name = "remark", required = true)
 private  String remark;

@ApiModelProperty(value = "附件名称", name = "fileName", required = true)
 private  String fileName;

@ApiModelProperty(value = "转正附件base64编码", name = "attachment", required = true)
 private  String attachment;


}