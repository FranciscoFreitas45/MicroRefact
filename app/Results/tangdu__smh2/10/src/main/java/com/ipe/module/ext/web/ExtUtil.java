package com.ipe.module.ext.web;
 import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import java.io.File;
import java.io.StringWriter;
import java.util.Map;
public class ExtUtil {

 private  String PATH;


public String generate(Map<String,Object> objectMap,String type,Boolean createFile,String path){
    Configuration cfg = new Configuration();
    try {
        cfg.setDirectoryForTemplateLoading(new File(PATH));
        StringWriter result = new StringWriter();
        Template template = cfg.getTemplate(type + ".ftl");
        if (template != null) {
            template.process(objectMap, result);
            if (createFile && StringUtils.isNotBlank(path)) {
                FileUtils.write(new File(path + "\\" + objectMap.get("entityName") + ".js"), result.toString(), "UTF-8");
            }
        }
        return result.toString();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}


}