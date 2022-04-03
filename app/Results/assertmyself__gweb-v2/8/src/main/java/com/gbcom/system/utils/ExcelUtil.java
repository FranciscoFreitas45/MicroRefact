package com.gbcom.system.utils;
 import jxl.Workbook;
import jxl.write;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.util.StringHelper;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
public class ExcelUtil {

 private  String properties;

 private  String[] cols;


public boolean exportExcelwhensql(String fileName,String[] showtitle,List dataList,String[] indexes,String filePath){
    try {
        // 创建Excel工作薄
        OutputStream os;
        WritableWorkbook wwb;
        os = new FileOutputStream(filePath + fileName + ".xls");
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet = wwb.createSheet(fileName, 0);
        Label label;
        exportExcel(fileName, showtitle, dataList, indexes, "header", sheet);
        WritableCellFormat wcfcell = formatCell("");
        // 下面是填充数据
        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                Object[] ss = (Object[]) dataList.get(i);
                for (int j = 0; j < indexes.length; j++) {
                    for (int k = 0; k < ss.length; k++) {
                        if (Integer.parseInt(indexes[j]) == k) {
                            String value = "";
                            if (ss[k] != null) {
                                value = ss[k].toString();
                            }
                            if (!StringHelper.isEmpty(value)) {
                                if (value.equals("true")) {
                                    value = "是";
                                }
                                if (value.equals("false")) {
                                    value = "否";
                                }
                                label = new Label(j, i + 1, value, wcfcell);
                            } else {
                                label = new Label(j, i + 1, "  ", wcfcell);
                            }
                            sheet.addCell(label);
                            label = null;
                        }
                    }
                }
            }
        }
        wwb.write();
        // 关闭
        wwb.close();
        os.flush();
        os.close();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
    }
}


public void exportExcel(String fileName,String[] showtitle,List dataList,String[] indexes,String cell,WritableSheet sheet){
    // 第一行
    Label label;
    WritableCellFormat wcf = formatCell(cell);
    if (showtitle != null) {
        for (int i = 0; i < showtitle.length; i++) {
            if (showtitle[i].equals("操作")) {
                break;
            }
            label = new Label(i, 0, showtitle[i], wcf);
            sheet.setColumnView(i, 25);
            sheet.addCell(label);
        }
    }
}


public String exportExcelwhenhql(String fileName,String[] showtitle,List dataList,String[] indexes,String filePath){
    try {
        OutputStream os;
        WritableWorkbook wwb;
        os = new FileOutputStream(filePath + fileName + ".xls");
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet = wwb.createSheet(fileName, 0);
        Label label;
        exportExcel(fileName, showtitle, dataList, indexes, "header", sheet);
        WritableCellFormat wcfcell = formatCell("");
        // 下面是填充数据
        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                for (int j = 0; j < cols.length; j++) {
                    String value = "";
                    try {
                        value = PropertyUtils.getProperty(dataList.get(i), cols[j]).toString();
                    } catch (Exception e) {
                        value = "";
                    } finally {
                        if (!StringHelper.isEmpty(value)) {
                            if (value.equals("true")) {
                                value = "是";
                            }
                            if (value.equals("false")) {
                                value = "否";
                            }
                            label = new Label(j, i + 1, value, wcfcell);
                        } else {
                            label = new Label(j, i + 1, "  ", wcfcell);
                        }
                    }
                    sheet.addCell(label);
                    label = null;
                }
            }
        }
        wwb.write();
        // 关闭
        wwb.close();
        os.flush();
        os.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return filePath;
}


public String[] getCols(){
    return cols;
}


public void setProperties(String properties){
    cols = properties.split(",");
    this.properties = properties;
}


public WritableCellFormat formatCell(String cell){
    // 给cell添加样式
    if (cell.equals("header")) {
        WritableFont wf = new WritableFont(WritableFont.TAHOMA, 14, WritableFont.NO_BOLD, false);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        wcf.setBackground(jxl.format.Colour.GRAY_25);
        return wcf;
    } else {
        WritableFont wfcell = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD, false);
        WritableCellFormat wcfcell = new WritableCellFormat(wfcell);
        wcfcell.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        return wcfcell;
    }
}


public void setCols(String[] cols){
    this.cols = cols;
}


}