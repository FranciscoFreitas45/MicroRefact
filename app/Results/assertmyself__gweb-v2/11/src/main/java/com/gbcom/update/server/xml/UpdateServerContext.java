package com.gbcom.update.server.xml;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("UpdateServerContext")
public class UpdateServerContext {

@XStreamAlias("list")
 private  List<UpdateServer> list;

@XStreamAlias("updateSwitch")
 private  boolean updateSwitch;


public List<UpdateServer> getList(){
    return list;
}


public void setUpdateSwitch(boolean updateSwitch){
    this.updateSwitch = updateSwitch;
}


public void setList(List<UpdateServer> list){
    this.list = list;
}


public boolean isUpdateSwitch(){
    return updateSwitch;
}


}