package com.gbcom.common.template.xml.msg;
 import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
@XStreamAlias("routeContext")
public class RouteInfoContext {

@XStreamAsAttribute
 private  List<RouteInfo> list;


public List<RouteInfo> getRouteList(){
    return list;
}


public void setRouteList(List<RouteInfo> routeList){
    this.list = routeList;
}


}