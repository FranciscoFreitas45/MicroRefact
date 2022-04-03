package com.ukefu.util.es;
 import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.ukefu.webim.web.model.CallOutTask;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.Organ;
import com.ukefu.webim.web.model.User;
import Interface.User;
import Interface.Organ;
public class UKDataBean {

 private  long serialVersionUID;

 public  String id;

 private  String creater;

 private  String username;

 private  String orgi;

 private  Date createtime;

 private  Date updatetime;

 private  MetadataTable table;

 private  String type;

 private  User user;

 private  User assuser;

 private  Organ organ;

 private  CallOutTask task;

 private  JobDetail activity;

 private  JobDetail batch;

 private  Map<String,Object> values;


public User getAssuser(){
    return assuser;
}


public void setBatch(JobDetail batch){
    this.batch = batch;
}


public User getUser(){
    return user;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setAssuser(User assuser){
    this.assuser = assuser;
}


public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public void setActivity(JobDetail activity){
    this.activity = activity;
}


public Date getCreatetime(){
    return createtime;
}


public void setValues(Map<String,Object> values){
    this.values = values;
}


public void setTask(CallOutTask task){
    this.task = task;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setUser(User user){
    this.user = user;
}


public JobDetail getActivity(){
    return activity;
}


public void setUsername(String username){
    this.username = username;
}


public Map<String,Object> getValues(){
    return values;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setType(String type){
    this.type = type;
}


public void setOrgan(Organ organ){
    this.organ = organ;
}


public Organ getOrgan(){
    return organ;
}


public JobDetail getBatch(){
    return batch;
}


public MetadataTable getTable(){
    return table;
}


public String getType(){
    return type;
}


public void setTable(MetadataTable table){
    this.table = table;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public CallOutTask getTask(){
    return task;
}


}