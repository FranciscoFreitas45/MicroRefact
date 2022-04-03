package org.jeecgframework.core.common.model.common;
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
public void setRequest(HttpServletRequest request){
    this.request = request;
}


public void setContent(byte[] content){
    this.content = content;
}


public HttpServletRequest getRequest(){
    return request;
}


public boolean isView(){
    return view;
}


public void setFileKey(String fileKey){
    this.fileKey = fileKey;
}


public byte[] getContent(){
    return content;
}


public void setTitleField(String titleField){
    this.titleField = titleField;
}


public void setByteField(String byteField){
    this.byteField = byteField;
}


public void setResponse(HttpServletResponse response){
    this.response = response;
}


public String getRealPath(){
    return realPath;
}


public MultipartHttpServletRequest getMultipartRequest(){
    return multipartRequest;
}


public void setObject(Object object){
    this.object = object;
}


public String getExtend(){
    return extend;
}


public String get(String name){
    return getMultipartRequest().getParameter(name);
}


public void setRename(boolean rename){
    this.rename = rename;
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


public void setSwfpath(String swfpath){
    this.swfpath = swfpath;
}


public void setExtend(String extend){
    this.extend = extend;
}


public void setBasePath(String basePath){
    this.basePath = basePath;
}


public void setCusPath(String cusPath){
    this.cusPath = cusPath;
}


public String getSwfpath(){
    return swfpath;
}


public void setRealPath(String realPath){
    this.realPath = realPath;
}


public String getBasePath(){
    return basePath;
}


public boolean isRename(){
    return rename;
}


public void setMultipartRequest(MultipartHttpServletRequest multipartRequest){
    this.multipartRequest = multipartRequest;
}


public void setView(boolean view){
    this.view = view;
}


}