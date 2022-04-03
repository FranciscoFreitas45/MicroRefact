package com.gbcom.common.template.xml.snmp;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("verdor")
public class PmCollectionVerdor {

@XStreamAsAttribute
 private String name;

 private  List<PmCollectionGroup> groupList;


public void setName(String name){
    this.name = name;
}


public List<PmCollectionGroup> getGroupList(){
    return groupList;
}


public void setGroupList(List<PmCollectionGroup> groupList){
    this.groupList = groupList;
}


public String getName(){
    return name;
}


}