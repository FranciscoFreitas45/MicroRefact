package com.uec.imonitor.news.utils;
 import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.request.bean.RequestNewsShow;
public class ExcelUtil {


public HSSFCellStyle leftStyle(HSSFWorkbook wb){
    HSSFCellStyle curStyle = wb.createCellStyle();
    // 设置字体
    HSSFFont curFont = wb.createFont();
    // curFont.setFontName("Times New Roman");				//设置英文字体
    // 设置英文字体
    curFont.setFontName("微软雅黑");
    // 设置中文字体，那必须还要再对单元格进行编码设置
    curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);
    // 字体大小
    curFont.setFontHeightInPoints((short) 10);
    // 加粗
    curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 居中
    curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    curStyle.setFont(curFont);
    return curStyle;
}


public String enCodingFileName(HttpServletRequest request,String fileName){
    String agent = request.getHeader("User-Agent");
    boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
    if (isFireFox) {
        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
    } else {
        fileName = URLEncoder.encode(fileName, "UTF-8");
    }
    return fileName;
}


public void exportExcel(List<RequestNewsShow> listEntity,HttpServletRequest req,HttpServletResponse response,String fileName){
    try {
        // fileName = enCodingFileName(req, fileName.replaceAll("TO", "至") + "最新发稿文章列表");
        fileName = fileName + "最新发稿文章列表";
        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("最新发稿文章列表");
        HSSFCellStyle style = leftStyle(wb);
        // 合并单元格：
        Row header = sheet.createRow(0);
        // 横向合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, (short) 0, (short) 5));
        Cell cell = header.createCell(0);
        cell.setCellValue(fileName);
        cell.setCellStyle(style);
        header = sheet.createRow(1);
        cell = header.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        cell = header.createCell(1);
        cell.setCellValue("板块");
        cell.setCellStyle(style);
        cell = header.createCell(2);
        cell.setCellValue("标题");
        cell.setCellStyle(style);
        cell = header.createCell(3);
        cell.setCellValue("被转载次数");
        cell.setCellStyle(style);
        cell = header.createCell(4);
        cell.setCellValue("发稿时间");
        cell.setCellStyle(style);
        cell = header.createCell(5);
        cell.setCellValue("作者");
        cell.setCellStyle(style);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < listEntity.size(); i++) {
            sheet.setColumnWidth(0, 2 * 700);
            sheet.setColumnWidth(1, listEntity.get(i).getNewsSection().length() * 512);
            sheet.setColumnWidth(2, listEntity.get(i).getTitle().length() * 512);
            sheet.setColumnWidth(3, 4 * 700);
            sheet.setColumnWidth(4, listEntity.get(i).getStartDatetime().toString().length() * 230);
            sheet.setColumnWidth(5, listEntity.get(i).getNewsAuthor().toString().length() * 520);
            Row row = sheet.createRow(i + 2);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(listEntity.get(i).getNewsSection());
            row.createCell(2).setCellValue(listEntity.get(i).getTitle());
            row.createCell(3).setCellValue(listEntity.get(i).getReprintCount());
            row.createCell(4).setCellValue(simpleDateFormat.format(listEntity.get(i).getStartDatetime()));
            row.createCell(5).setCellValue(listEntity.get(i).getNewsAuthor());
        }
        write(wb, req, response, fileName + ".xls");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public HSSFCellStyle colorStyle(HSSFWorkbook wb){
    HSSFCellStyle curStyle = wb.createCellStyle();
    // 设置字体
    HSSFFont curFont = wb.createFont();
    // 设置中文字体，那必须还要再对单元格进行编码设置
    curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);
    // 字体大小
    curFont.setFontHeightInPoints((short) 10);
    // 绿字
    curFont.setColor(HSSFColor.RED.index);
    curStyle.setFont(curFont);
    // 
    curStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    return curStyle;
}


