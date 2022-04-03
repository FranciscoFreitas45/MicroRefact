package DTO;
 import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
public class TemplateUtil {

 public  String BASE_DIR;

 public  String TEMPLET_CODE_DEFAULT;

 public  String TEMPLET_ONE_DEFAULT;

 public  String TEMPLET_ONE_MANY_DEFAULT;

 public  String TEMPLET_VIEW_DIR_DEFAULT;

 public  String TEMPLET_LIST;

 private  String name;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getName(){
    return name;
}


public TemplateType getVal(String type){
    if ("add".equals(type)) {
        return ADD;
    } else if ("update".equals(type)) {
        return UPDATE;
    } else if ("detail".equals(type)) {
        return DETAIL;
    } else if ("list".equals(type)) {
        return LIST;
    } else {
        return null;
    }
}


public String getTempletPath(CgformTemplateEntity entity,Integer formType,TemplateType type){
    if (entity == null || StringUtils.isBlank(entity.getTemplateCode())) {
        entity = new CgformTemplateEntity();
        entity.setTemplateCode(TEMPLET_CODE_DEFAULT);
        entity.setTemplateListName(TEMPLET_LIST);
        if (CgAutoListConstant.JFORM_TYPE_MAIN_TALBE == formType) {
            entity.setTemplateAddName(TEMPLET_ONE_MANY_DEFAULT);
            entity.setTemplateUpdateName(TEMPLET_ONE_MANY_DEFAULT);
            entity.setTemplateDetailName(TEMPLET_ONE_MANY_DEFAULT);
        } else {
            entity.setTemplateAddName(TEMPLET_ONE_DEFAULT);
            entity.setTemplateUpdateName(TEMPLET_ONE_DEFAULT);
            entity.setTemplateDetailName(TEMPLET_ONE_DEFAULT);
        }
    }
    String templateCode = entity.getTemplateCode();
    String templateName = null;
    // update--begin--author:zhoujf date:20170320 for:TASK 1796【online】online改造模板，不区分一对多和单表的，固化模板文件名字，不需要复杂配置
    switch(type) {
        case ADD:
            // templateName=entity.getTemplateAddName();
            if (CgAutoListConstant.JFORM_TYPE_MAIN_TALBE == formType) {
                templateName = "jformunion.ftl";
            } else {
                templateName = "jform.ftl";
            }
            break;
        case UPDATE:
            // templateName=entity.getTemplateUpdateName();
            if (CgAutoListConstant.JFORM_TYPE_MAIN_TALBE == formType) {
                templateName = "jformunion.ftl";
            } else {
                templateName = "jform.ftl";
            }
            break;
        case LIST:
            templateName = entity.getTemplateListName();
            templateName = "autolist.ftl";
            break;
        case DETAIL:
            // templateName=entity.getTemplateDetailName();
            if (CgAutoListConstant.JFORM_TYPE_MAIN_TALBE == formType) {
                templateName = "jformunion.ftl";
            } else {
                templateName = "jform.ftl";
            }
            break;
        default:
            templateName = entity.getTemplateListName();
    }
    // update--end--author:zhoujf date:20170320 for:TASK 1796【online】online改造模板，不区分一对多和单表的，固化模板文件名字，不需要复杂配置
    StringBuffer buffer = new StringBuffer(BASE_DIR + templateCode + "/");
    buffer.append(TEMPLET_VIEW_DIR_DEFAULT + "/");
    buffer.append(templateName);
    return buffer.toString();
}


public Map<String,Object> processor(String content){
    Map<String, Object> map = new HashMap<String, Object>();
    try {
        JSONObject jsonObj = JSONObject.fromObject(content);
        String template = (String) jsonObj.get("template");
        String parseHtml = (String) jsonObj.get("parse");
        JSONArray jsonArray = new JSONArray().fromObject(jsonObj.get("data"));
        map.put("template", template);
        // 1.利用正则，取得所有的input标签
        String rexEg = "(<input[^>]*>)";
        Pattern p = Pattern.compile(rexEg);
        Matcher m = p.matcher(parseHtml);
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group());
        }
        Map<String, Object> tableData = null;
        int index = 0;
        for (int i = 0; i < result.size(); i++) {
            // 2,利用正则，匹配标签中是否含有listctrl字段
            String ctrlExp = "(listctrl)";
            Pattern ctrlP = Pattern.compile(ctrlExp);
            Matcher ctrlM = ctrlP.matcher(result.get(i));
            // 2.1 如果含有，则插入解析data.生成html后，讲html替换
            if (ctrlM.find()) {
                tableData = new HashMap<String, Object>();
                for (int j = index; j < jsonArray.size(); j++) {
                    JSONObject item = jsonArray.getJSONObject(j);
                    if ("listctrl".equals(item.getString("leipiplugins"))) {
                        String tempHtml = GetListctrl(jsonArray.getJSONObject(j), tableData, "");
                        parseHtml = parseHtml.replace(result.get(i), tempHtml);
                        index = j + 1;
                    }
                }
            }
        }
        map.put("parseHtml", parseHtml);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return map;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processor"))

.queryParam("content",content)
;
Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(),Map<String,Object>.class);
return aux;
}


}