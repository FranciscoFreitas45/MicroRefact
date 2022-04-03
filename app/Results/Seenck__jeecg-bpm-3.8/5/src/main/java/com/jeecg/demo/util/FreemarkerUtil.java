package com.jeecg.demo.util;
 import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class FreemarkerUtil {

 private  Object LOCK;

 public  int WORD_FILE;

 public  int EXCEL_FILE;

 private  Configuration cfg;

 private  FreemarkerUtil ftl;

private FreemarkerUtil(String templateFolder) throws IOException {
    cfg = new Configuration();
    cfg.setDirectoryForTemplateLoading(new File(templateFolder));
    cfg.setObjectWrapper(new DefaultObjectWrapper());
}
public void check(HttpServletRequest request){
    if (ftl == null) {
        synchronized (LOCK) {
            try {
                ftl = new FreemarkerUtil(request.getServletContext().getRealPath("/") + "export/template");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


public void createFile(String templateName,String docFileName,Map<String,Object> rootMap,HttpServletRequest request,HttpServletResponse response,int fileType){
    // response.resetBuffer();
    // 设置导出
    response.addHeader("Cache-Control", "no-cache");
    response.setCharacterEncoding("UTF-8");
    if (WORD_FILE == fileType) {
        response.setContentType("application/vnd.ms-word;charset=UTF-8");
    } else if (EXCEL_FILE == fileType) {
        response.setContentType("application/octet-stream;charset=UTF-8");
    } else {
        response.setContentType("application/octet-stream");
    }
    String ua = request.getHeader("user-agent");
    ua = ua == null ? null : ua.toLowerCase();
    if (ua != null && (ua.indexOf("firefox") > 0 || ua.indexOf("safari") > 0)) {
        try {
            docFileName = new String(docFileName.getBytes(), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + docFileName);
        } catch (Exception e) {
        }
    } else {
        try {
            docFileName = URLEncoder.encode(docFileName, "utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + docFileName);
        } catch (Exception e) {
        }
    }
    check(request);
    // 解析模版
    Template temp = cfg.getTemplate(templateName, "UTF-8");
    PrintWriter write = response.getWriter();
    try {
        temp.process(rootMap, write);
    } catch (TemplateException e) {
        e.printStackTrace();
    } finally {
        if (write != null) {
            write.flush();
            write.close();
        }
    }
}


}