public void latestReprint(List<NewsSpreadingAnalysisDetail> listEntity,HttpServletRequest req,HttpServletResponse response,String fileName){
    try {
        // fileName = enCodingFileName(req, fileName.replaceAll("TO", "至") + "最新转载记录情况");
        fileName = fileName + "最新转载记录情况";
        HSSFWorkbook wb = new HSSFWorkbook();
        // Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("最新转载记录情况");
        HSSFCellStyle style = leftStyle(wb);
        // 合并单元格：
        Row header = sheet.createRow(0);
        // 横向合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, (short) 0, (short) 9));
        Cell cell = header.createCell(0);
        cell.setCellValue(fileName);
        cell.setCellStyle(style);
        header = sheet.createRow(1);
        cell = header.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        cell = header.createCell(1);
        cell.setCellValue("记录日期");
        cell.setCellStyle(style);
        cell = header.createCell(2);
        cell.setCellValue("转载单位");
        cell.setCellStyle(style);
        cell = header.createCell(3);
        cell.setCellValue("转载文章标题");
        cell.setCellStyle(style);
        cell = header.createCell(4);
        cell.setCellValue("原文标题");
        cell.setCellStyle(style);
        cell = header.createCell(5);
        cell.setCellValue("转载时间");
        cell.setCellStyle(style);
        cell = header.createCell(6);
        cell.setCellValue("相似度");
        cell.setCellStyle(style);
        cell = header.createCell(7);
        cell.setCellValue("转载类型");
        cell.setCellStyle(style);
        cell = header.createCell(8);
        cell.setCellValue("侵权情况");
        cell.setCellStyle(style);
        cell = header.createCell(9);
        cell.setCellValue("查看详情");
        cell.setCellStyle(style);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < listEntity.size(); i++) {
            NewsSpreadingAnalysisDetail newsSpreadingAnalysisDetail = listEntity.get(i);
            sheet.setColumnWidth(0, 2 * 700);
            if (null != newsSpreadingAnalysisDetail.getCreateDatetime()) {
                sheet.setColumnWidth(1, newsSpreadingAnalysisDetail.getCreateDatetime().toString().length() * 220);
            }
            // sheet.setColumnWidth(2, listEntity.get(i).getSourceCrawl().length()*512);
            sheet.setColumnWidth(2, 6 * 700);
            if (null != newsSpreadingAnalysisDetail.getTitle()) {
                sheet.setColumnWidth(3, newsSpreadingAnalysisDetail.getTitle().length() * 500);
            }
            if (null != newsSpreadingAnalysisDetail.getReqNewsTitle()) {
                sheet.setColumnWidth(4, newsSpreadingAnalysisDetail.getReqNewsTitle().length() * 500);
            }
            if (null != newsSpreadingAnalysisDetail.getReleaseDatetimeStr()) {
                sheet.setColumnWidth(5, newsSpreadingAnalysisDetail.getReleaseDatetimeStr().length() * 256);
            }
            sheet.setColumnWidth(6, 4 * 700);
            if (null != newsSpreadingAnalysisDetail.getReprintTypeName()) {
                sheet.setColumnWidth(7, newsSpreadingAnalysisDetail.getReprintTypeName().length() * 512);
            }
            if (null != newsSpreadingAnalysisDetail.getStatusName()) {
                sheet.setColumnWidth(8, newsSpreadingAnalysisDetail.getStatusName().length() * 512);
            }
            sheet.setColumnWidth(9, 4 * 700);
            // 添加早
            int compareTo = 0;
            if (null != newsSpreadingAnalysisDetail.getReqReportDatetime()) {
                compareTo = newsSpreadingAnalysisDetail.getReqReportDatetime().compareTo(newsSpreadingAnalysisDetail.getReleaseDatetime());
            }
            Row row = sheet.createRow(i + 2);
            // 序号
            row.createCell(0).setCellValue(i + 1);
            // 记录日期
            row.createCell(1).setCellValue(sdf.format(newsSpreadingAnalysisDetail.getCreateDatetime()));
            // 转载单位
            row.createCell(2).setCellValue(newsSpreadingAnalysisDetail.getSourceCrawl());
            // 转载文章标题
            row.createCell(3).setCellValue(newsSpreadingAnalysisDetail.getTitle());
            // 原文章标题
            row.createCell(4).setCellValue(newsSpreadingAnalysisDetail.getReqNewsTitle());
            if (compareTo > 0) {
                HSSFCellStyle style1 = colorStyle(wb);
                cell = row.createCell(4);
                // 原文章标题
                cell.setCellValue(newsSpreadingAnalysisDetail.getReqNewsTitle());
                cell.setCellStyle(style1);
            }
            // 转载时间
            row.createCell(5).setCellValue(newsSpreadingAnalysisDetail.getReleaseDatetimeStr());
            NumberFormat nf = NumberFormat.getNumberInstance();
            // 小数点精确一位数
            nf.setMaximumFractionDigits(1);
            String similaryString = String.valueOf(nf.format(newsSpreadingAnalysisDetail.getContentSimilarity() * 100));
            // 转载类型
            row.createCell(6).setCellValue(similaryString + "%");
            // 转载类型
            row.createCell(7).setCellValue(newsSpreadingAnalysisDetail.getReprintTypeName());
            // 侵权情况
            row.createCell(8).setCellValue(newsSpreadingAnalysisDetail.getStatusName());
            // 查看详情
            row.createCell(9).setCellValue(newsSpreadingAnalysisDetail.getWebpageUrl());
        }
        write(wb, req, response, fileName + ".xls");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void write(HSSFWorkbook wb,HttpServletRequest req,HttpServletResponse response,String fileName){
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        baos.flush();
        response.reset();
        response.setContentType("application/x-msdownload");
        // String title = response.encodeURL(new String(fileName.getBytes(),"iso8859-1"));
        String title = enCodingFileName(req, fileName);
        response.addHeader("Content-Disposition", "attachment;filename=" + title);
        response.setContentLength(baos.size());
        ServletOutputStream outputStream = response.getOutputStream();
        baos.writeTo(outputStream);
        baos.close();
        outputStream.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}