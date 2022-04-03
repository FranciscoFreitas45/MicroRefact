package org.jeecgframework.core.common.model.json;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;
public class ImportFile {

 private  Object object;

 private  String fileName;

 private  String entityName;

 private  String field;

 private  Class entityClass;

 private  MultipartHttpServletRequest multipartRequest;

 private  HttpServletRequest request;

 private  HttpServletResponse response;

public ImportFile(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
}
public void setRequest(HttpServletRequest request){
    this.request = request;
}


public void setEntityClass(Class entityClass){
    this.entityClass = entityClass;
}


public Object getObject(){
    return object;
}


public void setField(String field){
    this.field = field;
}


public HttpServletRequest getRequest(){
    return request;
}


public HttpServletResponse getResponse(){
    return response;
}


public String getField(){
    return field;
}


public MultipartHttpServletRequest getMultipartRequest(){
    return multipartRequest;
}


public void setResponse(HttpServletResponse response){
    this.response = response;
}


public void setObject(Object object){
    this.object = object;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public void setFileName(String fileName){
    this.fileName = fileName;
}


public Class getEntityClass(){
    return entityClass;
}


public void setMultipartRequest(MultipartHttpServletRequest multipartRequest){
    this.multipartRequest = multipartRequest;
}


public String getEntityName(){
    return entityName;
}


public String getFileName(){
    return fileName;
}


}