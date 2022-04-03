package com.gs.common.util;
 import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ExcelReader {

 public  String OFFICE_EXCEL_2003_POSTFIX;

 public  String OFFICE_EXCEL_2010_POSTFIX;

 public  String EMPTY;

 public  String POINT;

 public  SimpleDateFormat sdf;


public String getPostfix(String path){
    if (path == null || EMPTY.equals(path.trim())) {
        return EMPTY;
    }
    if (path.contains(POINT)) {
        return path.substring(path.lastIndexOf(POINT) + 1, path.length());
    }
    return EMPTY;
}


@SuppressWarnings({ "static-access", "deprecation" })
public String getHValue(HSSFCell hssfCell){
    if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
        return String.valueOf(hssfCell.getBooleanCellValue());
    } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        String cellValue = "";
        if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
            Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
            cellValue = sdf.format(date);
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            cellValue = df.format(hssfCell.getNumericCellValue());
            String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
            if (strArr.equals("00")) {
                cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
            }
        }
        return cellValue;
    } else {
        return String.valueOf(hssfCell.getStringCellValue());
    }
}


public String getXValue(XSSFCell xssfCell){
    if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
        return String.valueOf(xssfCell.getBooleanCellValue());
    } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        String cellValue = "";
        if (XSSFDateUtil.isCellDateFormatted(xssfCell)) {
            Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());
            cellValue = sdf.format(date);
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            cellValue = df.format(xssfCell.getNumericCellValue());
            String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
            if (strArr.equals("00")) {
                cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
            }
        }
        return cellValue;
    } else {
        return String.valueOf(xssfCell.getStringCellValue());
    }
}


}