package com.gbcom.common.template.xml.snmp;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("collection")
public class MibNodeCollection {

@XStreamAsAttribute
 private  String item;

@XStreamAsAttribute
 private  boolean isVector;

@XStreamAlias("list")
 private  List<MibNode> mibNodeList;


public void setMibNodeList(List<MibNode> mibNodeList){
    this.mibNodeList = mibNodeList;
}


public void setVector(boolean isVector){
    this.isVector = isVector;
}


public String getItem(){
    return item;
}


public boolean isVector(){
    return isVector;
}


public void setItem(String item){
    this.item = item;
}


public List<MibNode> getMibNodeList(){
    return mibNodeList;
}


}