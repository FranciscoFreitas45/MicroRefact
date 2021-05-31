import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtils {

 private  String excel2003L;

 private  String excel2007U;


public String getValue(Cell cell){
    String value = "";
    if (null == cell) {
        return value;
    }
    switch(cell.getCellType()) {
        // 数值型
        case Cell.CELL_TYPE_NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                // 如果是date类型则 ，获取该cell的date值
                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                value = format.format(date);
            } else {
                // 纯数字
                BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                value = big.toString();
                // 解决1234.0  去掉后面的.0
                if (null != value && !"".equals(value.trim())) {
                    String[] item = value.split("[.]");
                    if (1 < item.length && "0".equals(item[1])) {
                        value = item[0];
                    }
                }
            }
            break;
        // 字符串类型
        case Cell.CELL_TYPE_STRING:
            value = cell.getStringCellValue().toString();
            break;
        // 公式类型
        case Cell.CELL_TYPE_FORMULA:
            // 读公式计算值
            value = String.valueOf(cell.getNumericCellValue());
            if (value.equals("NaN")) {
                // 如果获取的数据值为非法值,则转换为获取字符串
                value = cell.getStringCellValue().toString();
            }
            break;
        // 布尔类型
        case Cell.CELL_TYPE_BOOLEAN:
            value = " " + cell.getBooleanCellValue();
            break;
        default:
            value = cell.getStringCellValue().toString();
    }
    if ("null".endsWith(value.trim())) {
        value = "";
    }
    return value;
}


public Workbook getWorkbook(InputStream inStr,String fileName){
    Workbook wb = null;
    String fileType = fileName.substring(fileName.lastIndexOf("."));
    if (excel2003L.equals(fileType)) {
        // 2003-
        wb = new HSSFWorkbook(inStr);
    } else if (excel2007U.equals(fileType)) {
        // 2007+
        wb = new XSSFWorkbook(inStr);
    } else {
        throw new Exception("解析的文件格式有误！");
    }
    return wb;
}


public List<List<Object>> getBankListByExcel(InputStream in,String fileName){
    List<List<Object>> list = null;
    // 创建Excel工作薄
    Workbook work = this.getWorkbook(in, fileName);
    if (null == work) {
        throw new Exception("创建Excel工作薄为空！");
    }
    // 页数
    Sheet sheet = null;
    // 行数
    Row row = null;
    // 列数
    Cell cell = null;
    list = new ArrayList<List<Object>>();
    // 遍历Excel中所有的sheet
    for (int i = 0; i < work.getNumberOfSheets(); i++) {
        sheet = work.getSheetAt(i);
        if (sheet == null) {
            continue;
        }
        // 遍历当前sheet中的所有行
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            if (row == null || row.getFirstCellNum() == j) {
                continue;
            }
            // 遍历所有的列
            List<Object> li = new ArrayList<Object>();
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                li.add(this.getValue(cell));
            }
            list.add(li);
        }
    }
    return list;
}


}