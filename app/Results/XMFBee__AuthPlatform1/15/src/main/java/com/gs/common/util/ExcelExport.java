package com.gs.common.util;
 import org.apache.poi.hssf.usermodel;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
public class ExcelExport {

 private  String title;

 private  String[] rowName;

 private  List<Object[]> dataList;

 private  HttpServletResponse response;

// 传入要导入的数据
public ExcelExport(String title, String[] rowName, List<Object[]> dataList, HttpServletResponse response) {
    this.title = title;
    this.rowName = rowName;
    this.dataList = dataList;
    this.response = response;
}
public HSSFCellStyle getStyle(HSSFWorkbook workbook){
    // 设置字体
    HSSFFont font = workbook.createFont();
    // 设置字体大小
    // font.setFontHeightInPoints((short)10);
    // 字体加粗
    // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 设置字体名字
    font.setFontName("Courier New");
    // 设置样式;
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置底边框;
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    // 设置底边框颜色;
    style.setBottomBorderColor(HSSFColor.BLACK.index);
    // 设置左边框;
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    // 设置左边框颜色;
    style.setLeftBorderColor(HSSFColor.BLACK.index);
    // 设置右边框;
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    // 设置右边框颜色;
    style.setRightBorderColor(HSSFColor.BLACK.index);
    // 设置顶边框;
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    // 设置顶边框颜色;
    style.setTopBorderColor(HSSFColor.BLACK.index);
    // 在样式用应用设置的字体;
    style.setFont(font);
    // 设置自动换行;
    style.setWrapText(false);
    // 设置水平对齐的样式为居中对齐;
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 设置垂直对齐的样式为居中对齐;
    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    return style;
}


public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook){
    // 设置字体
    HSSFFont font = workbook.createFont();
    // 设置字体大小
    font.setFontHeightInPoints((short) 11);
    // 字体加粗
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 设置字体名字
    font.setFontName("Courier New");
    // 设置样式;
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置底边框;
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    // 设置底边框颜色;
    style.setBottomBorderColor(HSSFColor.BLACK.index);
    // 设置左边框;
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    // 设置左边框颜色;
    style.setLeftBorderColor(HSSFColor.BLACK.index);
    // 设置右边框;
    style.setBorderRight(HSSFCellStyle.BORDER_DASH_DOT_DOT);
    // 设置右边框颜色;
    style.setRightBorderColor(HSSFColor.BLACK.index);
    // 设置顶边框;
    style.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT_DOT);
    // 设置顶边框颜色;
    style.setTopBorderColor(HSSFColor.BLACK.index);
    // 在样式用应用设置的字体;
    style.setFont(font);
    // 设置自动换行;
    style.setWrapText(false);
    // 设置水平对齐的样式为居中对齐;
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 设置垂直对齐的样式为居中对齐;
    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    return style;
}


public void exportData(){
    try {
        // 创建一个excel对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 产生表格标题行
        // 行
        HSSFRow rowm = sheet.createRow(0);
        // 单元格
        HSSFCell cellTiltle = rowm.createCell(0);
        // sheet样式定义
        // 头样式
        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);
        // 单元格样式
        HSSFCellStyle style = this.getStyle(workbook);
        /**
         * 参数说明
         * 从0开始   第一行 第一列 都是从角标0开始
         * 行 列 行列    (0,0,0,5)  合并第一行 第一列  到第一行 第六列
         * 起始行，起始列，结束行，结束列
         *
         * new Region()  这个方法使过时的
         */
        // 合并第一行的所有列
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (rowName.length - 1)));
        cellTiltle.setCellStyle(columnTopStyle);
        cellTiltle.setCellValue(title);
        // 表格列的长度
        int columnNum = rowName.length;
        // 在第二行创建行
        HSSFRow rowRowName = sheet.createRow(1);
        HSSFCellStyle cells = workbook.createCellStyle();
        cells.setBottomBorderColor(HSSFColor.BLACK.index);
        rowRowName.setRowStyle(cells);
        // 循环 将列名放进去
        for (int i = 0; i < columnNum; i++) {
            HSSFCell cellRowName = rowRowName.createCell((int) i);
            // 单元格类型
            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 得到列的值
            HSSFRichTextString text = new HSSFRichTextString(rowName[i]);
            // 设置第二行的列宽
            sheet.setColumnWidth(i, text.length() * 1000);
            // 设置列的值
            cellRowName.setCellValue(text);
            // 样式
            cellRowName.setCellStyle(columnTopStyle);
        }
        // 将查询到的数据设置到对应的单元格中
        for (int i = 0; i < dataList.size(); i++) {
            // 遍历每个对象
            Object[] obj = dataList.get(i);
            // 创建所需的行数
            HSSFRow row = sheet.createRow(i + 2);
            for (int j = 0; j < obj.length; j++) {
                // 设置单元格的数据类型
                HSSFCell cell = null;
                if (j == 0) {
                    // 第一列设置为序号
                    cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(i + 1);
                } else {
                    cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        // 设置单元格的值
                        cell.setCellValue(obj[j].toString());
                    } else {
                        cell.setCellValue("  ");
                    }
                }
                // 样式
                cell.setCellStyle(style);
            }
        }
        if (workbook != null) {
            try {
                // excel 表文件名
                String fileName = title + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
                String fileName11 = URLEncoder.encode(fileName, "UTF-8");
                String headStr = "attachment; filename=\"" + fileName11 + "\"";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", headStr);
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}