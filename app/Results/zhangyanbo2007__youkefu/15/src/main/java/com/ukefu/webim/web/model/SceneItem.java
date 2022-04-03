package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_xiaoe_sceneitem")
@org.hibernate.annotations.Proxy(lazy = false)
public class SceneItem {

 private  long serialVersionUID;

 private  String id;

 private  String content;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String replaytype;

 private  boolean allowask;

 private  String sceneid;

 private  int inx;

 private  String itemtype;


public void setReplaytype(String replaytype){
    this.replaytype = replaytype;
}


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public String getReplaytype(){
    return replaytype;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setInx(int inx){
    this.inx = inx;
}


public boolean isAllowask(){
    return allowask;
}


public Date getCreatetime(){
    return createtime;
}


public int getInx(){
    return inx;
}


public void setAllowask(boolean allowask){
    this.allowask = allowask;
}


public String getOrgi(){
    return orgi;
}


public void setSceneid(String sceneid){
    this.sceneid = sceneid;
}


public void setCreater(String creater){
    this.creater = creater;
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


public String getItemtype(){
    return itemtype;
}


public String getSceneid(){
    return sceneid;
}


public void setItemtype(String itemtype){
    this.itemtype = itemtype;
}


}