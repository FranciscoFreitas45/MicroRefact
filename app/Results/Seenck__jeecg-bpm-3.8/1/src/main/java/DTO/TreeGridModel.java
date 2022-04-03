package DTO;
 import java.util.Map;
public class TreeGridModel {

 private  String idField;

 private  String textField;

 private  String childList;

 private  String parentId;

 private  String parentText;

 private  String code;

 private  String src;

 private  String roleid;

 private  String icon;

 private  String order;

 private  String functionType;

 private  String iconStyle;

 private  Map<String,Object> fieldMap;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getIdField(){
    return idField;
}


public Map<String,Object> getFieldMap(){
    return fieldMap;
}


public String getParentText(){
    return parentText;
}


public String getChildList(){
    return childList;
}


public String getCode(){
    return code;
}


public String getSrc(){
    return src;
}


public String getIconStyle(){
    return iconStyle;
}


public String getTextField(){
    return textField;
}


public String getRoleid(){
    return roleid;
}


public String getIcon(){
    return icon;
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


public void setRoleid(String roleid){
    this.roleid = roleid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoleid"))

.queryParam("roleid",roleid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIcon(String icon){
    this.icon = icon;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIcon"))

.queryParam("icon",icon)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTextField(String textField){
    this.textField = textField;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTextField"))

.queryParam("textField",textField)
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


public void setParentId(String parentId){
    this.parentId = parentId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParentId"))

.queryParam("parentId",parentId)
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


public void setIdField(String idField){
    this.idField = idField;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIdField"))

.queryParam("idField",idField)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChildList(String childList){
    this.childList = childList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChildList"))

.queryParam("childList",childList)
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


public void setIconStyle(String iconStyle){
    this.iconStyle = iconStyle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIconStyle"))

.queryParam("iconStyle",iconStyle)
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


}