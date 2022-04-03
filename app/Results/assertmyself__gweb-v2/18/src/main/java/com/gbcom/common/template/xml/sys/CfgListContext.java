package com.gbcom.common.template.xml.sys;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("CfgItemContext")
public class CfgListContext {

@XStreamAsAttribute
 private  List<CfgItem> list;


public List<CfgItem> getList(){
    return list;
}


public void setList(List<CfgItem> routeList){
    this.list = routeList;
}


}