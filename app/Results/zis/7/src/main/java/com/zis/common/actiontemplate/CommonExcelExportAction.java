package com.zis.common.actiontemplate;
 import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.common.util.ZisUtils;
public class CommonExcelExportAction extends ActionSupportimplements ServletResponseAware{

 private  long serialVersionUID;

 private  Integer colnum;

 private  HttpServletResponse response;


public boolean isSkip(T record){
    return false;
}


public String[] getTableHeaders()


public String[] getRowDatas(T record)


public void setResponseHeader(){
    try {
        // 两种方法都可以
        response.setContentType("application/msexcel;charset=UTF-8");
        // response.setContentType("application/octet-stream;charset=iso-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(setExportFileName(), "UTF-8"));
        // 客户端不缓存
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public void createNewRow(Row row,String[] rowData){
    for (int i = 0; i < colnum; i++) {
        String data = rowData[i] == null ? "" : rowData[i];
        row.createCell(i).setCellValue(data);
    }
}


public List<T> queryExportData()


public void setServletResponse(HttpServletResponse arg0){
    this.response = arg0;
}


public Collection<T> TransformResultList(List<T> list){
    return list;
}


public String export(){
    // 获取需要导出的数据
    List<T> list = queryExportData();
    Collection<T> listAfterTransform = TransformResultList(list);
    // 创建工作表
    Workbook book = new HSSFWorkbook();
    Sheet sheet = book.createSheet();
    // 创建表头
    Row row = sheet.createRow(0);
    String[] tableHeaders = getTableHeaders();
    colnum = tableHeaders.length;
    for (int i = 0; i < colnum; i++) {
        row.createCell(i).setCellValue(tableHeaders[i]);
    }
    // 填充数据
    int i = 1;
    for (T record : listAfterTransform) {
        // 过滤需要跳过的记录
        if (isSkip(record)) {
            continue;
        }
        createNewRow(sheet.createRow(i), getRowDatas(record));
        i++;
    }
    // 导出
    setResponseHeader();
    try {
        book.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        return SUCCESS;
    } catch (IOException e) {
        throw new RuntimeException("导出数据失败", e);
    }
}


public String setExportFileName(){
    return "导出数据-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}