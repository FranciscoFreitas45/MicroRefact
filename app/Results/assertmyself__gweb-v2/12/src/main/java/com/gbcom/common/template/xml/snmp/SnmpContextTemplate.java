package com.gbcom.common.template.xml.snmp;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("SnmpContext")
public class SnmpContextTemplate {

 private  List<SnmpConfigTemplate> list;


public List<SnmpConfigTemplate> getList(){
    return list;
}


public void setList(List<SnmpConfigTemplate> list){
    this.list = list;
}


}