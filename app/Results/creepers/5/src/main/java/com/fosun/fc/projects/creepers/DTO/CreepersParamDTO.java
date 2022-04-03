package com.fosun.fc.projects.creepers.DTO;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.fosun.fc.projects.creepers.constant.BaseConstant.OrderUrlKey;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
public class CreepersParamDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String SEARCH_KEY_WORD;

 protected  String taskType;

 protected  String errorInfo;

 protected  String errorPath;

 protected  String taskStatus;

 protected  boolean doRedirect;

 protected  Map<String,String> searchKeyWord;

 protected  List<String> targetUrlList;

 protected  Map<OrderUrlKey,String> orderUrl;

 protected  Map<String,String> nameValuePair;

 private  String breakDownRequest;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public CreepersParamDTO() {
}
public String getTaskStatus(){
    if (StringUtils.isBlank(taskStatus)) {
        return StringUtils.EMPTY;
    } else {
        return taskStatus;
    }
}


public String getSearchKeyWordForString(){
    if (CommonMethodUtils.isEmpty(searchKeyWord) || !searchKeyWord.containsKey(SEARCH_KEY_WORD) || (searchKeyWord.containsKey(SEARCH_KEY_WORD) && StringUtils.isBlank(searchKeyWord.get(SEARCH_KEY_WORD)))) {
        return StringUtils.EMPTY;
    } else {
        return searchKeyWord.get(SEARCH_KEY_WORD);
    }
}


public String getNameValuePair(String key){
    String value = this.nameValuePair.get(key);
    if (StringUtils.isBlank(value)) {
        return StringUtils.EMPTY;
    } else {
        return value;
    }
}


public String getErrorPath(){
    if (StringUtils.isBlank(errorPath)) {
        return StringUtils.EMPTY;
    } else {
        return errorPath;
    }
}


public String getOrderUrl(OrderUrlKey order){
    String url = orderUrl.get(order);
    if (StringUtils.isBlank(url)) {
        return StringUtils.EMPTY;
    } else {
        return url;
    }
}


public String getErrorInfo(){
    if (StringUtils.isBlank(errorInfo)) {
        return StringUtils.EMPTY;
    } else {
        return errorInfo;
    }
}


public List<String> getTargetUrlList(){
    if (CommonMethodUtils.isEmpty(targetUrlList)) {
        return new ArrayList<String>();
    } else {
        return targetUrlList;
    }
}


public String getSearchKeyWord(String key){
    if (CommonMethodUtils.isEmpty(searchKeyWord) || StringUtils.isBlank(key) || !searchKeyWord.containsKey(key) || (searchKeyWord.containsKey(key) && StringUtils.isBlank(searchKeyWord.get(key)))) {
        return StringUtils.EMPTY;
    } else {
        return searchKeyWord.get(key);
    }
}


public String getTaskType(){
    if (StringUtils.isBlank(taskType)) {
        return StringUtils.EMPTY;
    } else {
        return taskType;
    }
}


public String getBreakDownRequest(){
    return breakDownRequest;
}


public void putSearchKeyWord(String key,String value){
    searchKeyWord.put(key, value);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/putSearchKeyWord"))

.queryParam("key",key)
.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTaskType(String taskType){
    this.taskType = taskType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTaskType"))

.queryParam("taskType",taskType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTaskStatus(String taskStatus){
    this.taskStatus = taskStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTaskStatus"))

.queryParam("taskStatus",taskStatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setErrorInfo(String errorInfo){
    this.errorInfo = errorInfo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrorInfo"))

.queryParam("errorInfo",errorInfo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setErrorPath(String errorPath){
    this.errorPath = errorPath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setErrorPath"))

.queryParam("errorPath",errorPath)
;
restTemplate.put(builder.toUriString(),null);
}


}