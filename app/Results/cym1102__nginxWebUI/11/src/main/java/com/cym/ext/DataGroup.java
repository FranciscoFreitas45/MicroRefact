package com.cym.ext;
 import java.util.List;
public class DataGroup {

 private List<KeyValue> pv;

 private List<KeyValue> uv;

 private List<KeyValue> status;

 private List<KeyValue> browser;

 private List<KeyValue> httpReferer;


public void setHttpReferer(List<KeyValue> httpReferer){
    this.httpReferer = httpReferer;
}


public List<KeyValue> getPv(){
    return pv;
}


public void setUv(List<KeyValue> uv){
    this.uv = uv;
}


public void setBrowser(List<KeyValue> browser){
    this.browser = browser;
}


public List<KeyValue> getStatus(){
    return status;
}


public void setPv(List<KeyValue> pv){
    this.pv = pv;
}


public List<KeyValue> getUv(){
    return uv;
}


public List<KeyValue> getHttpReferer(){
    return httpReferer;
}


public List<KeyValue> getBrowser(){
    return browser;
}


public void setStatus(List<KeyValue> status){
    this.status = status;
}


}