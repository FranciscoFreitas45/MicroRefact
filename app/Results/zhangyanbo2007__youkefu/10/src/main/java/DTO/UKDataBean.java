package DTO;
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


public User getUser(){
    return user;
}


public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getCreater(){
    return creater;
}


public JobDetail getActivity(){
    return activity;
}


public Map<String,Object> getValues(){
    return values;
}


public Date getUpdatetime(){
    return updatetime;
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


public String getOrgi(){
    return orgi;
}


public CallOutTask getTask(){
    return task;
}


}