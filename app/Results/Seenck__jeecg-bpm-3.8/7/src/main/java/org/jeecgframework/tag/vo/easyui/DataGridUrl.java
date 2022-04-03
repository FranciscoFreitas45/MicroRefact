package org.jeecgframework.tag.vo.easyui;
 public class DataGridUrl {

 private  String url;

 private  String title;

 private  String icon;

 private  String value;

 private  String width;

 private  String height;

 private  OptTypeDirection type;

 private  String isbtn;

 private  String message;

 private  String exp;

 private  String funname;

 private  boolean isRadio;

 private  String onclick;

 private  String urlStyle;

 private  String urlclass;

 private  String urlfont;

 private  String id;

 private  String operationCode;

 private  boolean inGroup;


public String getId(){
    return id;
}


public String getWidth(){
    return width;
}


public String getHeight(){
    return height;
}


public void setUrlStyle(String urlStyle){
    this.urlStyle = urlStyle;
}


public String getTitle(){
    return title;
}


public void setOperationCode(String operationCode){
    this.operationCode = operationCode;
}


public void setUrlclass(String urlclass){
    this.urlclass = urlclass;
}


public void setExp(String exp){
    this.exp = exp;
}


public void setIsbtn(String isbtn){
    this.isbtn = isbtn;
}


public void setId(String id){
    this.id = id;
}


public void setUrlfont(String urlfont){
    this.urlfont = urlfont;
}


public boolean isInGroup(){
    return inGroup;
}


public String getUrlclass(){
    return urlclass;
}


public String getFunname(){
    return funname;
}


public String getUrlStyle(){
    return urlStyle;
}


public void setRadio(boolean isRadio){
    this.isRadio = isRadio;
}


public String getIsbtn(){
    return isbtn;
}


public void setInGroup(boolean inGroup){
    this.inGroup = inGroup;
}


public String getOnclick(){
    return onclick;
}


public String getMessage(){
    return message;
}


public void setTitle(String title){
    this.title = title;
}


public void setOnclick(String onclick){
    this.onclick = onclick;
}


public void setFunname(String funname){
    this.funname = funname;
}


public void setMessage(String message){
    this.message = message;
}


public void setType(OptTypeDirection type){
    this.type = type;
}


public String getOperationCode(){
    return operationCode;
}


public boolean isRadio(){
    return isRadio;
}


public void setWidth(String width){
    this.width = width;
}


public void setHeight(String height){
    this.height = height;
}


public String getExp(){
    return exp;
}


public void setUrl(String url){
    this.url = url;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public String getUrl(){
    return url;
}


public String getValue(){
    return value;
}


public OptTypeDirection getType(){
    return type;
}


public void setValue(String value){
    this.value = value;
}


public String getUrlfont(){
    return urlfont;
}


@Override
public String toString(){
    return "DataGridUrl [url=" + url + ", title=" + title + ", icon=" + icon + ", value=" + value + ", width=" + width + ", height=" + height + ", type=" + type + ", isbtn=" + isbtn + ", message=" + message + ", exp=" + exp + ", funname=" + funname + ", isRadio=" + isRadio + ", onclick=" + onclick + ", urlStyle=" + urlStyle + ", urlclass=" + urlclass + ", urlfont=" + urlfont + ", id=" + id + ", operationCode=" + operationCode + "]";
}


}