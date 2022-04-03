package com.qidian.hcm.module.organization.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.organization.request.CreateCompanyRequest;
import com.qidian.hcm.module.organization.request.EnableCompanyRequest;
import com.qidian.hcm.module.organization.request.UpdateCompanyRequest;
import com.qidian.hcm.module.organization.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
import java.lang.Boolean.FALSE;
import java.lang.Boolean.TRUE;
@RestController
@Slf4j
@RequestMapping("/api/company")
@Api(tags = "组织机构--公司相关")
public class CompanyController {

@Autowired
 private  CompanyService companyService;


@ApiImplicitParams({ @ApiImplicitParam(name = "active", value = "是否有效", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "keyword", value = "查询关键字", dataType = "int", paramType = "query"), @ApiImplicitParam(name = "pageNo", value = "页号", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, dataType = "int", paramType = "query") })
@ApiOperation("获取公司信息分页列表")
@GetMapping()
@PreAuthorize("hasAuthority('backend|company|GET') or hasRole('ROLE_ADMIN')")
public Result getCompanyList(Integer active,String keyword,Integer pageNo,Integer pageSize){
    return genSuccessResult(companyService.getCompanies(active, keyword, pageNo, pageSize));
}


@PreAuthorize("hasAuthority('backend|company|EDIT') or hasRole('ROLE_ADMIN')")
@PutMapping("/enable")
public Result enableCompanyById(EnableCompanyRequest request){
    companyService.enableOrgById(request.getId(), request.getEnable());
    return genSuccessResult();
}


@PreAuthorize("hasAuthority('backend|company|EDIT') or hasRole('ROLE_ADMIN')")
@ApiOperation("删除公司信息")
@DeleteMapping("/{id}")
@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
public Result deleteCompanyById(Long id){
    companyService.deleteOrgById(id);
    return genSuccessResult();
}


@ApiOperation(value = "组织架构树选择框")
@GetMapping(value = "/tree/selection")
public Result treeSelection(){
    return genSuccessResult(companyService.getOrganizationTree(TRUE, null));
}


@ApiOperation(value = "首页组织机构tree")
@GetMapping(value = "/tree")
public Result tree(){
    return genSuccessResult(companyService.getOrganizationTree(TRUE, ActionType.GET));
}


@PreAuthorize("hasAuthority('backend|company|EDIT') or hasRole('ROLE_ADMIN')")
@ApiOperation("修改公司信息")
@PutMapping("/{id}")
public Result updateCompany(Long id,UpdateCompanyRequest updateCompanyRequest){
    updateCompanyRequest.setId(id);
    companyService.updateCompany(updateCompanyRequest);
    return genSuccessResult();
}


@PreAuthorize("hasAuthority('backend|company|GET') or hasRole('ROLE_ADMIN')")
@ApiOperation(value = "只包含公司tree")
@GetMapping(value = "/companyTree")
public Result companyTree(){
    return genSuccessResult(companyService.getOrganizationTree(FALSE, ActionType.GET));
}


@PreAuthorize("hasAuthority('backend|company|GET') or hasRole('ROLE_ADMIN')")
@ApiImplicitParam(name = "id", value = "公司ID", dataType = "Integer", paramType = "path")
@ApiOperation("获取公司信息单条记录")
@GetMapping("/{id}")
public Result getCompanyById(Long id){
    return genSuccessResult(companyService.getCompanyById(id));
}


@PreAuthorize("hasAuthority('backend|company|EDIT') or hasRole('ROLE_ADMIN')")
@ApiOperation("创建公司")
@PostMapping()
public Result createCompany(CreateCompanyRequest createCompanyRequest){
    companyService.createCompany(createCompanyRequest);
    return genSuccessResult();
}


}