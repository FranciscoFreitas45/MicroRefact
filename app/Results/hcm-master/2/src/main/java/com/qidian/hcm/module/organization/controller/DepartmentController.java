package com.qidian.hcm.module.organization.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.common.utils.ResultGenerator;
import com.qidian.hcm.module.organization.dto.PositionGradeDTO;
import com.qidian.hcm.module.organization.request.CreateDepartmentRequest;
import com.qidian.hcm.module.organization.request.EnableDepartmentRequest;
import com.qidian.hcm.module.organization.request.UpdateDepartmentRequest;
import com.qidian.hcm.module.organization.response.DepartmentResponse;
import com.qidian.hcm.module.organization.service.DepartmentService;
import com.qidian.hcm.module.organization.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import com.qidian.hcm.Interface.PositionService;
@RestController
@Slf4j
@RequestMapping("/api/department")
@Api(tags = "组织机构--部门相关")
@SuppressWarnings("PMD")
public class DepartmentController {

@Autowired
 private  DepartmentService departmentService;

@Autowired
 private  PositionService positionService;


@ApiOperation("启用禁用部门")
@PutMapping("/enable")
public Result enableDepartmentById(EnableDepartmentRequest request){
    departmentService.enableById(request.getId(), request.getEnable());
    return ResultGenerator.genSuccessResult();
}


@ApiImplicitParams({ @ApiImplicitParam(name = "active", value = "是否有效", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "keyword", value = "查询关键字", dataType = "int", paramType = "query"), @ApiImplicitParam(name = "pageNo", value = "页号", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, dataType = "int", paramType = "query") })
@ApiOperation("获取部门信息分页列表")
@GetMapping()
@PreAuthorize("hasAuthority('backend|department|GET') or hasRole('ROLE_ADMIN')")
public Result<Page<DepartmentResponse>> getDepartmentList(Integer active,String keyword,Integer pageNo,Integer pageSize){
    return ResultGenerator.genSuccessResult(departmentService.getDepartments(active, keyword, pageNo, pageSize));
}


@ApiImplicitParam(name = "id", value = "部门ID", dataType = "Integer", paramType = "path")
@ApiOperation("获取部门信息单条记录")
@GetMapping("/{id}")
public Result<DepartmentResponse> getPositionById(Long id){
    return ResultGenerator.genSuccessResult(departmentService.getDepartmentById(id));
}


@ApiOperation("创建部门")
@PostMapping()
public Result createDepartment(CreateDepartmentRequest createDepartmentRequest){
    departmentService.createDepartment(createDepartmentRequest);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation("根据部门ID获取岗位信息")
@GetMapping("{id}/positions")
public Result<List<PositionGradeDTO>> getPositionByDepartmentId(Long id){
    return ResultGenerator.genSuccessResult(positionService.getPositionByDepartmentId(id));
}


@ApiOperation("删除部门信息")
@DeleteMapping("/{id}")
@ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "int", paramType = "path")
public Result deleteCompanyById(Long id){
    departmentService.deleteById(id);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation("修改部门信息")
@PutMapping("/{id}")
@ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "int", paramType = "path")
public Result updateCompany(Long id,UpdateDepartmentRequest updateCompanyRequest){
    updateCompanyRequest.setId(id);
    departmentService.updateDepartment(updateCompanyRequest);
    return ResultGenerator.genSuccessResult();
}


}