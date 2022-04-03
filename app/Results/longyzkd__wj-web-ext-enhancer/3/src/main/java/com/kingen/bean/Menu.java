package com.kingen.bean;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tmanagermenu")
public class Menu {

 private  String menuId;

 private  String menuName;

 private  String iconUrl;

 private  String funUrl;

 private  String fmenuId;

 private  String funId;

 private  Integer orderId;

 private  String note;

 private  List<Menu> childList;

// TODO type 表示 菜单还是按钮
// Constructors
/**
 * default constructor
 */
public Menu() {
}/**
 * full constructor
 */
public Menu(String menuName, String iconUrl, String funUrl, String fmenuId, String funId, Integer orderId, String note) {
    this.menuName = menuName;
    this.iconUrl = iconUrl;
    this.funUrl = funUrl;
    this.fmenuId = fmenuId;
    this.funId = funId;
    this.orderId = orderId;
    this.note = note;
}
public void setFmenuId(String fmenuId){
    this.fmenuId = fmenuId;
}


@Column(name = "FMenuID", length = 1000)
public String getFmenuId(){
    return this.fmenuId;
}


public void setFunId(String funId){
    this.funId = funId;
}


@Column(name = "OrderID")
public Integer getOrderId(){
    return this.orderId;
}


public void setChildList(List<Menu> childList){
    this.childList = childList;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "MenuID", unique = true, nullable = false, length = 100)
public String getMenuId(){
    return this.menuId;
}


@Column(name = "Note", length = 2000)
public String getNote(){
    return this.note;
}


public void setFunUrl(String funUrl){
    this.funUrl = funUrl;
}


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


@Column(name = "FunURL", length = 1000)
public String getFunUrl(){
    return this.funUrl;
}


public void setMenuId(String menuId){
    this.menuId = menuId;
}


@Column(name = "FunID", length = 100)
public String getFunId(){
    return this.funId;
}


public void setIconUrl(String iconUrl){
    this.iconUrl = iconUrl;
}


public void setNote(String note){
    this.note = note;
}


@Transient
public List<Menu> getChildList(){
    return childList;
}


@Column(name = "MenuName", length = 400)
public String getMenuName(){
    return this.menuName;
}


@Column(name = "IconURL", length = 1000)
public String getIconUrl(){
    return this.iconUrl;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


}