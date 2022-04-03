package org.live.sys.vo;
 import org.live.sys.support.Combinable;
import java.util.List;
public class MenuNode implements Combinable{

 private  String id;

 private  String menuName;

 private  String menuType;

 private  String parentId;

 private  List<MenuNode> childMenuNodes;

 private  Integer serialNo;

public MenuNode() {
}public MenuNode(String id, String menuName, String parentId, Integer serialNo) {
    this.setId(id);
    this.setMenuName(menuName);
    this.setParentId(parentId);
    this.setSerialNo(serialNo);
}public MenuNode(String id, String menuName, String parentId, Integer serialNo, String menuType) {
    this.setId(id);
    this.setMenuName(menuName);
    this.setParentId(parentId);
    this.setSerialNo(serialNo);
    this.setMenuType(menuType);
}
@Override
public boolean compare(Object compareObj){
    MenuNode node = (MenuNode) compareObj;
    if (parentId == null) {
        // 没有父节点
        return node == null;
    } else {
        return node == null ? false : parentId.equals(node.getId());
    }
}


public String getId(){
    return id;
}


public void setChildMenuNodes(List<MenuNode> childMenuNodes){
    this.childMenuNodes = childMenuNodes;
}


public void setMenuType(String menuType){
    this.menuType = menuType;
}


public String getMenuType(){
    return menuType;
}


public List<MenuNode> getChildMenuNodes(){
    return childMenuNodes;
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
    this.setChildMenuNodes((List<MenuNode>) combinables);
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