package com.qidian.hcm.common.jwt;
 import com.qidian.hcm.module.authorization.dto.RolePermissionDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import com.qidian.hcm.Interface.RolePermissionDTO;
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class JwtUserInfo {

 private  Long id;

 private  String tenantName;

 private  String username;

 private  List<JWTGrantedAuthority> authorities;

 private  RolePermissionDTO rolePermission;

 private  boolean superAdmin;

 private  Long employeeId;

public JwtUserInfo(Long id, String tenantName) {
    this.id = id;
    this.tenantName = tenantName;
}
}