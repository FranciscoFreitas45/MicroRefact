package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";

public ImportFile(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
}
public Object getObject(){
    return object;
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


public Class getEntityClass(){
    return entityClass;
}


public String getEntityName(){
    return entityName;
}


public String getFileName(){
    return fileName;
}


public void setField(String field){
    this.field = field;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setField"))

.queryParam("field",field)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEntityName(String entityName){
    this.entityName = entityName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEntityName"))

.queryParam("entityName",entityName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFileName(String fileName){
    this.fileName = fileName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFileName"))

.queryParam("fileName",fileName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEntityClass(Class entityClass){
    this.entityClass = entityClass;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEntityClass"))

.queryParam("entityClass",entityClass)
;
restTemplate.put(builder.toUriString(),null);
}


}