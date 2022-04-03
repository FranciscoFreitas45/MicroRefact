package org.jeecgframework.web.cgform.service.excel;
 import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jeecgframework.core.common.exception.BusinessException;
public class ExcelTempletService {

 private  int maxColumnWidth;

 private  int minColumnWidth;

 private  int defaultBlankRow;

 public  DateFormat DEFAULT_DATE_FORMAT;


public HSSFCellStyle getTwoStyle(HSSFWorkbook workbook){
    // 产生Excel表头
    HSSFCellStyle style = workbook.createCellStyle();
    // 左边框
    style.setBorderLeft((short) 1);
    // 右边框
    style.setBorderRight((short) 1);
    style.setBorderBottom((short) 1);
    style.setBorderTop((short) 1);
    // 填充的背景颜色
    style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
    // 填充图案
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    return style;
}


public HSSFWorkbook exportExcel(String title,Collection<?> dataSet,List<Map<String,Object>> datalist){
    // 使用userModel模式实现的，当excel文档出现10万级别的大数据文件可能导致OOM内存溢出
    return exportExcelInUserModel2File(title, dataSet, datalist);
}


public void setBlankRows(int rows,int columns,HSSFWorkbook workbook){
    // 得到第一页
    Sheet sheet = workbook.getSheetAt(0);
    // 样式
    CellStyle cellStyle = getOneStyle(workbook);
    for (int i = 1; i <= rows; i++) {
        Row row = sheet.createRow(i);
        for (int j = 0; j < columns; j++) {
            row.createCell(j).setCellStyle(cellStyle);
        }
    }
}


public Map<String,CgFormFieldEntity> ConvertDate(List<CgFormFieldEntity> lists){
    Map<String, CgFormFieldEntity> maps = new HashMap<String, CgFormFieldEntity>();
    for (CgFormFieldEntity cgFormFieldEntity : lists) {
        maps.put(cgFormFieldEntity.getContent(), cgFormFieldEntity);
    }
    return maps;
}


public HSSFCellStyle getOneStyle(HSSFWorkbook workbook){
    // 产生Excel表头
    HSSFCellStyle style = workbook.createCellStyle();
    // 左边框
    style.setBorderLeft((short) 1);
    // 右边框
    style.setBorderRight((short) 1);
    style.setBorderBottom((short) 1);
    style.setBorderTop((short) 1);
    return style;
}


@SuppressWarnings("unchecked")
public HSSFWorkbook exportExcelInUserModel2File(String title,Collection<?> dataSet,List<Map<String,Object>> datalist){
    // 声明一个工作薄
    HSSFWorkbook workbook = null;
    try {
        // 首先检查数据看是否是正确的
        if (dataSet == null || dataSet.size() == 0) {
            throw new Exception("导出数据为空！");
        }
        if (title == null) {
            throw new Exception("传入参数不能为空！");
        }
        // 声明一个工作薄
        workbook = new HSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        int index = 0;
        // 产生表格标题行
        Row row = sheet.createRow(index);
        row.setHeight((short) 450);
        CellStyle titleStyle = getTitleStyle(workbook);
        Iterator it = dataSet.iterator();
        while (it.hasNext()) {
            CgFormFieldEntity type = (CgFormFieldEntity) it.next();
            // 输入需要显示的字段信息
            if (type.getIsShow().equals("Y")) {
                Cell cell = row.createCell(index);
                RichTextString text = new HSSFRichTextString(type.getContent());
                cell.setCellValue(text);
                // 设置列宽
                int columnWidth = type.getLength() == 0 ? minColumnWidth : type.getLength() > maxColumnWidth ? maxColumnWidth : type.getLength();
                sheet.setColumnWidth(index, 256 * columnWidth);
                cell.setCellStyle(titleStyle);
                index++;
            // 标题列下方默认给三行的边框空位置
            }
            setBlankRows(defaultBlankRow, index, workbook);
        }
        for (int i = 0; i < datalist.size(); i++) {
            it = dataSet.iterator();
            row = sheet.createRow(i + 1);
            index = 0;
            while (it.hasNext()) {
                CgFormFieldEntity type = (CgFormFieldEntity) it.next();
                // 输入需要显示的字段信息
                if (type.getIsShow().equals("Y")) {
                    Cell cell = row.createCell(index);
                    if (datalist.get(i).get(type.getFieldName()) != null) {
                        RichTextString text = new HSSFRichTextString(datalist.get(i).get(type.getFieldName()).toString());
                        cell.setCellValue(text);
                    }
                    // 标题列下方默认给三行的边框空位置
                    index++;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return workbook;
}


public HSSFCellStyle getTitleStyle(HSSFWorkbook workbook){
    // 产生Excel表头
    HSSFCellStyle titleStyle = workbook.createCellStyle();
    // 设置边框样式
    titleStyle.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
    // 左边框
    titleStyle.setBorderLeft((short) 2);
    // 右边框
    titleStyle.setBorderRight((short) 2);
    // 左边框
    titleStyle.setBorderTop((short) 2);
    // 右边框
    titleStyle.setBorderBottom((short) 2);
    // 顶边框
    titleStyle.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
    // 填充的背景颜色
    titleStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 填充图案
    titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    return titleStyle;
}


public Collection importExcelByIs(InputStream inputstream,List<CgFormFieldEntity> lists){
    Map<String, CgFormFieldEntity> fieldMap = ConvertDate(lists);
    // 返回的数据类型
    List<Map<String, Object>> tObject = new ArrayList<Map<String, Object>>();
    try {
        // 将传入的File构造为FileInputStream;
        // // 得到工作表
        HSSFWorkbook book = new HSSFWorkbook(inputstream);
        // // 得到第一页
        HSSFSheet sheet = book.getSheetAt(0);
        // // 得到第一面的所有行
        Iterator<Row> row = sheet.rowIterator();
        // 得到第一行，也就是标题行
        Row title = row.next();
        // 得到第一行的所有列
        Iterator<Cell> cellTitle = title.cellIterator();
        // 将标题的文字内容放入到一个map中。
        Map titlemap = new HashMap();
        // 从标题第一列开始
        int i = 0;
        // 循环标题所有的列
        while (cellTitle.hasNext()) {
            Cell cell = cellTitle.next();
            String value = cell.getStringCellValue();
            if (fieldMap.get(value) == null) {
                throw new BusinessException("导入数据excel列名有不能识别的列");
            }
            titlemap.put(i, value);
            i = i + 1;
        }
        // 用来格式化日期的DateFormat
        Map<String, Object> retMap = null;
        while (row.hasNext()) {
            retMap = new HashMap<String, Object>();
            // 标题下的第一行
            Row rown = row.next();
            // 行的所有列
            Iterator<Cell> cellbody = rown.cellIterator();
            int k = 0;
            // 遍历一行的列
            while (cellbody.hasNext()) {
                Cell cell = cellbody.next();
                // 这里得到此列的对应的标题
                String titleString = (String) titlemap.get(k);
                if (fieldMap.containsKey(titleString)) {
                    retMap.put(fieldMap.get(titleString).getFieldName(), getCellValueString(cell));
                }
                // 下一列
                k = k + 1;
            }
            tObject.add(retMap);
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return tObject;
}


public String getDateValue(Cell cell){
    return DEFAULT_DATE_FORMAT.format(cell.getDateCellValue());
}


public String getCellValueString(Cell cell){
    if (cell == null) {
        return null;
    }
    // 时间对象 特殊处理
    int dataFormat = cell.getCellStyle().getDataFormat();
    if (dataFormat == 14 || dataFormat == 178 || dataFormat == 180 || dataFormat == 181 || dataFormat == 182) {
        return getDateValue(cell);
    }
    String value = null;
    switch(cell.getCellType()) {
        case Cell.CELL_TYPE_NUMERIC:
            value = new DecimalFormat("0.##########").format(cell.getNumericCellValue());
            break;
        case Cell.CELL_TYPE_STRING:
            // value = cell.getStringCellValue();
            value = cell.getRichStringCellValue().toString();
            break;
        case Cell.CELL_TYPE_FORMULA:
            value = String.valueOf(cell.getCellFormula());
            break;
        case Cell.CELL_TYPE_BLANK:
            // value = String.valueOf(cell.getStringCellValue());
            value = String.valueOf(cell.getRichStringCellValue().toString());
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            value = String.valueOf(cell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_ERROR:
            value = String.valueOf(cell.getErrorCellValue());
            break;
    }
    return value;
}


}