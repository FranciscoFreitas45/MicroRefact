package com.qidian.hcm.module.salary.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.salary.request.SalaryItemRequest;
import com.qidian.hcm.module.salary.response.SalaryItemResponse;
import com.qidian.hcm.module.salary.response.SalaryItemsOptionResponse;
import com.qidian.hcm.module.salary.service.SalaryItemService;
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
@RequestMapping("/api/salaries/items")
@Api(tags = "薪酬管理--薪资项")
public class SalaryItemController {

@Autowired
 private  SalaryItemService salaryItemService;


@ApiOperation(value = "新增薪资项")
@PostMapping
public Result addSalaryItem(SalaryItemRequest salaryItemRequest){
    return genSuccessResult(salaryItemService.addSalaryItem(salaryItemRequest));
}


@ApiOperation(value = "获取薪资项列表")
@GetMapping()
public Result<List<SalaryItemResponse>> getSalaryItems(){
    return genSuccessResult(salaryItemService.getSalaryItems());
}


@ApiOperation(value = "编辑薪资项页面--获取薪资项列表包含内置")
@GetMapping("option")
public Result<List<SalaryItemsOptionResponse>> getSalaryItemOption(){
    return genSuccessResult(salaryItemService.getSalaryItemOption());
}


@ApiOperation(value = "删除薪资项")
@DeleteMapping("{id}")
public Result deleteSalaryItem(Long id){
    salaryItemService.deleteSalaryItem(id);
    return genSuccessResult();
}


@ApiOperation(value = "编辑薪资项")
@PutMapping("{id}")
public Result updateSalaryItem(Long id,SalaryItemRequest salaryItemRequest){
    salaryItemService.updateSalaryItem(id, salaryItemRequest);
    return genSuccessResult();
}


}