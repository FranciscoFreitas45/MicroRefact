package com.ukefu.util;
 import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import freemarker.cache.TemplateLoader;
public class TempletLoader implements TemplateLoader{

 private  String DEFAULT_TEMPLATE_KEY;

 private  Map<String,String> templates;

public TempletLoader(String defaultTemplate) {
    if (defaultTemplate != null && !defaultTemplate.equals("")) {
        templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
    }
}
public long getLastModified(Object templateSource){
    return 0;
}


public Reader getReader(Object templateSource,String encoding){
    return new StringReader((String) templateSource);
}


public void AddTemplate(String name,String template){
    if (name == null || template == null || name.equals("") || template.equals("")) {
        return;
    }
    if (!templates.containsKey(name)) {
        templates.put(name, template);
    }
}


public void closeTemplateSource(Object templateSource){
}


public Object findTemplateSource(String name){
    if (name == null || name.equals("")) {
        name = DEFAULT_TEMPLATE_KEY;
    }
    return templates.get(name);
}


}