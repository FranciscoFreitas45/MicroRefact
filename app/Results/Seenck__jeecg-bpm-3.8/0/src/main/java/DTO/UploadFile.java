package DTO;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
public class UploadFile {

 private  String byteField;

 private  String titleField;

 private  String basePath;

 private  String realPath;

 private  String extend;

 private  boolean view;

 private  boolean rename;

 private  String swfpath;

 private  String cusPath;

 private  byte[] content;

 private  Object object;

 private  String fileKey;

 private  MultipartHttpServletRequest multipartRequest;

 private  HttpServletRequest request;

 private  HttpServletResponse response;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public UploadFile(HttpServletRequest request, Object object) {
    // 文件ID
    String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
    if (StringUtil.isNotEmpty(fileKey)) {
        this.fileKey = fileKey;
        this.request = request;
    } else {
        this.multipartRequest = (MultipartHttpServletRequest) request;
    }
    this.object = object;
}public UploadFile(HttpServletRequest request) {
    this.multipartRequest = (MultipartHttpServletRequest) request;
}public UploadFile(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
}public UploadFile() {
}
public HttpServletRequest getRequest(){
    return request;
}


public byte[] getContent(){
    return content;
}


public String getRealPath(){
    return realPath;
}


public MultipartHttpServletRequest getMultipartRequest(){
    return multipartRequest;
}


public String getExtend(){
    return extend;
}


public String get(String name){
    return getMultipartRequest().getParameter(name);
}


public String getByteField(){
    return byteField;
}


public String getFileKey(){
    return fileKey;
}


public String getTitleField(){
    return titleField;
}


public Object getObject(){
    return object;
}


public HttpServletResponse getResponse(){
    return response;
}


public String getCusPath(){
    return cusPath;
}


public String getSwfpath(){
    return swfpath;
}


public String getBasePath(){
    return basePath;
}


public void setCusPath(String cusPath){
    this.cusPath = cusPath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCusPath"))

.queryParam("cusPath",cusPath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExtend(String extend){
    this.extend = extend;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExtend"))

.queryParam("extend",extend)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTitleField(String titleField){
    this.titleField = titleField;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitleField"))

.queryParam("titleField",titleField)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRealPath(String realPath){
    this.realPath = realPath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRealPath"))

.queryParam("realPath",realPath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setObject(Object object){
    this.object = object;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setObject"))

.queryParam("object",object)
;
restTemplate.put(builder.toUriString(),null);
}


public void setByteField(String byteField){
    this.byteField = byteField;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setByteField"))

.queryParam("byteField",byteField)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRename(boolean rename){
    this.rename = rename;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRename"))

.queryParam("rename",rename)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBasePath(String basePath){
    this.basePath = basePath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBasePath"))

.queryParam("basePath",basePath)
;
restTemplate.put(builder.toUriString(),null);
}


}