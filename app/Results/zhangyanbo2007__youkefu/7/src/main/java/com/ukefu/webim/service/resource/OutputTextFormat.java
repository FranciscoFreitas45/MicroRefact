package com.ukefu.webim.service.resource;
 import java.util.HashMap;
import java.util.Map;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.webim.web.model.JobDetail;
public class OutputTextFormat {

 private  String id;

 private  String title;

 private  String parent;

 private  Map<String,Object> data;

 private  JobDetail job;

 private  UKDataBean dataBean;

 private  Object object;

public OutputTextFormat(JobDetail job) {
    this.job = job;
}
public String getParent(){
    return parent;
}


public Object getObject(){
    return object;
}


public UKDataBean getDataBean(){
    return dataBean;
}


public void setData(Map<String,Object> data){
    this.data = data;
}


public void setTitle(String title){
    this.title = title;
}


public String getId(){
    return id;
}


public void setObject(Object object){
    this.object = object;
}


public JobDetail getJob(){
    return job;
}


public void setDataBean(UKDataBean dataBean){
    this.dataBean = dataBean;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public void setJob(JobDetail job){
    this.job = job;
}


public void setParent(String parent){
    this.parent = parent;
}


public Map<String,Object> getData(){
    return data;
}


}