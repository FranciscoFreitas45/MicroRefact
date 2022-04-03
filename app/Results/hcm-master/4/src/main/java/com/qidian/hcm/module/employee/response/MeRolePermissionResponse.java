package com.qidian.hcm.module.employee.response;
 import com.qidian.hcm.module.authorization.dto.RolePermissionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class MeRolePermissionResponse {

 private  boolean superAdmin;

 private  RolePermissionDTO permission;


}