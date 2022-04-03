package com.hmm.finance.financeReportDaily.controller;
 import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDaily;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDailyQueryDTO;
import com.hmm.finance.financeReportDaily.service.IFinanceReportDailyService;
import com.hmm.finance.financeReportDaily.util.ExportExcel;
import com.hmm.DTO.ExtjsPageRequest;
@RestController
@RequestMapping(value = "/financeReportDaily")
public class FinanceReportDailyController {

@Autowired
 private  IFinanceReportDailyService financeReportDailyService;


@GetMapping
public Page<FinanceReportDaily> findAll(FinanceReportDailyQueryDTO financeReportDailyQueryDTO,ExtjsPageRequest pageable){
    Page<FinanceReportDaily> page;
    page = financeReportDailyService.findAll(financeReportDailyQueryDTO.getWhereClause(financeReportDailyQueryDTO), pageable.getPageable());
    return page;
}


@RequestMapping(value = "exportExcelByYearAndMonth/{date}", method = RequestMethod.GET)
public void exportExcelByYearAndMonth(Date date,HttpServletResponse response){
    List<FinanceReportDaily> classmateList = financeReportDailyService.exportExcelByYearAndMonth(date);
    HSSFWorkbook workbook = ExportExcel.exportExcel(classmateList);
    // 设置要导出的文件的名字
    String fileName = "财务日志" + ".xls";
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    response.flushBuffer();
    workbook.write(response.getOutputStream());
}


@RequestMapping(value = "exportExcelBySelectIds/{selectIds}", method = RequestMethod.GET)
public void exportExcelBySelectIds(Long[] ids,HttpServletResponse response){
    List<FinanceReportDaily> classmateList = financeReportDailyService.exportExcelBySelectIds(ids);
    HSSFWorkbook workbook = ExportExcel.exportExcel(classmateList);
    // 设置要导出的文件的名字
    String fileName = "财务日志" + ".xls";
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    response.flushBuffer();
    workbook.write(response.getOutputStream());
}


@RequestMapping(value = "exportExcelByAll", method = RequestMethod.GET)
public void exportExcelByAll(HttpServletResponse response){
    List<FinanceReportDaily> classmateList = financeReportDailyService.exportExcelByAll();
    HSSFWorkbook workbook = ExportExcel.exportExcel(classmateList);
    // 设置要导出的文件的名字
    String fileName = "财务日志" + ".xls";
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    response.flushBuffer();
    workbook.write(response.getOutputStream());
}


@RequestMapping(value = "exportExcelByYear/{year}")
public void exportExcelByYear(Integer year,HttpServletResponse response){
    List<FinanceReportDaily> classmateList = financeReportDailyService.exportExcelByYear(year);
    HSSFWorkbook workbook = ExportExcel.exportExcel(classmateList);
    // 设置要导出的文件的名字
    String fileName = "财务日志" + ".xls";
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    response.flushBuffer();
    workbook.write(response.getOutputStream());
}


}