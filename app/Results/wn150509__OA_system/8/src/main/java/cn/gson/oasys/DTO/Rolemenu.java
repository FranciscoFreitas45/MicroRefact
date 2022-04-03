package cn.gson.oasys.DTO;
 import javax.persistence.Column;
public class Rolemenu {

 private  Long menuId;

 private  String menuName;

 private  String menuUrl;

 private  Boolean show;

 private  Boolean check;

 private  String menuIcon;

 private  Integer sortId;

 private  Integer menuGrade;

 private  Long parentId;

public Rolemenu(Long menuId, String menuName, String menuUrl, Boolean show, Boolean check, Long parentId, String menuIcon, Integer sortId, Integer menuGrade) {
    this.menuId = menuId;
    this.menuName = menuName;
    this.menuUrl = menuUrl;
    this.show = show;
    this.check = check;
    this.parentId = parentId;
    this.menuIcon = menuIcon;
    this.sortId = sortId;
    this.menuGrade = menuGrade;
}
public String getMenuUrl(){
    return menuUrl;
}


public Integer getSortId(){
    return sortId;
}


public Long getMenuId(){
    return menuId;
}


public Boolean getCheck(){
    return check;
}


public Boolean getShow(){
    return show;
}


public String getMenuIcon(){
    return menuIcon;
}


public String getMenuName(){
    return menuName;
}


public Integer getMenuGrade(){
    return menuGrade;
}


public Long getParentId(){
    return parentId;
}


}