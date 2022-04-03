package org.live.sys.vo;
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
@Override
public boolean compare(Object compareObj){
    SidebarNode node = (SidebarNode) compareObj;
    if (parentId == null) {
        return node == null;
    } else {
        return node == null ? false : parentId.equals(node.getId());
    }
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


public void setMenuIcon(String menuIcon){
    this.menuIcon = menuIcon;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public String getMenuIcon(){
    return menuIcon;
}


public void setId(String id){
    this.id = id;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public String getMenuName(){
    return menuName;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


@Override
public void setChilds(List<? extends Combinable> combinables){
    this.setChildSidebarNodes((List<SidebarNode>) combinables);
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


public String getParentId(){
    return parentId;
}


public Integer getSerialNo(){
    return serialNo;
}


}