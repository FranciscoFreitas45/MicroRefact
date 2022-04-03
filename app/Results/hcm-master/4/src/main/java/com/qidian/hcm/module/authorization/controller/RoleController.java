package com.qidian.hcm.module.authorization.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.authorization.dto.RoleDTO;
import com.qidian.hcm.module.authorization.dto.RolePermissionDTO;
import com.qidian.hcm.module.authorization.request.RoleEditRequest;
import com.qidian.hcm.module.authorization.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import java.util.List;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
@RestController
@RequestMapping("/api/authorization")
@Api(tags = "权限管理--角色信息")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

@Autowired
 private  RoleService roleService;


@ApiOperation(value = "保存角色权限配置", notes = "保存角色权限配置")
@PostMapping("roles/{id}/permissions")
public Result saveRolePermissionsList(RolePermissionDTO rolePermissionDTO,Long roleId){
    roleService.saveRolePermissionsList(rolePermissionDTO, roleId);
    return genSuccessResult();
}


@ApiOperation(value = "删除角色", notes = "删除角色")
@DeleteMapping("roles/{id}")
public Result updateRole(Long roleId){
    roleService.deleteRole(roleId);
    return genSuccessResult();
}


@ApiOperation(value = "获取角色列表", notes = "获取角色列表")
@GetMapping("roles")
public Result getRoleList(){
    List<RoleDTO> allRoleList = roleService.findAllRoleList();
    return genSuccessResult(allRoleList);
}


@ApiOperation(value = "获取角色权限配置", notes = "获取角色权限配置")
@GetMapping("roles/{id}/permissions")
public Result getRolePermissionsList(Long roleId){
    return genSuccessResult(roleService.getRolePermissionsList(roleId));
}


@ApiOperation(value = "新增角色", notes = "新增角色")
@PostMapping("roles")
public Result addRole(RoleEditRequest roleEditDTO){
    return genSuccessResult(roleService.addRole(roleEditDTO));
}


}