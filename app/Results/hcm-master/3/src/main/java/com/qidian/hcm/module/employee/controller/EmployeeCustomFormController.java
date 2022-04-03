package com.qidian.hcm.module.employee.controller;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.employee.request.CustomizedFormsRequest;
import com.qidian.hcm.module.employee.request.EnableRequest;
import com.qidian.hcm.module.employee.response.CustomizedFormLayoutResponse;
import com.qidian.hcm.module.employee.response.CustomizedFormResponse;
import com.qidian.hcm.module.employee.response.CustomizedFormsResponse;
import com.qidian.hcm.module.employee.service.EmployeeCustomizedFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
@RestController
@RequestMapping("/api/employees")
@Api(tags = "人事管理--自定义字段")
public class EmployeeCustomFormController {

@Autowired
 private  EmployeeCustomizedFormService employeeCustomizedFormService;


@ApiOperation("获取自定义表单详情")
@GetMapping("customized_forms/{id}")
@ApiImplicitParam(name = "id", value = "自定义表单ID", dataType = "long", paramType = "path", required = true)
public Result<CustomizedFormResponse> getCustomizedFormDetail(Long id){
    return genSuccessResult(employeeCustomizedFormService.getCustomizedFormsDetail(id));
}


@ApiOperation("获取自定义模块")
@GetMapping("customized_forms")
public Result<CustomizedFormsResponse> getCustomizedForms(){
    return genSuccessResult(employeeCustomizedFormService.getCustomizedForms());
}


@ApiOperation(value = "排序自定义模块", notes = "排序自定义模块")
@ApiImplicitParam(name = "ids", value = "模板id,示例[1,2]", paramType = "body")
@PutMapping(value = "/customized_forms/sort")
public Result sortForm(List<Long> ids){
    employeeCustomizedFormService.sortForms(ids);
    return genSuccessResult();
}


@ApiOperation("启用/禁用自定义表单")
@PutMapping("customized_forms/{id}/toggle_active")
@ApiImplicitParam(name = "id", value = "自定义表单ID", dataType = "long", paramType = "path", required = true)
public Result toggleActiveCustomizedForms(Long id,EnableRequest enableRequest){
    employeeCustomizedFormService.toggleActiveCustomizedForms(id, enableRequest.getEnable());
    return genSuccessResult();
}


@ApiOperation("删除自定义表单")
@DeleteMapping("customized_forms/{id}")
@ApiImplicitParam(name = "id", value = "自定义表单ID", dataType = "long", paramType = "path", required = true)
public Result deleteCustomizedForms(Long id){
    employeeCustomizedFormService.deleteCustomizedForms(id);
    return genSuccessResult();
}


@ApiOperation("更新自定义表单")
@PutMapping("customized_forms/{id}")
@ApiImplicitParam(name = "id", value = "自定义表单ID", dataType = "long", paramType = "path", required = true)
public Result updateCustomizedForms(Long id,CustomizedFormsRequest addCustomizedFormsRequest){
    employeeCustomizedFormService.updateCustomizedForms(id, addCustomizedFormsRequest);
    return genSuccessResult();
}


@ApiOperation(value = "员工入职表单自定义结构", notes = "员工入职表单自定义结构")
@GetMapping(value = "/customized_forms/layout")
public Result<CustomizedFormLayoutResponse> getCustomizedFormLayout(){
    CustomizedFormLayoutResponse response = employeeCustomizedFormService.getCustomizedFormLayout();
    return genSuccessResult(response);
}


@ApiOperation("创建自定义表单")
@PostMapping("customized_forms")
public Result createCustomizedForms(CustomizedFormsRequest addCustomizedFormsRequest){
    if (addCustomizedFormsRequest.getType() == null) {
        throw new BizException(ResultCode.FORM_TYPE_INCORRECT);
    }
    employeeCustomizedFormService.createCustomizedForms(addCustomizedFormsRequest);
    return genSuccessResult();
}


}