package com.fosun.fc.projects.creepers.dto;
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

public CreepersParamDTO() {
}
public void setTaskType(String taskType){
    this.taskType = taskType;
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


public boolean isDoRedirect(){
    return doRedirect;
}


public void setDoRedirect(boolean doRedirect){
    this.doRedirect = doRedirect;
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


public void setNameValuePair(Map<String,String> nameValuePair){
    if (CommonMethodUtils.isEmpty(nameValuePair)) {
        return;
    }
    this.nameValuePair = nameValuePair;
}


public void putSearchKeyWord(String key,String value){
    searchKeyWord.put(key, value);
}


public void setOrderUrl(Map<OrderUrlKey,String> orderUrl){
    if (CommonMethodUtils.isEmpty(orderUrl)) {
        return;
    }
    this.orderUrl = orderUrl;
}


public void setBreakDownRequest(String breakDownRequest){
    this.breakDownRequest = breakDownRequest;
}


public void setErrorPath(String errorPath){
    this.errorPath = errorPath;
}


public void putNameValuePair(String key,String value){
    this.nameValuePair.put(key, value);
}


public void setSearchKeyWord(Map<String,String> searchMap){
    this.searchKeyWord = searchMap;
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


public void putTargetUrlList(String targetUrl){
    if (StringUtils.isBlank(targetUrl)) {
        return;
    }
    this.targetUrlList.add(targetUrl);
}


public void setErrorInfo(String errorInfo){
    this.errorInfo = errorInfo;
}


public String getSearchKeyWord(String key){
    if (CommonMethodUtils.isEmpty(searchKeyWord) || StringUtils.isBlank(key) || !searchKeyWord.containsKey(key) || (searchKeyWord.containsKey(key) && StringUtils.isBlank(searchKeyWord.get(key)))) {
        return StringUtils.EMPTY;
    } else {
        return searchKeyWord.get(key);
    }
}


public void setTaskStatus(String taskStatus){
    this.taskStatus = taskStatus;
}


public void setTargetUrlList(List<String> targetUrlList){
    if (CommonMethodUtils.isEmpty(targetUrlList)) {
        return;
    }
    this.targetUrlList = targetUrlList;
}


public String getTaskType(){
    if (StringUtils.isBlank(taskType)) {
        return StringUtils.EMPTY;
    } else {
        return taskType;
    }
}


public void putOrderUrl(OrderUrlKey order,String url){
    orderUrl.put(order, url);
}


public String getBreakDownRequest(){
    return breakDownRequest;
}


}