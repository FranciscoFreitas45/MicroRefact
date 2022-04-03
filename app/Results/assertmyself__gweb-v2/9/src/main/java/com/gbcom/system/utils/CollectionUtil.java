package com.gbcom.system.utils;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
public class CollectionUtil {

 public  String[] BROWSER_NAME;


public int compare(Map.Entry<Object,Object> obj1,Map.Entry<Object,Object> obj2){
    if (obj1.getValue() instanceof List<?> && obj2.getValue() instanceof List<?>) {
        return ((List<?>) obj2.getValue()).size() - ((List<?>) obj1.getValue()).size();
    } else {
        return Integer.parseInt(obj2.getValue().toString()) - Integer.parseInt(obj1.getValue().toString());
    }
}


public String listToString(Set set){
    if (set == null || set.size() == 0) {
        return "''";
    }
    StringBuilder result = new StringBuilder();
    boolean flag = false;
    for (Object string : set) {
        if (flag) {
            result.append(",");
        } else {
            flag = true;
        }
        result.append(string.toString());
    }
    return result.toString();
}


@SuppressWarnings("unchecked")
public Map mapSort(Map map,int num){
    List<Map.Entry<Object, Object>> entries = new ArrayList<Map.Entry<Object, Object>>(map.entrySet());
    Collections.sort(entries, new Comparator<Map.Entry<Object, Object>>() {

        public int compare(Map.Entry<Object, Object> obj1, Map.Entry<Object, Object> obj2) {
            if (obj1.getValue() instanceof List<?> && obj2.getValue() instanceof List<?>) {
                return ((List<?>) obj2.getValue()).size() - ((List<?>) obj1.getValue()).size();
            } else {
                return Integer.parseInt(obj2.getValue().toString()) - Integer.parseInt(obj1.getValue().toString());
            }
        }
    });
    Map<Object, Object> sortMap = new LinkedHashMap<Object, Object>();
    if (num == 0 || entries.size() < num) {
        for (int i = 0; i < entries.size(); i++) {
            Entry<Object, Object> entry = entries.get(i);
            sortMap.put(entry.getKey(), entry.getValue());
        }
    } else {
        for (int i = 0; i < num; i++) {
            Entry<Object, Object> entry = entries.get(i);
            sortMap.put(entry.getKey(), entry.getValue());
        }
    }
    return sortMap;
}


public T getFirstEle(Collection<T> collection){
    if (collection == null || collection.size() == 0) {
        return null;
    }
    return collection.iterator().next();
}


public String browserTypeCheck(String agent){
    if (agent == null) {
        throw new Exception("browser agent is null");
    }
    String browser = "";
    for (int i = 0; i < BROWSER_NAME.length; i++) {
        browser = BROWSER_NAME[i];
        if (agent.toUpperCase().indexOf(browser.toUpperCase()) == -1) {
            browser = "其它";
        } else {
            if (browser.equals("MSIE")) {
                browser = "IE";
            }
            break;
        }
    }
    return browser;
}


}