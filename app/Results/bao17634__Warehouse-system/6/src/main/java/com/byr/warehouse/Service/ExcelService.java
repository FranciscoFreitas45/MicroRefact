package com.byr.warehouse.Service;
 import com.byr.warehouse.myexception.StoreException;
import com.byr.warehouse.util.PoiUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Service
public class ExcelService {

 private  T t;


public void ExportEcelService(List<T> excellist,String title,String sheetName,HttpServletResponse response,String filename,Class calzz){
    try {
        PoiUtils.exportExcel(excellist, title, sheetName, calzz, filename, response);
    } catch (StoreException e) {
        e.printStackTrace();
    }
}


public List<T> ImportExcelService(MultipartFile file,T t){
    List<T> personList = null;
    try {
        personList = PoiUtils.importExcel(file, 1, 1, t.getClass());
    } catch (StoreException e) {
        e.printStackTrace();
    }
    return personList;
}


public void ExportEcel(List<T> excellist,String title,String sheetName,String filename,Class calzz){
    try {
        PoiUtils.exportExcel(excellist, title, sheetName, calzz, filename);
    } catch (StoreException e) {
        e.printStackTrace();
    }
}


}