package org.live.DTO;
 import org.live.sys.support.Combinable;
import java.util.List;
public class SidebarNode implements Combinable{

 private  String id;

 private  String menuName;

 private  String menuUrl;

 private  String menuIcon;

 private  Integer serialNo;

 private  String parentId;

 private  List<SidebarNode> childSidebarNodes;

public SidebarNode() {
}public SidebarNode(String id, String menuName, String menuUrl, String menuIcon, Integer serialNo, String parentId) {
    this.id = id;
    this.menuName = menuName;
    this.menuUrl = menuUrl;
    this.menuIcon = menuIcon;
    this.serialNo = serialNo;
    this.parentId = parentId;
}
public void setChildSidebarNodes(List<SidebarNode> childSidebarNodes){
    this.childSidebarNodes = childSidebarNodes;
}


public String getMenuUrl(){
    return menuUrl;
}


public List<SidebarNode> getChildSidebarNodes(){
    return childSidebarNodes;
}


public String getId(){
    return id;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public String getMenuIcon(){
    return menuIcon;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public String getMenuName(){
    return menuName;
}


@Override
public void setChilds(List<? extends Combinable> combinables){
    this.setChildSidebarNodes((List<SidebarNode>) combinables);
}


public String getParentId(){
    return parentId;
}


public Integer getSerialNo(){
    return serialNo;
}


}