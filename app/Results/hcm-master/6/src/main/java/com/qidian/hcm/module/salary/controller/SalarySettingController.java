package com.qidian.hcm.module.salary.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.salary.request.SalaryCycleDateRequest;
import com.qidian.hcm.module.salary.request.SalaryDateRequest;
import com.qidian.hcm.module.salary.request.SalaryThresholdRequest;
import com.qidian.hcm.module.salary.response.SalarySettingResponse;
import com.qidian.hcm.module.salary.response.SalaryThresholdResponse;
import com.qidian.hcm.module.salary.service.SalarySettingService;
import com.qidian.hcm.module.salary.service.SalaryThresholdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
import com.qidian.hcm.Interface.SalaryThresholdService;
@RestController
@Slf4j
@RequestMapping("/api/salaries/setting")
@Api(tags = "薪酬管理--薪酬设置")
public class SalarySettingController {

@Autowired
 private  SalarySettingService salarySettingService;

@Autowired
 private  SalaryThresholdService salaryThresholdService;


@ApiOperation(value = "设置发薪日期")
@PutMapping("pay_date")
public Result setSalaryPayDate(SalaryDateRequest salaryDateRequest){
    salarySettingService.setSalaryPayDate(salaryDateRequest);
    return genSuccessResult();
}


@ApiOperation(value = "设置计薪周期")
@PutMapping("cycle")
public Result setSalaryCycleDate(SalaryCycleDateRequest salaryCycleDateRequest){
    salarySettingService.setSalaryCycleDate(salaryCycleDateRequest);
    return genSuccessResult();
}


@ApiOperation(value = "获取薪酬配置信息")
@GetMapping("config")
public Result<SalarySettingResponse> getSalaryConfigs(){
    return genSuccessResult(salarySettingService.getSalaryConfigs());
}


@ApiOperation(value = "修改免税额")
@PutMapping("threshold/{id}")
public Result updateThreshold(Long id,SalaryThresholdRequest request){
    salaryThresholdService.updateThreshold(id, request);
    return genSuccessResult();
}


@ApiOperation(value = "获取免税额列表")
@GetMapping("threshold")
public Result<List<SalaryThresholdResponse>> getSalaryThreshold(){
    return genSuccessResult(salaryThresholdService.getSalaryThreshold());
}


}