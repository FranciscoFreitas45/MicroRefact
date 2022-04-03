package com.ukefu.core;
 import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import com.ukefu.util.DateConverter;
import com.ukefu.webim.service.resource.ActivityResource;
import com.ukefu.webim.service.resource.BatchResource;
import com.ukefu.webim.service.resource.QualityResource;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.Log;
public class UKDataContext {

 public  String USER_SESSION_NAME;

 public  String GUEST_USER;

 public  String IM_USER_SESSION_NAME;

 public  String UKEFU_SYSTEM_DIC;

 public  String UKEFU_SYSTEM_AUTH_DIC;

 public  String UKEFU_SYSTEM_AREA_DIC;

 public  String UKEFU_SYSTEM_ADPOS_DIC;

 public  String UKEFU_SYSTEM_COMMENT_DIC;

 public  String UKEFU_SYSTEM_COMMENT_ITEM_DIC;

 public  String UKEFU_SYSTEM_DIS_AI;

 public  String UKEFU_SYSTEM_DIS_FORECAST;

 public  String UKEFU_SYSTEM_DIS_AGENT;

 public  String UKEFU_SYSTEM_ASSUSER;

 public  String UKEFU_SYSTEM_DIS_ORGAN;

 public  String UKEFU_SYSTEM_DIS_TIME;

 public  String UKEFU_SYSTEM_COOKIES_FLAG;

 public  String UKEFU_SYSTEM_NO_AI_CONFIG;

 public  String UKEFU_SYSTEM_NO_DAT;

 public  String UKEFU_SYSTEM_PRIVATEFIELD;

 public  String SYSTEM_INDEX;

 public  String CALLOUT_INDEX;

 public  String UKEFU_SYSTEM_SECFIELD;

 public  String UKEFU_SYSTEM_CALLCENTER;

 public  String UKEFU_SYSTEM_WORKORDEREMAIL;

 public  String UKEFU_SYSTEM_SMSEMAIL;

 public  String UKEFU_SYSTEM_AI_INPUT;

 public  String UKEFU_SYSTEM_AI_OUTPUT;

 public  String UKEFU_SYSTEM_NOTICESMS;

 public  String UKEFU_SYSTEM_NOTICEMAIL;

 public  String UKEFU_SYSTEM_INFOACQ;

 public  String GUEST_USER_ID_CODE;

 public  String WORKORDERS_CLOSED_STATUS;

 public  String SERVICE_QUENE_NULL_STR;

 public  String DEFAULT_TYPE;

 public  String START;

 public  String CACHE_SKILL;

 public  String CACHE_AGENT;

 public  String CUBE_TITLE_MEASURE;

 public  String UKEFU_SYSTEM_AREA;

 public  String UKEFU_SYSTEM_ADV;

 public  int MAX_IMAGE_WIDTH;

 private  boolean imServerRunning;

 public  int AGENT_STATUS_MAX_USER;

 public  String SYSTEM_CACHE_SESSION_CONFIG;

 public  String SYSTEM_CACHE_SESSION_CONFIG_LIST;

 public  String SYSTEM_CACHE_AI_CONFIG;

 public  String SYSTEM_CACHE_QUALITY_CONFIG;

 public  String SYSTEM_CACHE_CALLOUT_CONFIG;

 public  String SYSTEM_ORGI;

 public  String USER_CURRENT_ORGI_SESSION;

 public  Map<String,Boolean> model;

 public  Map<String,Class<?>> uKeFuResourceMap;

 public  Map<String,JobDetail> localJobDetailMap;

 private  int WebIMPort;

 private  ApplicationContext applicationContext;

 private  ElasticsearchTemplate templet;

 public  BlockingQueue<Log> tempLogQueue;

 public  int QUALITY_ARCHIVE_DEFAULT_DAY;

 private  String type;

 private  String namespace;


public String getSystemSecrityPassword(){
    return "UCKeFu";
}


public Class<?> getResource(String resource){
    return uKeFuResourceMap.get(resource);
}


public int getWebIMPort(){
    return WebIMPort;
}


public void setWebIMPort(int webIMPort){
    WebIMPort = webIMPort;
}


public void setIMServerStatus(boolean running){
    imServerRunning = running;
}


public void setType(String type){
    this.type = type;
}


public void setApplicationContext(ApplicationContext context){
    applicationContext = context;
}


public String getType(){
    return type;
}


public String getNamespace(){
    return namespace;
}


public boolean getIMServerStatus(){
    return imServerRunning;
}


public boolean needRunTask(){
    return ClusterContext.getInstance().isMaster();
}


public String toString(){
    return super.toString().toLowerCase();
}


public void setTemplet(ElasticsearchTemplate templet){
    UKDataContext.templet = templet;
}


public void setNamespace(String namespace){
    this.namespace = namespace;
}


public ApplicationContext getContext(){
    return applicationContext;
}


public ElasticsearchTemplate getTemplet(){
    return templet;
}


}