package com.qidian.hcm.module.authorization.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok;
import java.util.List;
@Getter
@Setter
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RolePermissionDTO {

@ApiModelProperty(value = "自助服务权限", name = "自助服务权限")
 private  List<PermissionMetaDataDTO> frontend;

@ApiModelProperty(value = "管理后台权限", name = "管理后台权限")
 private  List<PermissionMetaDataDTO> backend;


}