package com.qidian.hcm.module.salary.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.salary.dto.HousingFundPlanDTO;
import com.qidian.hcm.module.salary.service.HousingFundPlanService;
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
@RequestMapping("/api/salaries/housing_fund_plans")
@Api(tags = "薪酬管理--公积金")
public class HousingFundPlanController {

@Autowired
 private  HousingFundPlanService housingFundPlanService;


@ApiOperation(value = "编辑公积金")
@PutMapping("{id}")
public Result updateHousingFunPlan(Long id,HousingFundPlanDTO housingFundPlanDTO){
    housingFundPlanService.updateHousingFundPlan(id, housingFundPlanDTO);
    return genSuccessResult();
}


@ApiOperation(value = "新增公积金")
@PostMapping
public Result addHousingFunPlan(HousingFundPlanDTO housingFundPlanDTO){
    housingFundPlanService.addHousingFundPlan(housingFundPlanDTO);
    return genSuccessResult();
}


@ApiOperation(value = "公积金列表")
@GetMapping("")
public Result<List<HousingFundPlanDTO>> getHousingFunPlanList(){
    return genSuccessResult(housingFundPlanService.getHousingFundPlans());
}


@ApiOperation(value = "删除公积金")
@DeleteMapping("{id}")
public Result deleteHousingFundPlan(Long id){
    housingFundPlanService.deleteHousingFundPlan(id);
    return genSuccessResult();
}


}