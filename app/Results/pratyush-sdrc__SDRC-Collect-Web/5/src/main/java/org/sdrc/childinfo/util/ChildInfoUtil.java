package org.sdrc.childinfo.util;
 import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.springframework.stereotype.Component;
@Component
public class ChildInfoUtil {


public XSSFCellStyle getStyleForLeftAlign(XSSFWorkbook workbook){
    XSSFCellStyle styleForLeftAlign = workbook.createCellStyle();
    styleForLeftAlign.setAlignment(HorizontalAlignment.LEFT);
    styleForLeftAlign.setBorderBottom(BorderStyle.THIN);
    styleForLeftAlign.setBorderTop(BorderStyle.THIN);
    styleForLeftAlign.setBorderLeft(BorderStyle.THIN);
    styleForLeftAlign.setBorderRight(BorderStyle.THIN);
    return styleForLeftAlign;
}


public XSSFCellStyle getStyleForLocking(XSSFWorkbook workbook){
    XSSFCellStyle styleForLocking = workbook.createCellStyle();
    styleForLocking.setBorderBottom(BorderStyle.THIN);
    styleForLocking.setBorderTop(BorderStyle.THIN);
    styleForLocking.setBorderLeft(BorderStyle.THIN);
    styleForLocking.setBorderRight(BorderStyle.THIN);
    styleForLocking.setLocked(true);
    styleForLocking.setAlignment(HorizontalAlignment.CENTER);
    styleForLocking.setVerticalAlignment(VerticalAlignment.CENTER);
    return styleForLocking;
}


public XSSFSheet sheetLock(XSSFSheet sheet){
    sheet.enableLocking();
    sheet.lockDeleteColumns(true);
    sheet.lockDeleteRows(true);
    sheet.lockFormatCells(true);
    sheet.lockFormatColumns(true);
    sheet.lockFormatRows(true);
    sheet.lockInsertColumns(true);
    sheet.lockInsertRows(true);
    CTSheetProtection sheetProtection = sheet.getCTWorksheet().getSheetProtection();
    sheetProtection.setSelectLockedCells(true);
    sheetProtection.setSelectUnlockedCells(false);
    sheetProtection.setFormatCells(true);
    sheetProtection.setFormatColumns(true);
    sheetProtection.setFormatRows(true);
    sheetProtection.setInsertColumns(true);
    sheetProtection.setInsertRows(true);
    sheetProtection.setInsertHyperlinks(true);
    sheetProtection.setDeleteColumns(true);
    sheetProtection.setDeleteRows(true);
    sheetProtection.setSort(false);
    sheetProtection.setAutoFilter(false);
    sheetProtection.setPivotTables(true);
    sheetProtection.setObjects(true);
    sheetProtection.setScenarios(true);
    return sheet;
}


public XSSFCellStyle getStyleForUnLocking(XSSFWorkbook workbook){
    // Define unlock style for the cells to be unlocked
    XSSFCellStyle styleForUnLocking = workbook.createCellStyle();
    styleForUnLocking.setBorderBottom(BorderStyle.THIN);
    styleForUnLocking.setLocked(false);
    styleForUnLocking.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
    return styleForUnLocking;
}


public Font getHeaderFontProject(SXSSFWorkbook workbook){
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 17);
    headerFont.setColor(IndexedColors.BLACK.getIndex());
    return headerFont;
}


public CellStyle getStyleForCenter(SXSSFWorkbook workbook){
    CellStyle styleForCenter = workbook.createCellStyle();
    styleForCenter.setAlignment(HorizontalAlignment.CENTER);
    styleForCenter.setBorderBottom(BorderStyle.THIN);
    styleForCenter.setBorderTop(BorderStyle.THIN);
    styleForCenter.setBorderLeft(BorderStyle.THIN);
    styleForCenter.setBorderRight(BorderStyle.THIN);
    styleForCenter.setWrapText(true);
    return styleForCenter;
}


public CellStyle getStyleForOtherHeader(SXSSFWorkbook workbook){
    CellStyle styleForCenter = workbook.createCellStyle();
    styleForCenter.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
    styleForCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleForCenter.setVerticalAlignment(VerticalAlignment.CENTER);
    styleForCenter.setAlignment(HorizontalAlignment.CENTER);
    styleForCenter.setBorderBottom(BorderStyle.THIN);
    styleForCenter.setBorderTop(BorderStyle.THIN);
    styleForCenter.setBorderLeft(BorderStyle.THIN);
    styleForCenter.setBorderRight(BorderStyle.THIN);
    styleForCenter.setFont(getHeaderFontOtherHeader(workbook));
    return styleForCenter;
}


public Font getHeaderFontOtherHeader(SXSSFWorkbook workbook){
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 12);
    headerFont.setColor(IndexedColors.BLACK.getIndex());
    return headerFont;
}


public Font getHeaderFont(SXSSFWorkbook workbook){
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 12);
    headerFont.setColor(IndexedColors.WHITE.getIndex());
    return headerFont;
}


public CellStyle getStyleForProjectName(SXSSFWorkbook workbook){
    CellStyle styleForCenter = workbook.createCellStyle();
    styleForCenter.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
    styleForCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleForCenter.setVerticalAlignment(VerticalAlignment.CENTER);
    styleForCenter.setAlignment(HorizontalAlignment.CENTER);
    styleForCenter.setBorderBottom(BorderStyle.THIN);
    styleForCenter.setBorderTop(BorderStyle.THIN);
    styleForCenter.setBorderLeft(BorderStyle.THIN);
    styleForCenter.setBorderRight(BorderStyle.THIN);
    styleForCenter.setFont(getHeaderFontProject(workbook));
    return styleForCenter;
}


public CellStyle getStyleForOtherDateHeader(SXSSFWorkbook workbook){
    CellStyle styleForCenter = workbook.createCellStyle();
    styleForCenter.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
    styleForCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleForCenter.setVerticalAlignment(VerticalAlignment.CENTER);
    styleForCenter.setAlignment(HorizontalAlignment.CENTER);
    styleForCenter.setBorderBottom(BorderStyle.THIN);
    styleForCenter.setBorderTop(BorderStyle.THIN);
    styleForCenter.setBorderLeft(BorderStyle.THIN);
    styleForCenter.setBorderRight(BorderStyle.THIN);
    styleForCenter.setFont(getHeaderFontOtherHeader(workbook));
    CreationHelper createHelper = workbook.getCreationHelper();
    styleForCenter.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
    return styleForCenter;
}


public CellStyle getHeaderCellStyle(SXSSFWorkbook workbook){
    Font headerFont = getHeaderFont(workbook);
    CellStyle headerCellStyle = workbook.createCellStyle();
    headerCellStyle.setFont(headerFont);
    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    headerCellStyle.setFillBackgroundColor(IndexedColors.BROWN.getIndex());
    headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
    return headerCellStyle;
}


}