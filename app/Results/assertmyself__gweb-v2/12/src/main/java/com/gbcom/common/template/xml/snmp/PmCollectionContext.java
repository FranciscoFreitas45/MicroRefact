package com.gbcom.common.template.xml.snmp;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("context")
public class PmCollectionContext {

 private  List<PmCollectionVerdor> verdorList;


public List<PmCollectionVerdor> getVerdorList(){
    return verdorList;
}


public void setVerdorList(List<PmCollectionVerdor> verdorList){
    this.verdorList = verdorList;
}


}