package org.jeecgframework.web.cgform.entity.config;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class CgSubTableVO {

 private CgFormHeadEntity head;

 private List<Map<String,Object>> fieldList;

 private List<Map<String,Object>> hiddenFieldList;

 private List<Map<String,Object>> fieldNoAreaList;

 private List<Map<String,Object>> fieldAreaList;


public void setFieldList(List<Map<String,Object>> fieldList){
    this.fieldList = fieldList;
    for (Map<String, Object> map : fieldList) {
        if ("Y".equals((String) map.get("is_show"))) {
            if ("textarea".equals((String) map.get("show_type"))) {
                this.fieldAreaList.add(map);
            } else {
                this.fieldNoAreaList.add(map);
            }
        }
    }
}


public List<Map<String,Object>> getFieldList(){
    return fieldList;
}


public List<Map<String,Object>> getFieldNoAreaList(){
    return fieldNoAreaList;
}


public void setFieldAreaList(List<Map<String,Object>> fieldAreaList){
    this.fieldAreaList = fieldAreaList;
}


public List<Map<String,Object>> getFieldAreaList(){
    return fieldAreaList;
}


public CgFormHeadEntity getHead(){
    return head;
}


public void setHead(CgFormHeadEntity head){
    this.head = head;
}


public List<Map<String,Object>> getHiddenFieldList(){
    return hiddenFieldList;
}


public void setHiddenFieldList(List<Map<String,Object>> hiddenFieldList){
    this.hiddenFieldList = hiddenFieldList;
}


public void setFieldNoAreaList(List<Map<String,Object>> fieldNoAreaList){
    this.fieldNoAreaList = fieldNoAreaList;
}


}