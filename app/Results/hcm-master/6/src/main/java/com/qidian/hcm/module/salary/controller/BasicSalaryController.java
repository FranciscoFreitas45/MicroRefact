package com.qidian.hcm.module.salary.controller;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.module.center.request.UploadFileRequest;
import com.qidian.hcm.module.salary.request.FilterSalaryRecordRequest;
import com.qidian.hcm.module.salary.response.SalaryRecordMonthlyResponse;
import com.qidian.hcm.module.salary.response.SalaryReportAllResponse;
import com.qidian.hcm.module.salary.service.BasicSalaryService;
import com.qidian.hcm.module.salary.service.SalarySettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.qidian.hcm.common.utils.ResultGenerator.genSuccessResult;
@Controller
@Slf4j
@RequestMapping("/api/salaries")
@Api(tags = "薪酬管理--基本信息")
public class BasicSalaryController {

@Autowired
 private  BasicSalaryService basicSalaryService;

@Autowired
 private  SalarySettingService salarySettingService;


@ApiOperation(value = "导入薪资明细")
@PostMapping("import")
@ResponseBody
public Result importSalaryMonthExcel(UploadFileRequest uploadFileRequest){
    basicSalaryService.importSalaryFromExcel(uploadFileRequest);
    return genSuccessResult();
}


public void writeFileToResponse(HttpServletResponse response,String fileName,HSSFWorkbook wb){
    try (OutputStream output = response.getOutputStream()) {
        String attachmentName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + attachmentName);
        // 前端协议
        response.setHeader("type", "download");
        wb.write(output);
    } catch (IOException e) {
        log.error(e.getMessage(), e);
    }
}


@ApiOperation(value = "查询归档记录")
@GetMapping("records")
@ResponseBody
public Result<Page<SalaryRecordMonthlyResponse>> getSalaryRecords(FilterSalaryRecordRequest recordRequest){
    return genSuccessResult(basicSalaryService.getSalaryRecords(recordRequest));
}


@ApiOperation(value = "导出薪资明细模板")
@GetMapping("export_templet")
public void exportSalaryMonthExcelTemplate(HttpServletResponse response){
    HSSFWorkbook wb = basicSalaryService.exportTemplet();
    String fileName = "薪资明细模板.xls";
    writeFileToResponse(response, fileName, wb);
}


@ApiOperation(value = "获取薪资项列表")
@GetMapping("salary_items")
@ResponseBody
public Result getSalaryItems(){
    return genSuccessResult(basicSalaryService.getSalaryItemList());
}


@ApiOperation(value = "获取核算状态 1-已核算|0-未核算")
@GetMapping("account_status")
@ResponseBody
public Result getAccountStatus(){
    return genSuccessResult(basicSalaryService.getAccountStatus());
}


@ApiOperation(value = "本月报表--明细")
@GetMapping("report/{month}")
@ResponseBody
public Result<Page<SalaryReportAllResponse>> getSalaryMonthReport(FilterSalaryRecordRequest recordRequest,String month){
    return genSuccessResult(basicSalaryService.getSalaryMonthReport(month, recordRequest));
}


@ApiOperation(value = "导出薪资明细")
@GetMapping("export")
public void exportSalaryMonthExcel(String month,HttpServletResponse response){
    HSSFWorkbook wb = basicSalaryService.exportMonthlyReport(month);
    String fileName = "薪资明细(" + month + ").xls";
    writeFileToResponse(response, fileName, wb);
}


@ApiOperation(value = "取消核算")
@PutMapping("accounting_cancel")
@ResponseBody
public Result accountingCancel(){
    basicSalaryService.accountingCancel();
    return genSuccessResult();
}


@ApiOperation(value = "归档")
@PostMapping("records")
@ResponseBody
public Result saveSalaryRecord(){
    basicSalaryService.saveSalaryRecord();
    return genSuccessResult();
}


@ApiOperation(value = "获取当前计薪周期的开始时间和结束时间")
@GetMapping("current_cycle_time")
@ResponseBody
public Result currentCycleTime(){
    return genSuccessResult(salarySettingService.getCurrentSalaryCycleTime());
}


@ApiOperation(value = "薪酬核算")
@PutMapping("accounting")
@ResponseBody
public Result accountingSalary(){
    basicSalaryService.accountingSalary();
    return genSuccessResult();
}


}