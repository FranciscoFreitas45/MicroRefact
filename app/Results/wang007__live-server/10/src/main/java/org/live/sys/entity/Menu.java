package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import org.live.sys.support.Combinable;
import javax.persistence;
import java.util.List;
@Entity
@Table(name = "sys_menu")
public class Menu extends BaseEntityimplements Combinable{

 private  long serialVersionUID;

@OneToOne
@JoinColumn(name = "permission_id")
 private  Permission permission;

@OneToOne
@JoinColumn(name = "parent_id")
 private  Menu parent;

@Column
 private  String menuName;

@Column
 private  String menuUrl;

@Column
 private  String menuIcon;

@Column
 private  String menuOpenIcon;

@Column
 private  Integer serialNo;

@Column
 private  String description;

 private  String menuType;

// 不参与持久化
@Transient
 private  List<Menu> childMenus;

public Menu() {
}
@Override
public boolean compare(Object compareObj){
    return this.getParent() == compareObj;
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


public void setMenuIcon(String menuIcon){
    this.menuIcon = menuIcon;
}


public void setMenuOpenIcon(String menuOpenIcon){
    this.menuOpenIcon = menuOpenIcon;
}


public void setDescription(String description){
    this.description = description;
}


public List<Menu> getChildMenus(){
    return childMenus;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public String getDescription(){
    return description;
}


public void setMenuType(String menuType){
    this.menuType = menuType;
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


public void setPermission(Permission permission){
    this.permission = permission;
}


public void setParent(Menu parent){
    this.parent = parent;
}


public void setSerialNo(Integer serialNo){
    this.serialNo = serialNo;
}


public void setChildMenus(List<Menu> childMenus){
    this.childMenus = childMenus;
}


@Override
public void setChilds(List<? extends Combinable> combinables){
    this.setChildMenus((List<Menu>) combinables);
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


public String getMenuOpenIcon(){
    return menuOpenIcon;
}


public Integer getSerialNo(){
    return serialNo;
}


}