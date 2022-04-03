package org.live.DTO;
 import org.live.common.base.BaseEntity;
import org.live.sys.support.Combinable;
import javax.persistence;
import java.util.List;
public class Menu extends BaseEntityimplements Combinable{

 private  long serialVersionUID;

 private  Permission permission;

 private  Menu parent;

 private  String menuName;

 private  String menuUrl;

 private  String menuIcon;

 private  String menuOpenIcon;

 private  Integer serialNo;

 private  String description;

 private  String menuType;

 private  List<Menu> childMenus;

public Menu() {
}
public Menu getParent(){
    return parent;
}


public Permission getPermission(){
    return permission;
}


public String getMenuUrl(){
    return menuUrl;
}


public List<Menu> getChildMenus(){
    return childMenus;
}


public String getDescription(){
    return description;
}


public String getMenuType(){
    return menuType;
}


public String getMenuIcon(){
    return menuIcon;
}


public String getMenuName(){
    return menuName;
}


public String getMenuOpenIcon(){
    return menuOpenIcon;
}


public Integer getSerialNo(){
    return serialNo;
}


}