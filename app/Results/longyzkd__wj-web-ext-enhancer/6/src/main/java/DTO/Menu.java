package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Column(name = "FMenuID", length = 1000)
public String getFmenuId(){
    return this.fmenuId;
}


@Column(name = "OrderID")
public Integer getOrderId(){
    return this.orderId;
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


public void setOrderId(Integer orderId){
    this.orderId = orderId;
}


@Column(name = "FunURL", length = 1000)
public String getFunUrl(){
    return this.funUrl;
}


@Column(name = "FunID", length = 100)
public String getFunId(){
    return this.funId;
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


}