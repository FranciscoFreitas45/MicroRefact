package org.jeecgframework.core.common.model.json;
 import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
public class TreeGrid {

 private  String id;

 private  String text;

 private  String parentId;

 private  String parentText;

 private  String code;

 private  String src;

 private  String note;

 private  Map<String,String> attributes;

 private  String operations;

 private  String state;

 private  String order;

 private  Map<String,Object> fieldMap;

 private  String functionType;

 private  String iconStyle;


public Map<String,Object> getFieldMap(){
    return fieldMap;
}


public String getParentText(){
    return parentText;
}


public void setOperations(String operations){
    this.operations = operations;
}


public String getId(){
    return id;
}


public void setAttributes(Map<String,String> attributes){
    this.attributes = attributes;
}


public void setIconStyle(String iconStyle){
    this.iconStyle = iconStyle;
}


public void setFunctionType(String functionType){
    this.functionType = functionType;
}


public String assembleFieldsJson(){
    String fieldsJson = ", 'fieldMap':" + JSON.toJSON(fieldMap);
    if (fieldMap != null && fieldMap.size() > 0) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            resultMap.put("fieldMap." + entry.getKey(), entry.getValue());
        }
        fieldsJson += ", " + JSON.toJSON(resultMap).toString().replace("{", "").replace("}", "");
    }
    return fieldsJson;
}


public void setId(String id){
    this.id = id;
}


public String getCode(){
    return code;
}


public void setSrc(String src){
    this.src = src;
}


public String getOperations(){
    return operations;
}


public Map<String,String> getAttributes(){
    return attributes;
}


public String getSrc(){
    return src;
}


public String getText(){
    return text;
}


public String getIconStyle(){
    return iconStyle;
}


public void setCode(String code){
    this.code = code;
}


public String getNote(){
    return note;
}


public void setOrder(String order){
    this.order = order;
}


public String toJson(){
    return "{" + "'id':'" + id + '\'' + ", 'text':'" + text + '\'' + ", 'parentId':'" + parentId + '\'' + ", 'parentText':'" + parentText + '\'' + ", 'code':'" + code + '\'' + ", 'src':'" + src + '\'' + ", 'note':'" + note + '\'' + ", 'attributes':" + attributes + ", 'operations':'" + operations + '\'' + ", 'state':'" + state + '\'' + ", 'order':'" + order + '\'' + // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
    ", 'iconStyle':'" + iconStyle + '\'' + // update-end--Author:chenj  Date:20160722 for：添加菜单图标样式
    assembleFieldsJson() + '}';
}


public String getState(){
    return state;
}


public String getFunctionType(){
    return functionType;
}


public void setParentText(String parentText){
    this.parentText = parentText;
}


public void setFieldMap(Map<String,Object> fieldMap){
    this.fieldMap = fieldMap;
}


public String getOrder(){
    return order;
}


public void setNote(String note){
    this.note = note;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public void setState(String state){
    this.state = state;
}


public String getParentId(){
    return parentId;
}


public void setText(String text){
    this.text = text;
}


}