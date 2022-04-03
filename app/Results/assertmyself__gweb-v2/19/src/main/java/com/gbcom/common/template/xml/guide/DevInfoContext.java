package com.gbcom.common.template.xml.guide;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("DevInfoContext")
public class DevInfoContext {

@XStreamAsAttribute
 private  List<DevInfoGuide> list;


public List<DevInfoGuide> getList(){
    return list;
}


public void setList(List<DevInfoGuide> routeList){
    this.list = routeList;
}


}