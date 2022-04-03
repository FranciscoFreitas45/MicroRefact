package com.xwtec.xwserver.util.ueditor;
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.ProUtil;
public class Uploader {

 private  int MAX_SIZE;

 private  String url;

 private  String fileName;

 private  String state;

 private  String type;

 private  String originalName;

 private  String size;

 private  HttpServletRequest request;

 private  String title;

 private  String savePath;

 private  String[] allowFiles;

 private  long maxSize;

 private  HashMap<String,String> errorInfo;

 private  Map<String,String> params;

 private  byte[] fileBytes;

 public  String ENCODE;

 private  Logger log;

/**
 * 方法描述:带参构造方法。
 * @param request http请求对象
 * date:2014-1-6
 * add by: liu_tao@xwtec.cn
 */
public Uploader(HttpServletRequest request) {
    this.request = request;
    this.params = new HashMap<String, String>();
    this.setMaxSize(Uploader.MAX_SIZE);
    this.parseParams();
    HashMap<String, String> tmp = this.errorInfo;
    // 默认成功
    tmp.put("SUCCESS", "SUCCESS");
    // 未包含文件上传域
    tmp.put("NOFILE", "未包含文件上传域");
    // 不允许的文件格式
    tmp.put("TYPE", "不允许的文件格式");
    // 文件大小超出限制
    tmp.put("SIZE", "文件大小超出限制");
    // 请求类型错误
    tmp.put("ENTYPE", "请求类型ENTYPE错误");
    // 上传请求异常
    tmp.put("REQUEST", "上传请求异常");
    // 未找到上传文件
    tmp.put("FILE", "未找到上传文件");
    // IO异常
    tmp.put("IO", "IO异常");
    // 目录创建失败
    tmp.put("DIR", "目录创建失败");
    // 未知错误
    tmp.put("UNKNOWN", "未知错误");
}
public String getName(String fileName){
    Random random = new Random();
    return this.fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + this.getFileExt(fileName);
}


public String getParameterValue(InputStream in){
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String result = "";
    String tmpString = null;
    try {
        while ((tmpString = reader.readLine()) != null) {
            result += tmpString;
        }
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return result;
}


public void setSavePath(String savePath){
    this.savePath = savePath;
}


public void uploadBase64(String fieldName){
    String savePath = this.getFolder(this.savePath);
    String base64Data = this.request.getParameter(fieldName);
    this.fileName = this.getName("test.png");
    this.url = savePath + "/" + this.fileName;
    BASE64Decoder decoder = new BASE64Decoder();
    try {
        File outFile = new File(this.getPhysicalPath(this.url));
        OutputStream ro = new FileOutputStream(outFile);
        byte[] b = decoder.decodeBuffer(base64Data);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        ro.write(b);
        ro.flush();
        ro.close();
        this.state = this.errorInfo.get("SUCCESS");
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        this.state = this.errorInfo.get("IO");
    }
}


public void upload(){
    boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
    if (!isMultipart) {
        this.state = this.errorInfo.get("NOFILE");
        return;
    }
    if (this.fileBytes == null) {
        this.state = this.errorInfo.get("FILE");
        return;
    }
    // 存储title
    this.title = this.getParameter("pictitle");
    try {
        String savePath = this.getFolder(this.savePath);
        if (!this.checkFileType(this.originalName)) {
            this.state = this.errorInfo.get("TYPE");
            return;
        }
        if (this.fileBytes.length > this.maxSize) {
            this.state = this.errorInfo.get("SIZE");
            return;
        }
        this.fileName = this.getName(this.originalName);
        this.type = this.getFileExt(this.fileName);
        this.url = savePath + "/" + this.fileName;
        FileOutputStream fos = new FileOutputStream(this.getPhysicalPath(this.url));
        fos.write(this.fileBytes);
        fos.close();
        this.state = this.errorInfo.get("SUCCESS");
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        this.state = this.errorInfo.get("IO");
    }
}


public boolean checkFileType(String fileName){
    Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
    while (type.hasNext()) {
        String ext = type.next();
        if (fileName.toLowerCase().endsWith(ext)) {
            return true;
        }
    }
    return false;
}


public String getOriginalName(){
    return this.originalName;
}


public byte[] getFileOutputStream(InputStream in){
    try {
        return IOUtils.toByteArray(in);
    } catch (IOException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return null;
    }
}


public void setMaxSize(long size){
    this.maxSize = size * 1024;
}


public String getFolder(String path){
    SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
    path += "/" + formater.format(new Date());
    File dir = new File(this.getPhysicalPath(path));
    if (!dir.exists()) {
        try {
            dir.mkdirs();
        } catch (Exception e) {
            log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
            this.state = this.errorInfo.get("DIR");
            return "";
        }
    }
    return path;
}


public String getUrl(){
    return this.url;
}


public String getSize(){
    return this.size;
}


public String getState(){
    return this.state;
}


public String getTitle(){
    return this.title;
}


public String getType(){
    return this.type;
}


public void setAllowFiles(String[] allowFiles){
    this.allowFiles = allowFiles;
}


public String getPhysicalPath(String path){
    return ProUtil.get("uploadFileBasePath") + File.separator + path;
}


public String getFileExt(String fileName){
    return fileName.substring(fileName.lastIndexOf("."));
}


public String getFileName(){
    return this.fileName;
}


public void parseParams(){
    DiskFileItemFactory dff = new DiskFileItemFactory();
    try {
        ServletFileUpload sfu = new ServletFileUpload(dff);
        sfu.setSizeMax(this.maxSize);
        sfu.setHeaderEncoding("utf-8");
        FileItemIterator fii = sfu.getItemIterator(this.request);
        while (fii.hasNext()) {
            FileItemStream item = fii.next();
            // 普通参数存储
            if (item.isFormField()) {
                this.params.put(item.getFieldName(), this.getParameterValue(item.openStream()));
            } else {
                // 只保留一个
                if (this.fileBytes == null) {
                    this.fileBytes = this.getFileOutputStream(item.openStream());
                    this.originalName = item.getName();
                }
            }
        }
    } catch (SizeLimitExceededException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        this.state = this.errorInfo.get("SIZE");
    } catch (InvalidContentTypeException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        this.state = this.errorInfo.get("ENTYPE");
    } catch (FileUploadException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        this.state = this.errorInfo.get("REQUEST");
    } catch (Exception e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        this.state = this.errorInfo.get("UNKNOWN");
    }
}


public String getParameter(String name){
    return this.params.get(name);
}


}