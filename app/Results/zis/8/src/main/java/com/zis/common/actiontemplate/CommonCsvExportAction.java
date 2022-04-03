package com.zis.common.actiontemplate;
 import java.io.BufferedOutputStream;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.common.util.ZisUtils;
public class CommonCsvExportAction extends ActionSupportimplements ServletResponseAware{

 private  long serialVersionUID;

 private  BufferedOutputStream bos;

 private  HttpServletResponse response;

 protected  Logger logger;


public String getTableHeaders()


public String getRowDatas(T record)


public void setResponseHeader(){
    try {
        // 两种方法都可以
        response.setContentType("application/msexcel;charset=UTF-8");
        // response.setContentType("application/octet-stream;charset=iso-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(setExportFileName(), "UTF-8"));
        // 客户端不缓存
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        this.bos = new BufferedOutputStream(response.getOutputStream());
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public List<T> queryExportData()


public void setServletResponse(HttpServletResponse arg0){
    this.response = arg0;
}


public Collection<T> transformList(List<T> list){
    return list;
}


public String export(){
    // 获取需要导出的数据
    List<T> list = queryExportData();
    Collection<T> transformedList = transformList(list);
    // 创建工作表
    setResponseHeader();
    // 创建表头
    try {
        byte[] commonCsvHead = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
        bos.write(commonCsvHead);
        bos.write(getTableHeaders().getBytes());
        bos.flush();
        // 填充数据
        for (T record : transformedList) {
            // 过滤需要跳过的记录
            bos.write(getRowDatas(record).getBytes());
            bos.flush();
        }
        bos.close();
        return SUCCESS;
    } catch (Exception e) {
        logger.error("导出数据失败" + e.getMessage(), e);
        throw new RuntimeException("导出数据失败", e);
    }
}


public String setExportFileName(){
    return "导出数据-" + ZisUtils.getDateString("yyyy-MM-dd") + ".csv";
}


}