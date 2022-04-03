package com.gbcom.common.template.xml.snmp;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("group")
public class PmCollectionGroup {

@XStreamAsAttribute
 private  String id;

@XStreamAsAttribute
 private  boolean name;

@XStreamAlias("list")
 private  List<MibNode> mibNodeList;


public void setName(boolean name){
    this.name = name;
}


public void setMibNodeList(List<MibNode> mibNodeList){
    this.mibNodeList = mibNodeList;
}


public boolean isName(){
    return name;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public List<MibNode> getMibNodeList(){
    return mibNodeList;
}


}