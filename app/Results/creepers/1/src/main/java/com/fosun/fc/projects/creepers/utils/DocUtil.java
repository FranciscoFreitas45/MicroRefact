package com.fosun.fc.projects.creepers.utils;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.lowagie.text.DocumentException;
public class DocUtil {


public void readWordTableMedical(InputStream fileInputStream,List<Map<String,String>> listMap,int titleRowNum,String keyContent,String keyColumn){
    readWordTable(fileInputStream, listMap, titleRowNum, true, keyContent, keyColumn);
}


@SuppressWarnings("static-access")
public String getValue(HSSFCell hssfCell){
    if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
        return String.valueOf(hssfCell.getBooleanCellValue());
    } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        return String.valueOf(hssfCell.getNumericCellValue());
    } else {
        return String.valueOf(hssfCell.getStringCellValue());
    }
}


public void readExcelTable(InputStream fileInputStream,List<Map<String,String>> listMap,int titleRowNum,boolean setKeyToMap,String keyContent,String keyColumn,boolean singleSheet){
    try {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
        // 循环工作表Sheet
        int sheetNum = 1;
        if (!singleSheet) {
            sheetNum = hssfWorkbook.getNumberOfSheets();
        }
        String[] key = {};
        for (int numSheet = 0; numSheet < sheetNum; numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            if (titleRowNum > hssfSheet.getLastRowNum()) {
                titleRowNum = 0;
            }
            // 循环行Row
            for (int rowNum = titleRowNum; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                if (rowNum == titleRowNum) {
                    // 循环列Cell
                    key = new String[hssfRow.getLastCellNum()];
                    for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                        HSSFCell hssfCell = hssfRow.getCell(cellNum);
                        if (hssfCell == null) {
                            continue;
                        }
                        key[cellNum] = getValue(hssfCell);
                    }
                } else {
                    if (key.length < hssfRow.getLastCellNum()) {
                        titleRowNum++;
                        continue;
                    }
                    // 循环列Cell
                    Map<String, String> map = new HashMap<String, String>();
                    for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                        HSSFCell hssfCell = hssfRow.getCell(cellNum);
                        if (hssfCell == null) {
                            continue;
                        }
                        if (setKeyToMap && CommonMethodUtils.matchesChineseValue(keyContent).equals(CommonMethodUtils.matchesChineseValue(key[cellNum]))) {
                            map.put(keyColumn, getValue(hssfCell));
                        }
                        map.put(key[cellNum], getValue(hssfCell));
                    }
                    listMap.add(map);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public String excelToString(String filePathName){
    StringBuffer result = new StringBuffer();
    try {
        if (FileUtil.checkFilePath(filePathName)) {
            return result.toString();
        }
        InputStream is = new FileInputStream(filePathName);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                // 循环列Cell
                for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                    HSSFCell hssfCell = hssfRow.getCell(cellNum);
                    if (hssfCell == null) {
                        continue;
                    }
                    result.append(getValue(hssfCell));
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result.toString();
}


public void readExcelTableMedical(InputStream fileInputStream,List<Map<String,String>> listMap,int titleRowNum,String keyContent,String keyColumn,boolean singleSheet){
    readExcelTable(fileInputStream, listMap, titleRowNum, true, keyContent, keyColumn, singleSheet);
}


public void main(String[] args){
    try {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        readWordTable("C:\\Users\\Administrator\\Downloads\\ueO2q8qh0qnGt76t06rG89K1R1NQyMWpLmryr65q7jmo6i12jI2MbrFo6m4vbz+LmRvYw== (1).doc", resultList);
        for (Map<String, String> map : resultList) {
            Set<Entry<String, String>> set = map.entrySet();
            for (Entry<String, String> entry : set) {
                System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
            }
            System.out.println("==================================>>>>");
        }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


public String wordToString(String filePathName){
    String result = StringUtils.EMPTY;
    try {
        if (StringUtils.isNotBlank(filePathName) && filePathName.endsWith(BaseConstant.FileSuffix.DOC.getValue())) {
            InputStream is = new FileInputStream(new File(filePathName));
            WordExtractor ex = new WordExtractor(is);
            result = ex.getText();
        } else if (StringUtils.isNotBlank(filePathName) && filePathName.endsWith(BaseConstant.FileSuffix.DOCX.getValue())) {
            OPCPackage opcPackage = POIXMLDocument.openPackage(filePathName);
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            result = extractor.getText();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}


public void readWordTable(InputStream fileInputStream,List<Map<String,String>> listMap,int titleRowNum,boolean setKeyToMap,String keyContent,String keyColumn){
    try {
        HWPFDocument hwpf = new HWPFDocument(fileInputStream);
        // 得到文档的读取范围
        Range range = hwpf.getRange();
        TableIterator it = new TableIterator(range);
        // 迭代文档中的表格
        while (it.hasNext()) {
            Table tb = (Table) it.next();
            // 迭代行，默认从0开始
            String[] key = {};
            if (titleRowNum > tb.numRows()) {
                titleRowNum = 0;
            }
            for (int i = titleRowNum; i < tb.numRows(); i++) {
                TableRow tr = tb.getRow(i);
                // 迭代列，默认从0开始
                if (i == titleRowNum) {
                    key = new String[tr.numCells()];
                    for (int j = 0; j < tr.numCells(); j++) {
                        // 取得单元格
                        TableCell td = tr.getCell(j);
                        // 取得单元格的内容
                        StringBuffer value = new StringBuffer();
                        for (int k = 0; k < td.numParagraphs(); k++) {
                            Paragraph para = td.getParagraph(k);
                            String s = para.text().trim();
                            value.append(s);
                        }
                        key[j] = value.toString();
                    }
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    if (key.length < tr.numCells()) {
                        titleRowNum++;
                        continue;
                    } else {
                        for (int j = 0; j < tr.numCells(); j++) {
                            // 取得单元格
                            TableCell td = tr.getCell(j);
                            // 取得单元格的内容
                            StringBuffer value = new StringBuffer();
                            for (int k = 0; k < td.numParagraphs(); k++) {
                                Paragraph para = td.getParagraph(k);
                                String s = para.text().trim();
                                value.append(s);
                            }
                            if (setKeyToMap && CommonMethodUtils.matchesChineseValue(keyContent).equals(CommonMethodUtils.matchesChineseValue(key[j]))) {
                                map.put(keyColumn, value.toString());
                            }
                            map.put(key[j], value.toString());
                        }
                        listMap.add(map);
                    }
                }
            }
        }
    } catch (Exception e) {
        listMap = new ArrayList<Map<String, String>>();
        e.printStackTrace();
        System.err.println("DocUtil.readWordSingleTable error!!! \n" + e.getMessage());
    }
}


}