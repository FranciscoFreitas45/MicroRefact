package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
public class BaseReportTemplate {

@XStreamAlias("relative")
 public  boolean relative;


public void setRelative(boolean relative){
    this.relative = relative;
}


public boolean isRelative(){
    return relative;
}


}