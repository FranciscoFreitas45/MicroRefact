package com.hmm.employee.util;
 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import com.hmm.employee.entity.EmployeeDTO;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDaily;
public class ExportExcel {


public HSSFWorkbook exportExcel(List<EmployeeDTO> classmateList){
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("信息表");
    sheet.setColumnWidth(0, 3000);
    HSSFCellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    int rowNum = 1;
    String[] headers = { "员工编号", "员工姓名", "员工账户", "性别", "职称", "类别", "联系方式", "籍贯", "身份证", "入职日期", "离职时间", "部门", "简介" };
    // headers表示excel表中第一行的表头
    HSSFRow row = sheet.createRow(0);
    // 在excel表中添加表头
    for (int i = 0; i < headers.length; i++) {
        HSSFCell cell = row.createCell(i);
        HSSFRichTextString text = new HSSFRichTextString(headers[i]);
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }
    // 在表中存放查询到的数据放入对应的列
    for (EmployeeDTO dto : classmateList) {
        HSSFRow row1 = sheet.createRow(rowNum);
        Date endDate1 = dto.getEndDate();
        Date entryDate1 = dto.getEntryDate();
        SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = null;
        String entryDate = null;
        if (endDate1 != null) {
            endDate = timeFormater.format(endDate1);
        }
        if (entryDate1 != null) {
            entryDate = timeFormater.format(entryDate1);
        }
        HSSFCell cell = row1.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getEmpNo());
        cell = row1.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getEmpName());
        cell = row1.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getUserName());
        cell = row1.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getEmpSex());
        cell = row1.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getGroupName());
        cell = row1.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getJobtype());
        cell = row1.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getTel());
        cell = row1.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getAddress());
        cell = row1.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getIdcard());
        cell = row1.createCell(9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(entryDate);
        cell = row1.createCell(10);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(endDate);
        cell = row1.createCell(11);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getDeptName());
        cell = row1.createCell(12);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(dto.getIntroduce());
        rowNum++;
    }
    return workbook;
}


}