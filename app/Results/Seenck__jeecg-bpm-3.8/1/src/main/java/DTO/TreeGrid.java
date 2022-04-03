package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Map<String,Object> getFieldMap(){
    return fieldMap;
}


public String getParentText(){
    return parentText;
}


public String getId(){
    return id;
}


public String getCode(){
    return code;
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


public String getNote(){
    return note;
}


public String getState(){
    return state;
}


public String getFunctionType(){
    return functionType;
}


public String getOrder(){
    return order;
}


public String getParentId(){
    return parentId;
}


public void setId(String id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setText(String text){
    this.text = text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setText"))

.queryParam("text",text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCode(String code){
    this.code = code;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCode"))

.queryParam("code",code)
;
restTemplate.put(builder.toUriString(),null);
}


public void setState(String state){
    this.state = state;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setState"))

.queryParam("state",state)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrder(String order){
    this.order = order;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrder"))

.queryParam("order",order)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSrc(String src){
    this.src = src;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSrc"))

.queryParam("src",src)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParentId(String parentId){
    this.parentId = parentId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParentId"))

.queryParam("parentId",parentId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParentText(String parentText){
    this.parentText = parentText;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParentText"))

.queryParam("parentText",parentText)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOperations(String operations){
    this.operations = operations;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOperations"))

.queryParam("operations",operations)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFieldMap(Map<String,Object> fieldMap){
    this.fieldMap = fieldMap;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFieldMap"))

.queryParam("fieldMap",fieldMap)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFunctionType(String functionType){
    this.functionType = functionType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFunctionType"))

.queryParam("functionType",functionType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIconStyle(String iconStyle){
    this.iconStyle = iconStyle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIconStyle"))

.queryParam("iconStyle",iconStyle)
;
restTemplate.put(builder.toUriString(),null);
}


}