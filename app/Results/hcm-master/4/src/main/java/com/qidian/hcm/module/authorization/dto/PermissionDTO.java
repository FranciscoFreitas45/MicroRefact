package com.qidian.hcm.module.authorization.dto;
 import com.qidian.hcm.module.authorization.enums.ActionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {

@ApiModelProperty(value = "操作类型", name = "操作类型")
 private  ActionType action;

@ApiModelProperty(value = "条件", name = "条件")
 private  List<ConditionDTO> conditions;


}