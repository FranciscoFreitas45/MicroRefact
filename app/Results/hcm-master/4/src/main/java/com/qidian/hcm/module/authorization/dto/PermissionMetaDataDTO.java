package com.qidian.hcm.module.authorization.dto;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.MenuType;
import com.qidian.hcm.module.authorization.enums.PlatformType;
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
@NoArgsConstructor
@AllArgsConstructor
public class PermissionMetaDataDTO {

@ApiModelProperty(value = "菜单名称", name = "菜单名称")
 private  String name;

@ApiModelProperty(value = "菜单编码", name = "菜单编码")
 private  MenuCode code;

@ApiModelProperty(value = "菜单类型", name = "菜单类型", example = "cataloge,menu,test")
 private  MenuType type;

@JsonIgnore
@ApiModelProperty(value = "平台类型", name = "平台类型")
 private  PlatformType platformType;

@ApiModelProperty(value = "权限列表", name = "权限列表")
 private  List<PermissionDTO> permissions;


}