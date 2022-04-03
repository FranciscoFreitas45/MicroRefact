package org.jeecgframework.web.cgform.service.impl.config.util;
 import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.jeecgframework.core.util.StringUtil;
public class ExtendJsonConvert {

 protected  Map<String,String> syscode;


public String dealSyscode(String field,int flag){
    String change = field;
    Iterator it = syscode.keySet().iterator();
    while (it.hasNext()) {
        String key = String.valueOf(it.next());
        String value = String.valueOf(syscode.get(key));
        if (flag == 1) {
            change = field.replaceAll(key, value);
        } else if (flag == 2) {
            change = field.replaceAll(value, key);
        }
    }
    return change;
}


public void json2HtmlForMap(Map<String,Object> map,String kye){
    String extendJson = (String) map.get(kye);
    map.put(kye, ExtendJsonConvert.json2Html(extendJson));
}


public void json2HtmlForList(List<Map<String,Object>> list,String kye){
    if (list != null && list.size() > 0) {
        for (Map<String, Object> map : list) {
            json2HtmlForMap(map, kye);
        }
    }
}


public String extendAttribute(String field){
    if (StringUtil.isEmpty(field)) {
        return "";
    }
    field = dealSyscode(field, 1);
    StringBuilder re = new StringBuilder();
    try {
        JSONObject obj = JSONObject.fromObject(field);
        Iterator it = obj.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            JSONObject nextObj = null;
            try {
                nextObj = ((JSONObject) obj.get(key));
                Iterator itvalue = nextObj.keys();
                re.append(key + "=" + "\"");
                if (nextObj.size() <= 1) {
                    String onlykey = String.valueOf(itvalue.next());
                    if ("value".equals(onlykey)) {
                        re.append(nextObj.get(onlykey) + "");
                    } else {
                        re.append(onlykey + ":" + nextObj.get(onlykey) + "");
                    }
                } else {
                    while (itvalue.hasNext()) {
                        String multkey = String.valueOf(itvalue.next());
                        String multvalue = nextObj.getString(multkey);
                        re.append(multkey + ":" + multvalue + ",");
                    }
                    re.deleteCharAt(re.length() - 1);
                }
                re.append("\" ");
            } catch (Exception e) {
                // add-begin--Author:Yandong  Date:20180521 for：TASK #2723 【bug】online扩展参数用法问题
                re.append(key + "=" + "\"");
                // 为了正则匹配 加上空格
                re.append(obj.get(key) + "\" ");
            // re.append("\" ");
            // add-end--Author:Yandong  Date:20180521 for：TASK #2723 【bug】online扩展参数用法问题
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "";
    }
    return dealSyscode(re.toString(), 2);
}


public String json2Html(String json){
    return extendAttribute(json);
}


}