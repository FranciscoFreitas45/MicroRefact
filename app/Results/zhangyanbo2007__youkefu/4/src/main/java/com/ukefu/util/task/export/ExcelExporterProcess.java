package com.ukefu.util.task.export;
 import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UCKeFuTime;
import com.ukefu.util.UKTools;
import com.ukefu.util.extra.DataExchangeInterface;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.TableProperties;
import com.ukefu.webim.web.model.UKeFuDic;
import Interface.MetadataTable;
import DTO.SysDic;
@SuppressWarnings("deprecation")
public class ExcelExporterProcess {

 private  HSSFWorkbook wb;

 private  Sheet sheet;

 private  CellStyle firstStyle;

 private  int rowNum;

 private  List<Map<String,Object>> values;

 private  MetadataTable table;

 private  OutputStream output;

 private  Row titleRow;

public ExcelExporterProcess(List<Map<String, Object>> values, MetadataTable table, OutputStream output) {
    this.values = values;
    this.table = table;
    this.output = output;
    wb = new HSSFWorkbook();
    sheet = wb.createSheet();
    firstStyle = createFirstCellStyle();
    createHead();
}
public void process(){
    createContent();
    if (table != null) {
        for (TableProperties tp : table.getTableproperty()) {
            sheet.autoSizeColumn(table.getTableproperty().indexOf(tp));
        }
        wb.write(this.output);
    }
}


public CellStyle createContentStyle(){
    CellStyle cellStyle = wb.createCellStyle();
    // 指定单元格居中对齐
    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 指定单元格垂直居中对齐
    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    // 指定单元格自动换行
    cellStyle.setWrapText(false);
    // 设置单元格字体
    Font font = wb.createFont();
    // font.setFontName("微软雅黑");
    font.setFontHeight((short) 200);
    cellStyle.setFont(font);
    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    return cellStyle;
}


public void createContent(){
    CellStyle cellStyle = createContentStyle();
    if (table != null && table.getTableproperty() != null) {
        for (Map<String, Object> value : values) {
            Row row2 = sheet.createRow(rowNum);
            List<ExportData> tempExportDatas = new ArrayList<ExportData>();
            int cols = 0;
            for (TableProperties tp : table.getTableproperty()) {
                Cell cell2 = row2.createCell(cols++);
                cell2.setCellStyle(cellStyle);
                if (value.get(tp.getFieldname()) != null) {
                    if (tp.isModits()) {
                        @SuppressWarnings("unchecked")
                        List<String> list = (List<String>) value.get(tp.getFieldname());
                        if (list.size() > 0) {
                            cell2.setCellValue(new HSSFRichTextString(list.remove(0)));
                        }
                        ExportData expData = new ExportData(tp, list);
                        if (list.size() > 0) {
                            tempExportDatas.add(expData);
                            if (list.size() > expData.getMaxcols()) {
                                expData.setMaxcols(list.size());
                            }
                        }
                    } else if (tp.isSeldata()) {
                        SysDic sysDic = UKeFuDic.getInstance().getDicItem(String.valueOf(value.get(tp.getFieldname())));
                        if (sysDic != null) {
                            cell2.setCellValue(new HSSFRichTextString(sysDic.getName()));
                        } else {
                            List<SysDic> dicItemList = UKeFuDic.getInstance().getSysDic(tp.getSeldatacode());
                            if (dicItemList != null && dicItemList.size() > 0) {
                                for (SysDic dicItem : dicItemList) {
                                    String s = "";
                                    Object obj = value.get(tp.getFieldname());
                                    if (obj instanceof Boolean) {
                                        s = (Boolean) obj ? "1" : "0";
                                    } else {
                                        s = String.valueOf(value.get(tp.getFieldname()));
                                    }
                                    if (dicItem.getCode().equals(s)) {
                                        cell2.setCellValue(new HSSFRichTextString(dicItem.getName()));
                                        break;
                                    }
                                }
                            }
                        }
                    } else if (tp.isReffk() && !StringUtils.isBlank(tp.getReftbid())) {
                        String key = (String) value.get(tp.getFieldname());
                        String orgi = (String) value.get("orgi");
                        if (!StringUtils.isBlank(key) && !StringUtils.isBlank(orgi)) {
                            DataExchangeInterface exchange = (DataExchangeInterface) UKDataContext.getContext().getBean(tp.getReftbid());
                            Object refvalue = exchange.getDataByIdAndOrgi(key, orgi);
                            if (refvalue != null) {
                                cell2.setCellValue(new HSSFRichTextString(refvalue.toString()));
                            }
                        }
                    } else {
                        boolean writed = false;
                        if (!StringUtils.isBlank(String.valueOf(value.get("distype")))) {
                            if (value.get("disphonenum") != null && value.get("disphonenum").equals(value.get(tp.getFieldname()))) {
                                cell2.setCellValue(new HSSFRichTextString(UKTools.processSecField(String.valueOf(value.get(tp.getFieldname())), String.valueOf(value.get("distype")))));
                                writed = true;
                            }
                        }
                        if (writed == false) {
                            if (!StringUtils.isBlank(tp.getPlugin())) {
                                if (tp.getPlugin().equals("sectime") && String.valueOf(value.get(tp.getFieldname())).matches("[\\d]{1,}")) {
                                    int sectime = (int) Long.parseLong(String.valueOf(value.get(tp.getFieldname())));
                                    cell2.setCellValue(new HSSFRichTextString(new UCKeFuTime(0, 0, sectime).toString()));
                                } else if (tp.getPlugin().equals("mintime") && String.valueOf(value.get(tp.getFieldname())).matches("[\\d]{1,}")) {
                                    int mintime = (int) Long.parseLong(String.valueOf(value.get(tp.getFieldname()))) / 1000;
                                    cell2.setCellValue(new HSSFRichTextString(new UCKeFuTime(0, 0, (int) mintime).toString()));
                                }
                            } else {
                                cell2.setCellValue(new HSSFRichTextString(String.valueOf(value.get(tp.getFieldname()))));
                            }
                        }
                    }
                }
            }
            if (tempExportDatas.size() > 0) {
                for (ExportData expData : tempExportDatas) {
                    for (int i = 0; i < expData.getMaxcols(); i++) {
                        if (titleRow.getCell(cols + i) == null) {
                            Cell title = titleRow.createCell(cols + i);
                            title.setCellStyle(firstStyle);
                            title.setCellValue(new HSSFRichTextString(expData.getTp().getName()));
                        }
                    }
                    for (String itemValue : expData.getValues()) {
                        Cell cell2 = row2.createCell(cols++);
                        cell2.setCellValue(new HSSFRichTextString(itemValue));
                    }
                }
            }
            rowNum++;
        }
    }
}


public void createHead(){
    titleRow = sheet.createRow(rowNum);
    if (table != null && table.getTableproperty() != null) {
        for (TableProperties tp : table.getTableproperty()) {
            Cell cell2 = titleRow.createCell(table.getTableproperty().indexOf(tp));
            cell2.setCellStyle(firstStyle);
            cell2.setCellValue(new HSSFRichTextString(tp.getName()));
        }
    }
    rowNum++;
}


public CellStyle createFirstCellStyle(){
    CellStyle cellStyle = baseCellStyle();
    Font font = wb.createFont();
    // font.setFontName("微软雅黑");
    font.setFontHeight((short) 180);
    cellStyle.setFont(font);
    cellStyle.setWrapText(false);
    cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    return cellStyle;
}


public CellStyle baseCellStyle(){
    CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    cellStyle.setWrapText(true);
    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    Font font = wb.createFont();
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // font.setFontName("宋体");
    font.setFontHeight((short) 200);
    cellStyle.setFont(font);
    return cellStyle;
}


}