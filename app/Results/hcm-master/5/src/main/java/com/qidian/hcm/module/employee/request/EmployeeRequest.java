package com.qidian.hcm.module.employee.request;
 import com.qidian.hcm.module.employee.dto.EmployeeBasicInfoDTO;
import com.qidian.hcm.module.employee.dto.EmployeeCustomizedFormsDTO;
import com.qidian.hcm.module.employee.dto.EmployeeOtherInfoDTO;
import com.qidian.hcm.module.employee.dto.EmployeePositionInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.qidian.hcm.Interface.EmployeeOtherInfoDTO;
@Getter
@Setter
public class EmployeeRequest extends EmployeeBasicRequest{

@ApiModelProperty(value = "岗位信息", required = true)
@NotNull
@Valid
 private  EmployeePositionInfoDTO positionInfo;

@ApiModelProperty(value = "基本信息", required = true)
@NotNull
@Valid
 private  EmployeeBasicInfoDTO basicInfo;

@ApiModelProperty(value = "其他信息", required = true)
@NotNull
@Valid
 private  EmployeeOtherInfoDTO otherInfo;

@ApiModelProperty(value = "自定义字段信息")
@Valid
 private  List<EmployeeCustomizedFormsDTO> customizedForms;

@ApiModelProperty(value = "userId")
 private  Long userId;


}