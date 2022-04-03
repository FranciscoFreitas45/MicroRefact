package org.jeecgframework.tag.core.factory;
 import java.util.Map;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.tag.core.easyui.DataGridTag;
public class ComponentFactory {


public String invoke(String templatePath,DataGridTag dataGridTag){
    FreemarkerHelper free = new FreemarkerHelper();
    Map<String, Object> data = getData(dataGridTag);
    String content = free.parseTemplate(templatePath, data);
    return content;
}


public Map<String,Object> getData(DataGridTag dataGridTag)


}