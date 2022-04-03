package com.zis.requirement.controller;
 import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.requirement.biz.BookAmountService;
import com.zis.requirement.dto.RequirementCollectScheduleDTO;
@Controller
@RequestMapping(value = "/requirement")
public class RequirementCollectScheduleController {

@Autowired
 private  BookAmountService bookAmountService;


public void setResponseHeader(HttpServletResponse response){
    try {
        // 两种方法都可以
        response.setContentType("application/msexcel;charset=UTF-8");
        // response.setContentType("application/octet-stream;charset=iso-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("教材需求统计.csv", "UTF-8"));
        // 客户端不缓存
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public void createNewRow(Sheet sheet,int index,RequirementCollectScheduleDTO dto){
    Row row = sheet.createRow(index);
    // "学校", "学院", "专业", "年级", "学期", "收录员", "条码数量"
    row.createCell(0).setCellValue(dto.getCollege());
    row.createCell(1).setCellValue(dto.getInstitute());
    row.createCell(2).setCellValue(dto.getPartName());
    row.createCell(3).setCellValue("第" + dto.getGrade() + "学年");
    row.createCell(4).setCellValue("第" + dto.getTerm() + "学期");
    row.createCell(5).setCellValue(dto.getOperator());
    row.createCell(6).setCellValue(dto.getCount());
}


@RequiresPermissions(value = { "requirement:exportRequirementCollectSchedule" })
@RequestMapping(value = "/exportRequirementCollectSchedule")
public String exportSchedule(HttpServletResponse response,Boolean groupByOperator){
    List<RequirementCollectScheduleDTO> list = bookAmountService.findRequirementCollectSchedule(groupByOperator);
    Workbook book = new HSSFWorkbook();
    Sheet sheet = book.createSheet();
    Row row = sheet.createRow(0);
    row.createCell(0).setCellValue("学校");
    row.createCell(1).setCellValue("学院");
    row.createCell(2).setCellValue("专业");
    row.createCell(3).setCellValue("年级");
    row.createCell(4).setCellValue("学期");
    row.createCell(5).setCellValue("收录员");
    row.createCell(6).setCellValue("条码数量");
    int i = 1;
    for (RequirementCollectScheduleDTO dto : list) {
        // XXX 过滤大一和大四第二学期
        if (dto.getGrade() == 1 || (dto.getGrade() == 4 && dto.getTerm() == 2)) {
            continue;
        }
        createNewRow(sheet, i, dto);
        i++;
    }
    // 导出
    setResponseHeader(response);
    try {
        book.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return "sss.html";
}


}