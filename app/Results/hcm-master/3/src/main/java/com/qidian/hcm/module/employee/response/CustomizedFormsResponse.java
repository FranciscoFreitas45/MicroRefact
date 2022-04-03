package com.qidian.hcm.module.employee.response;
 import com.qidian.hcm.module.employee.dto.CustomizedEmployeeFormDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Getter
@ApiModel("自定义模块查询实体")
public class CustomizedFormsResponse {

@ApiModelProperty(value = "岗位信息")
 private  List<CustomizedEmployeeFormDTO> position;

@ApiModelProperty(value = "基本信息")
 private  List<CustomizedEmployeeFormDTO> basic;

@ApiModelProperty(value = "其他信息")
 private  List<CustomizedEmployeeFormDTO> other;


}