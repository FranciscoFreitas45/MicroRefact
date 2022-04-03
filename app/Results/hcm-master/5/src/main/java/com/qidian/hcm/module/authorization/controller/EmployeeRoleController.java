package com.qidian.hcm.module.authorization.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.authorization.dto.EmployeeRoleDTO;
import com.qidian.hcm.module.employee.request.FilterEmployeeRequest;
import com.qidian.hcm.module.employee.service.EmployeePermissionService;
import com.qidian.hcm.module.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import java.util.List;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
import com.qidian.hcm.Interface.EmployeePermissionService;
@RestController
@RequestMapping("/api/authorization")
@Api(tags = "权限管理--员工信息")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EmployeeRoleController {

@Autowired
 private  EmployeeService employeeService;

@Autowired
 private  EmployeePermissionService employeePermissionService;


@ApiOperation(value = "员工角色信息", notes = "员工角色信息")
@GetMapping("employees/{id}/roles")
public Result<EmployeeRoleDTO> getEmployeeRoles(Long employeeId){
    EmployeeRoleDTO employeeWithRoleInfo = employeeService.getEmployeeWithRoleInfo(employeeId);
    return genSuccessResult(employeeWithRoleInfo);
}


@ApiOperation(value = "员工列表-带角色信息", notes = "员工列表-带角色信息")
@GetMapping("employees")
public Result<Page<EmployeeRoleDTO>> getEmployees(FilterEmployeeRequest filterEmployeeRequest){
    Page<EmployeeRoleDTO> pageBean = employeeService.pageEmployeesWithRole(filterEmployeeRequest);
    return genSuccessResult(pageBean);
}


@ApiOperation(value = "修改员工角色信息", notes = "修改员工角色信息")
@PostMapping("employees/{id}/roles")
public Result updateEmployeeRole(List<Long> roleIds,Long employeeId){
    employeePermissionService.editEmployeeRole(employeeId, roleIds);
    return genSuccessResult();
}


}