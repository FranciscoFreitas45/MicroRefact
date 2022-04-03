package com.qidian.hcm.module.salary.response;
 import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@ApiModel(value = "归档列表Response")
public class SalaryRecordMonthlyResponse {

@ApiModelProperty(value = "主键")
 private  Long id;

@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
@ApiModelProperty(value = "归档日期")
 private  Date date;

@ApiModelProperty(value = "归档名称")
 private  String name;

@ApiModelProperty(value = "是否核算")
 private  Boolean accounted;

@ApiModelProperty(value = "是否归档")
 private  Boolean recorded;

@ApiModelProperty(value = "备注")
 private  String remark;


}