package com.gbcom.common.template.xml.snmp;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("context")
public class MibNodeContext {

 private  List<MibNodeCollection> collectionList;


public void setCollectionList(List<MibNodeCollection> collectionList){
    this.collectionList = collectionList;
}


public List<MibNodeCollection> getCollectionList(){
    return collectionList;
}


}