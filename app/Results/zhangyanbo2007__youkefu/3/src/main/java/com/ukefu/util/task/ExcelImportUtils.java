package com.ukefu.util.task;
 import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.callout.CallOutUtils;
import com.ukefu.util.crm.CrmUtils;
import com.ukefu.util.es.ESTools;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.TableProperties;
import Interface.DSDataEvent;
public class ExcelImportUtils {

 private  DecimalFormat format;

 protected  DSDataEvent event;

public ExcelImportUtils(DSDataEvent event) {
    this.event = event;
}
public DecimalFormat getNumberFormat(String dataformat){
    DecimalFormat numberFormat = null;
    int index = dataformat.indexOf("_") > 0 ? dataformat.indexOf("_") : dataformat.indexOf(";");
    if (index > 0) {
        String format = dataformat.substring(0, index);
        if (format.matches("[\\d.]{1,}")) {
            numberFormat = new DecimalFormat(format);
        }
    }
    return numberFormat;
}


@SuppressWarnings("deprecation")
public String getValue(Cell cell){
    String strCell = "";
    if (cell != null) {
        short dt = cell.getCellStyle().getDataFormat();
        switch(cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    } else {
                        // 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                    strCell = sdf.format(cell.getDateCellValue());
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    strCell = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value));
                } else {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        } else {
                            // 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        }
                        strCell = sdf.format(cell.getDateCellValue());
                    } else {
                        boolean isNumber = isNumberFormat(dt);
                        if (isNumber) {
                            DecimalFormat numberFormat = getNumberFormat(cell.getCellStyle().getDataFormatString());
                            if (numberFormat != null) {
                                strCell = String.valueOf(numberFormat.format(cell.getNumericCellValue()));
                            } else {
                                strCell = String.valueOf(cell.getNumericCellValue());
                            }
                        } else {
                            strCell = String.valueOf(format.format(cell.getNumericCellValue()));
                        }
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                {
                    // 判断当前的cell是否为Date
                    boolean isNumber = isNumberFormat(dt);
                    try {
                        if (isNumber) {
                            strCell = String.valueOf(cell.getNumericCellValue());
                        } else {
                            strCell = "";
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        strCell = cell.getRichStringCellValue().getString();
                    }
                    break;
                }
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
    }
    return strCell;
}


public boolean isExcel2007(String fileName){
    return fileName.matches("^.+\\.(?i)(xlsx)$");
}


@SuppressWarnings({ "deprecation", "unused" })
public String getDataType(Cell cell){
    String dataType = "string";
    if (cell != null) {
        short dt = cell.getCellStyle().getDataFormat();
        switch(cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                dataType = "string";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                dataType = "number";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        dataType = "datetime";
                    } else {
                        // 日期
                        dataType = "datetime";
                    }
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    dataType = "datetime";
                } else {
                    boolean isNumber = isNumberFormat(dt);
                    if (isNumber) {
                        dataType = "number";
                    } else {
                        dataType = "string";
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        dataType = "datetime";
                    } else {
                        // 日期
                        dataType = "datetime";
                    }
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    dataType = "datetime";
                } else {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                            dataType = "datetime";
                        } else {
                            // 日期
                            dataType = "datetime";
                        }
                    } else {
                        boolean isNumber = isNumberFormat(dt);
                        if (isNumber) {
                            dataType = "number";
                        } else {
                            dataType = "string";
                        }
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                {
                    // 判断当前的cell是否为Date
                    boolean isNumber = isNumberFormat(dt);
                    if (isNumber) {
                        dataType = "number";
                    } else {
                        dataType = "string";
                    }
                    break;
                }
            default:
                dataType = "string";
                break;
        }
    }
    return dataType;
}


