package com.qidian.hcm.common.utils;
 import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
@Slf4j
public class ExcelUtil {

 private  short FONT_SIZE;

private ExcelUtil() {
}
public HSSFWorkbook createWorkbook(String title,String[] rowName,List<Object[]> dataList){
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 创建工作表对象
    HSSFSheet sheet = workbook.createSheet();
    boolean withoutTitle = StringUtils.isEmpty(title);
    if (!withoutTitle) {
        // 产生表格标题行
        HSSFRow rowm = sheet.createRow(0);
        HSSFCell cellTiltle = rowm.createCell(0);
        cellTiltle.setCellValue(title);
        HSSFCellStyle columnHeaderStyle = getColumnHeaderStyle(workbook);
        cellTiltle.setCellStyle(columnHeaderStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
    }
    HSSFCellStyle style = getColumnStyle(workbook);
    style.setLocked(true);
    // 定义所需列数
    int columnNum = rowName.length;
    int fromIndex = withoutTitle ? 0 : 2;
    HSSFRow rowRowName = sheet.createRow(fromIndex);
    // 设置表头内容
    for (int n = 0; n < columnNum; n++) {
        HSSFCell cellRowName = rowRowName.createCell(n);
        HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
        // 设置列头单元格的值
        cellRowName.setCellValue(text);
        // 设置列头单元格样式
        cellRowName.setCellStyle(style);
    }
    if (!CollectionUtils.isEmpty(dataList)) {
        // 设置单元格内容
        for (int i = 0; i < dataList.size(); i++) {
            Object[] obj = dataList.get(i);
            // 创建所需的行数
            HSSFRow row = sheet.createRow(i + fromIndex + 1);
            Cell cell;
            for (int j = 0; j < obj.length; j++) {
                cell = row.createCell(j, CellType.STRING);
                if (StringUtils.isEmpty(obj[j])) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(obj[j].toString());
                }
                // 设置单元格样式
                cell.setCellStyle(style);
            }
        }
    }
    // 让列宽随着导出的列长自动适应
    for (int colNum = 0; colNum < columnNum; colNum++) {
        int columnWidth = sheet.getColumnWidth(colNum) / 256;
        for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
            HSSFRow currentRow;
            if (sheet.getRow(rowNum) == null) {
                currentRow = sheet.createRow(rowNum);
            } else {
                currentRow = sheet.getRow(rowNum);
            }
            if (currentRow.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                HSSFCell currentCell = currentRow.getCell(colNum);
                int length = currentCell.getStringCellValue().getBytes(StandardCharsets.UTF_8).length;
                if (columnWidth < length) {
                    columnWidth = length;
                }
            }
        }
        if (colNum == 0) {
            sheet.setColumnWidth(colNum, (columnWidth - 2) * 300);
        } else {
            sheet.setColumnWidth(colNum, (columnWidth + 4) * 300);
        }
    }
    return workbook;
}


public List<LinkedHashMap> parseExcelToJson(InputStream inputStream){
    List<LinkedHashMap> list = new ArrayList();
    try (POIFSFileSystem fs = new POIFSFileSystem(inputStream)) {
        Workbook wb = new HSSFWorkbook(fs);
        // 遍历每一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // 将第一行的列值作为正个json的key
        String[] cellNames;
        // 取第一行列的值作为key
        Row fisrtRow = sheet.getRow(0);
        // 如果第一行就为空，则是空sheet表，该表跳过
        if (null == fisrtRow) {
            return null;
        }
        // 得到第一行有多少列
        int curCellNum = fisrtRow.getLastCellNum();
        cellNames = new String[curCellNum];
        // 单独处理第一行，取出第一行的每个列值放在数组中，就得到了整张表的JSON的key
        for (int m = 0; m < curCellNum; m++) {
            Cell cell = fisrtRow.getCell(m, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            cell.setCellType(CellType.STRING);
            String columnTitle = cell.getStringCellValue();
            int i = 0;
            int j = 0;
            if (columnTitle.indexOf('(') != -1) {
                i = columnTitle.indexOf('(');
            }
            if (columnTitle.indexOf(')') != -1) {
                j = columnTitle.indexOf(')');
            }
            if (i > 0 && j > 0) {
                columnTitle = columnTitle.substring(i + 1, j);
            }
            cellNames[m] = columnTitle;
        }
        // 从第二行起遍历每一行
        int rowNum = sheet.getLastRowNum();
        for (int j = 1; j <= rowNum; j++) {
            LinkedHashMap<String, String> rowMap = new LinkedHashMap();
            Row row = sheet.getRow(j);
            if (row != null) {
                int cellNum = row.getLastCellNum();
                for (int k = 0; k < cellNum; k++) {
                    Cell cell = row.getCell(k, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cell == null || cell.getCellType() == CellType.BLANK) {
                        continue;
                    }
                    cell.setCellType(CellType.STRING);
                    rowMap.put(cellNames[k], cell.getStringCellValue());
                }
                list.add(rowMap);
            }
        }
    } catch (IOException e) {
        log.error(e.getMessage(), e);
    }
    return list;
}


public HSSFCellStyle getColumnStyle(HSSFWorkbook workbook){
    return getColumnHeaderStyle(workbook);
}


public HSSFCellStyle getColumnHeaderStyle(HSSFWorkbook workbook){
    // 设置字体
    HSSFFont font = workbook.createFont();
    // 设置字体大小
    font.setFontHeightInPoints(FONT_SIZE);
    // 设置样式
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置自动换行;
    style.setWrapText(true);
    // 设置水平对齐的样式为居中对齐;
    style.setAlignment(HorizontalAlignment.CENTER);
    // 设置垂直对齐的样式为居中对齐;
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    return style;
}


}