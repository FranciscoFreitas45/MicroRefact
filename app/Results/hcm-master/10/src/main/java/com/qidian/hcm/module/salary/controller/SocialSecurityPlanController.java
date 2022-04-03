package com.qidian.hcm.module.salary.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.salary.dto.SocialSecurityPlanDTO;
import com.qidian.hcm.module.salary.service.SocialSecurityPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
@RestController
@Slf4j
@RequestMapping("/api/salaries/social_security_plans")
@Api(tags = "薪酬管理--社保方案")
public class SocialSecurityPlanController {

@Autowired
 private  SocialSecurityPlanService socialSecurityPlanService;


@ApiOperation(value = "新增社保方案")
@PostMapping
public Result addSecurityPlan(SocialSecurityPlanDTO socialSecurityPlanDTO){
    socialSecurityPlanService.addSecurityPlan(socialSecurityPlanDTO);
    return genSuccessResult();
}


@ApiOperation(value = "编辑社保方案")
@PutMapping("{id}")
public Result updateSecurityPlan(Long id,SocialSecurityPlanDTO socialSecurityPlanDTO){
    socialSecurityPlanService.updateSecurityPlan(id, socialSecurityPlanDTO);
    return genSuccessResult();
}


@ApiOperation(value = "删除社保方案")
@DeleteMapping("{id}")
public Result deleteSecurityPlan(Long id){
    socialSecurityPlanService.deleteSecurityPlan(id);
    return genSuccessResult();
}


@ApiOperation(value = "获取社保方案列表")
@GetMapping()
public Result<List<SocialSecurityPlanDTO>> getSecurityPlanList(){
    return genSuccessResult(socialSecurityPlanService.getSecurityPlans());
}


}