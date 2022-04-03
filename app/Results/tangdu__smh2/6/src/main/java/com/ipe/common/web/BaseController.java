package com.ipe.common.web;
 import com.ipe.common.web.support.DateEditor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class BaseController {

 protected  SimpleDateFormat SIMPLEDATEFORMAT;

 protected  SimpleDateFormat FORMATER;

 protected  SimpleDateFormat FORMATERYMD;


public void downFile(File file,String fileName,HttpServletResponse response){
    response.setContentType("application/x-download");
    response.setHeader("Pragma", "public");
    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
    InputStream in = new FileInputStream(file);
    fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
    response.addHeader("Content-disposition", "attachment;filename=" + fileName + SIMPLEDATEFORMAT.format(new Date()));
    downFile(in, response);
}


@InitBinder
public void initBinder(WebDataBinder binder){
    // String类型转换，防止XSS攻击
    /*binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
        });*/
    binder.registerCustomEditor(Date.class, new DateEditor());
    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
    binder.registerCustomEditor(Double.class, new CustomNumberEditor(Number.class, true));
    binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
    binder.registerCustomEditor(MultipartFile.class, new ByteArrayMultipartFileEditor());
}


public Map<String,Object> getMap(){
    return new HashMap<String, Object>();
}


}