package org.jeecgframework.core.online.util;
 import java.io.StringWriter;
import java.util.Map;
import org.jeecgframework.core.util.ApplicationContextUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveModel;
public class FreemarkerHelper {

 private  Configuration _tplConfig;


public String parseTemplate(String tplName,Map<String,Object> paras){
    return this.parseTemplate(tplName, "utf-8", paras);
}


public String parseTemplateContent(String tplContent,Map<String,Object> paras){
    Configuration cfg = new Configuration();
    StringWriter writer = new StringWriter();
    cfg.setTemplateLoader(new StringTemplateLoader(tplContent));
    cfg.setDefaultEncoding("UTF-8");
    Template template;
    try {
        template = cfg.getTemplate("");
        template.process(paras, writer);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return writer.toString();
}


}