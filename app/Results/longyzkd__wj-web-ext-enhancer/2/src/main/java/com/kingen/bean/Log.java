package com.kingen.bean;
 import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import com.kingen.util.StringUtils;
import com.kingen.util.excel.annotation.ExcelField;
@Entity
@Table(name = "sys_log")
public class Log {

 private  long serialVersionUID;

 private  String id;

 private  String type;

 private  String title;

 private  String status;

 private  String remoteAddr;

 private  String requestUri;

 private  String params;

 private  String userAgent;

 private  String dwmc;

 private  String exception;

 private  String createDate;

 public  String TYPE_ACCESS;

 public  String TYPE_EXCEPTION;

 private  String fromDate;

 private  String toDate;


@Column(name = "remoteAddr")
public String getRemoteAddr(){
    return remoteAddr;
}


public void setRequestUri(String requestUri){
    this.requestUri = requestUri;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return id;
}


@Column(name = "status")
public String getStatus(){
    return status;
}


@Transient
public String getFromDate(){
    return fromDate;
}


@ExcelField(title = "内容", align = 2, sort = 3)
@Column(name = "title")
public String getTitle(){
    return title;
}


@ExcelField(title = "时间", align = 3, sort = 4)
@Column(name = "createDate")
public String getCreateDate(){
    return createDate;
}


public void setId(String id){
    this.id = id;
}


public void setException(String exception){
    this.exception = exception;
}


@ExcelField(title = "单位名称", align = 2, sort = 1)
@Column(name = "dwmc")
public String getDwmc(){
    return dwmc;
}


public void setFromDate(String fromDate){
    this.fromDate = fromDate;
}


public void setDwmc(String dwmc){
    this.dwmc = dwmc;
}


@Column(name = "exception")
public String getException(){
    return exception;
}


@Column(name = "requestUri")
public String getRequestUri(){
    return requestUri;
}


public void setUserAgent(String userAgent){
    this.userAgent = userAgent;
}


public void setTitle(String title){
    this.title = title;
}


public void setType(String type){
    this.type = type;
}


public void setStatus(String status){
    this.status = status;
}


public void setToDate(String toDate){
    this.toDate = toDate;
}


public void setRemoteAddr(String remoteAddr){
    this.remoteAddr = remoteAddr;
}


@Column(name = "type")
public String getType(){
    return type;
}


public void setCreateDate(String createDate){
    this.createDate = createDate;
}


@ExcelField(title = "用户名", align = 2, sort = 2)
@Column(name = "userAgent")
public String getUserAgent(){
    return userAgent;
}


@Override
public String toString(){
    return ReflectionToStringBuilder.toString(this);
}


@Transient
public String getToDate(){
    return toDate;
}


@Column(name = "params")
public String getParams(){
    return params;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public void setParams(Map paramMap){
    if (paramMap == null) {
        return;
    }
    StringBuilder params = new StringBuilder();
    for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
        params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
        String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
        params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
    }
    this.params = params.toString();
}


}