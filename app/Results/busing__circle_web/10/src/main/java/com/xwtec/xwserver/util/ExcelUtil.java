package com.xwtec.xwserver.util;
 import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.xwtec.xwserver.constant.ConstantBusiKeys.PoiExcelKey;
public class ExcelUtil {

 private  Logger logger;

 public  int SHEET_MAX_ROWS;


public int getRowNum(Workbook wb,int sheetIndex){
    // 获取工作表
    Sheet sheet = wb.getSheetAt(sheetIndex);
    return sheet.getLastRowNum();
}


public List<String[]> getAllData(Workbook wb,int sheetIndex){
    // 返回结果
    List<String[]> dataList = new ArrayList<String[]>();
    // 共多少列默认0
    int columnNum = 0;
    // 获取第几个工作表
    Sheet sheet = wb.getSheetAt(sheetIndex);
    // 如果第一行不为空就获取共有多少列
    if (sheet.getRow(0) != null) {
        columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
    }
    // 如果大于列大于0
    if (columnNum > 0) {
        // 遍历sheet
        for (Row row : sheet) {
            // 用来获取一行数据
            String[] singleRow = new String[columnNum];
            for (int i = 0; i < columnNum; i++) {
                Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
                // 判断类型
                switch(cell.getCellType()) {
                    case // 空格
                    Cell.CELL_TYPE_BLANK:
                        singleRow[i] = "";
                        break;
                    case // 布尔值
                    Cell.CELL_TYPE_BOOLEAN:
                        singleRow[i] = Boolean.toString(cell.getBooleanCellValue());
                        break;
                    case // 数字
                    Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            singleRow[i] = String.valueOf(cell.getDateCellValue());
                        } else {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String temp = cell.getStringCellValue();
                            // 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
                            if (temp.indexOf(".") > -1) {
                                singleRow[i] = String.valueOf(new Double(temp)).trim();
                            } else {
                                singleRow[i] = temp.trim();
                            }
                        }
                        break;
                    case // 字符串
                    Cell.CELL_TYPE_STRING:
                        singleRow[i] = cell.getStringCellValue().trim();
                        break;
                    case // 错误
                    Cell.CELL_TYPE_ERROR:
                        singleRow[i] = "";
                        break;
                    case // 公式
                    Cell.CELL_TYPE_FORMULA:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        singleRow[i] = cell.getStringCellValue();
                        if (singleRow[i] != null) {
                            singleRow[i] = singleRow[i].replaceAll("#N/A", "").trim();
                        }
                        break;
                    default:
                        singleRow[i] = "";
                        break;
                }
            }
            /**
             * 即使第一行为空，后面也可能不为空所以注释掉
             */
            // if("".equals(singleRow[0])){continue;}//如果第一行为空，跳过
            dataList.add(singleRow);
        }
    }
    return dataList;
}


public Workbook getWorkbook(String fileName){
    InputStream inp = new FileInputStream(fileName);
    Workbook wb = WorkbookFactory.create(inp);
    return wb;
}


public Workbook assembleExcel(List<Map<String,Object>> list,String excelName,String sheetName){
    logger.debug("[ExcelUtil.assembleExcel:msg] start...");
    /**
     * 工作表
     */
    Workbook workbook = new HSSFWorkbook();
    /**
     * 表单页
     */
    Sheet sheet = workbook.createSheet();
    /**
     * 设置sheet名称
     */
    workbook.setSheetName(0, sheetName);
    /**
     * 创建行索引
     */
    int rowIndex = 0;
    /**
     * 创建列索引
     */
    int cellIndex = 0;
    /**
     * 表单第一行（表头）
     */
    Row row = sheet.createRow(rowIndex);
    /**
     * 判断list是否为空
     */
    if (list != null && list.size() != 0) {
        /**
         * 循环list内第一个map 其 key为表头
         */
        for (Entry<String, ?> en : list.get(0).entrySet()) {
            /**
             * 创建表头行单元格
             */
            Cell cell = row.createCell(cellIndex);
            /**
             * 设置单元格内容
             */
            cell.setCellValue(en.getKey());
            /**
             * cellIndex所代表的当前单元格创建后cellIndex+1
             */
            cellIndex++;
        }
        /**
         * 循环list
         */
        for (int i = 0; i < list.size(); i++) {
            /**
             * rowIndex所代表的当前行已创建 所以rowIndex+1创建行
             */
            rowIndex++;
            /**
             * 如果数据过多，分多个sheet
             * add by:chenxiang@xwtec.cn
             */
            if (rowIndex >= SHEET_MAX_ROWS) {
                // 新建一个sheet
                sheet = workbook.createSheet();
                // 下一张sheet行索引置为0
                rowIndex = 0;
            }
            row = sheet.createRow(rowIndex);
            /**
             * 循环开始 cellIndex 置为 0
             */
            cellIndex = 0;
            /**
             * 循环List的map创建单元格
             */
            for (Entry<String, Object> en : list.get(i).entrySet()) {
                /**
                 * 根据索引创建单元植物园
                 */
                Cell cell = row.createCell(cellIndex);
                /**
                 * 设置单元格内容
                 */
                cell.setCellValue(CommonUtil.changeNullToEmptyString(en.getValue()));
                /**
                 * cellIndex所代表的当前单元格创建后cellIndex+1
                 */
                cellIndex++;
            }
        }
    } else {
        /**
         * 创建表头行单元格
         */
        Cell cell = row.createCell(cellIndex);
        /**
         * 设置单元格内容
         */
        cell.setCellValue(PoiExcelKey.POI_DEFAULT_VALUE);
    }
    logger.debug("[ExcelUtil.assembleExcel:msg] end...");
    return workbook;
}


public int getColumnNum(Workbook wb,int sheetIndex){
    // 获取工作表
    Sheet sheet = wb.getSheetAt(sheetIndex);
    // 根据第一行获取共有多少列
    Row row = sheet.getRow(0);
    if (row != null && row.getLastCellNum() > 0) {
        return row.getLastCellNum();
    }
    return 0;
}


}