package com.zis.common.excel;
 import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class ExcelImporter extends FileImporter<T>{

 private  Sheet sheet;

/**
 * @param inputStream
 *            �����ļ�
 * @param templateName
 *            ģ�����
 */
public ExcelImporter(InputStream inputStream, String templateName) {
    super(inputStream, templateName);
    try {
        Workbook book = new HSSFWorkbook(inputStream);
        sheet = book.getSheetAt(0);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
public Cell getCell(int rowNums,Integer index){
    Row row = this.sheet.getRow(rowNums);
    if (row == null) {
        return null;
    }
    return row.getCell(index);
}


@Override
public List<String> loadFactHeader(){
    int headRowIndex = headerRowNums - 1;
    int columns = sheet.getRow(headRowIndex).getLastCellNum();
    List<String> factList = new ArrayList<String>();
    for (int i = 0; i < columns; i++) {
        factList.add(getString(headerRowNums - 1, i));
    }
    return factList;
}


@Override
public Double getDouble(Integer rowNums,int index){
    Cell cell = getCell(rowNums, index);
    if (cell == null) {
        return null;
    }
    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
        if (StringUtils.isBlank(cell.getStringCellValue())) {
            return 0.0;
        } else {
            return Double.parseDouble(cell.getStringCellValue().trim());
        }
    }
    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        return cell.getNumericCellValue();
    }
    return cell == null ? null : cell.getNumericCellValue();
}


@Override
public Object getDate(Integer rowNums,Integer index){
    Cell cell = getCell(rowNums, index);
    if (cell == null) {
        return null;
    }
    return cell.getDateCellValue();
}


@Override
public Integer getInteger(Integer rowNums,int index){
    Cell cell = getCell(rowNums, index);
    if (cell == null) {
        return null;
    }
    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
        return Integer.parseInt(cell.getStringCellValue().trim());
    }
    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        return (int) cell.getNumericCellValue();
    }
    return (int) cell.getNumericCellValue();
}


@Override
public String getString(Integer rowNums,int index){
    Cell cell = getCell(rowNums, index);
    if (cell == null) {
        return "";
    }
    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
        return cell.getStringCellValue().trim();
    }
    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        return new DecimalFormat("###").format(cell.getNumericCellValue());
    }
    return "";
}


@Override
public Integer getRowNums(){
    return sheet.getLastRowNum();
}


public List<String> loadFileFormat(InputStream input){
    Workbook book = new HSSFWorkbook(input);
    Row headerRow = book.getSheetAt(0).getRow(headerRowNums - 1);
    List<String> list = new ArrayList<String>();
    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
        list.add(headerRow.getCell(i).getStringCellValue());
    }
    return list;
}


}