public boolean isNumberFormat(short dataType){
    boolean number = false;
    switch(dataType) {
        case 180:
            number = true;
            break;
        case 181:
            number = true;
            break;
        case 182:
            number = true;
            break;
        case 178:
            number = true;
            break;
        case 177:
            number = true;
            break;
        case 176:
            number = true;
            break;
        case 183:
            number = true;
            break;
        case 185:
            number = true;
            break;
        case 186:
            number = true;
            break;
        case 179:
            number = true;
            break;
        case 187:
            number = true;
            break;
        case 7:
            number = true;
            break;
        case 8:
            number = true;
            break;
        case 44:
            number = true;
            break;
        case 10:
            number = true;
            break;
        case 12:
            number = true;
            break;
        case 13:
            number = true;
            break;
        case 188:
            number = true;
            break;
        case 189:
            number = true;
            break;
        case 190:
            number = true;
            break;
        case 191:
            number = true;
            break;
        case 192:
            number = true;
            break;
        case 193:
            number = true;
            break;
        case 194:
            number = true;
            break;
        case 11:
            number = true;
            break;
    }
    return number;
}


public MetadataTable processExcel(DSDataEvent event,String tableTitle,String tabletype){
    MetadataTable metaDataTable = new MetadataTable();
    InputStream is = null;
    boolean findId = false;
    try {
        metaDataTable.setTablename(event.getTablename());
        metaDataTable.setOrgi(this.event.getOrgi());
        metaDataTable.setId(UKTools.md5(event.getTablename()));
        metaDataTable.setTabletype(tabletype);
        metaDataTable.setTabledirid("0");
        metaDataTable.setCreater(event.getDSData().getUser().getId());
        metaDataTable.setCreatername(event.getDSData().getUser().getUsername());
        metaDataTable.setName(tableTitle);
        metaDataTable.setUpdatetime(new Date());
        metaDataTable.setCreatetime(new Date());
        metaDataTable.setTableproperty(new ArrayList<TableProperties>());
        try {
            is = new FileInputStream(event.getDSData().getFile());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        boolean isExcel2003 = true;
        if (isExcel2007(event.getDSData().getFile().getName())) {
            isExcel2003 = false;
        }
        Workbook wb = null;
        try {
            wb = isExcel2003 ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        Row titleRow = sheet.getRow(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int colNum = titleRow.getPhysicalNumberOfCells();
        /**
         * 需要检查Mapping 是否存在
         */
        if (totalRows > 1) {
            Row row = sheet.getRow(0);
            if (row != null) {
                for (int col = 0; col < colNum; col++) {
                    Cell title = titleRow.getCell(col);
                    String titleValue = getValue(title);
                    if (!StringUtils.isBlank(titleValue)) {
                        /**
                         * 剔除标题字段中发非字母和数字字符
                         */
                        titleValue = titleValue.trim();
                        if (titleValue.equalsIgnoreCase("id")) {
                            findId = true;
                        }
                        TableProperties tp;
                        if ("4".equals(tabletype)) {
                            // CRM初始元数据字段
                            tp = CrmUtils.initProperties("f" + UKTools.genIDByKey(event.getTablename() + titleValue + "String"), titleValue, "String", event.getOrgi(), event.getTablename(), false);
                        } else {
                            tp = CallOutUtils.initProperties("f" + UKTools.genIDByKey(event.getTablename() + titleValue + "String"), titleValue, "String", event.getOrgi(), event.getTablename(), false);
                        }
                        tp.setViewtype("list,add,edit,detail");
                        metaDataTable.getTableproperty().add(tp);
                    }
                }
            }
        }
        if ("4".equals(tabletype)) {
            // CRM初始元数据字段
            CrmUtils.processMetadataTable(findId, metaDataTable, event);
        } else {
            CallOutUtils.processMetadataTable(findId, metaDataTable, event);
        }
        /**
         * 映射 Mapping , 已修正，增加了一个手动映射的步骤，上传数据结构以后，允许手动映射
         */
        ESTools.mapping(metaDataTable, UKDataContext.CALLOUT_INDEX);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getDSData().getFile() != null && event.getDSData().getFile().exists()) {
            event.getDSData().getFile().delete();
        }
    }
    return metaDataTable;
}


}