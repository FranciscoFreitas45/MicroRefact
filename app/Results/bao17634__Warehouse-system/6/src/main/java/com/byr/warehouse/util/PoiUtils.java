package com.byr.warehouse.util;
 import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.byr.warehouse.myexception.StoreException;
import com.byr.warehouse.pojo.StockHUB;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util;
public class PoiUtils {


public List<T> importExcel(MultipartFile file,Integer titleRows,Integer headerRows,Class pojoClass){
    if (file == null) {
        return null;
    }
    ImportParams params = new ImportParams();
    params.setTitleRows(titleRows);
    params.setHeadRows(headerRows);
    List<T> list = null;
    try {
        list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
    } catch (NoSuchElementException e) {
        throw new StoreException("excel文件不能为空");
    } catch (Exception e) {
        throw new StoreException(e.getMessage());
    }
    return list;
}


public void exportExcel(List<?> list,String title,String sheetName,Class<?> pojoClass,String fileName){
    defaultExport(list, pojoClass, fileName, new ExportParams(title, sheetName));
}


public void downLoadExcel(String fileName,HttpServletResponse response,Workbook workbook){
    try {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        workbook.write(response.getOutputStream());
    } catch (IOException e) {
        throw new StoreException(e.getMessage());
    }
}


public void main(String[] args){
    List<StockHUB> stockHUBS = new ArrayList<>();
    StockHUB stockHUB1 = new StockHUB("1", "1", "1", "1", "1", "1", "1");
    StockHUB stockHUB2 = new StockHUB("2", "2", "2", "2", "2", "2", "2");
    stockHUBS.add(stockHUB1);
    stockHUBS.add(stockHUB2);
}


public void defaultExport(List<Map<String,Object>> list,String fileName,HttpServletResponse response){
    Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
    if (workbook != null)
        ;
    downLoadExcel(fileName, response, workbook);
}


}