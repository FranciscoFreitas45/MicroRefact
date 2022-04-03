package cn.gson.oasys.model.entity.role;
 import javax.persistence.Column;
public class Rolemenu {

 private  Long menuId;

 private  String menuName;

 private  String menuUrl;

@Column(name = "is_show")
 private  Boolean show;

@Column(name = "is_show")
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
public void setSortId(Integer sortId){
    this.sortId = sortId;
}


public String getMenuUrl(){
    return menuUrl;
}


public void setCheck(Boolean check){
    this.check = check;
}


public Integer getSortId(){
    return sortId;
}


public Long getMenuId(){
    return menuId;
}


public void setMenuIcon(String menuIcon){
    this.menuIcon = menuIcon;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public void setShow(Boolean show){
    this.show = show;
}


public void setMenuId(Long menuId){
    this.menuId = menuId;
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


public void setParentId(Long parentId){
    this.parentId = parentId;
}


public String getMenuName(){
    return menuName;
}


@Override
public String toString(){
    return "Rolemenu [menuId=" + menuId + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", show=" + show + ", check=" + check + ", menuIcon=" + menuIcon + ", sortId=" + sortId + ", menuGrade=" + menuGrade + ", parentId=" + parentId + "]";
}


public Integer getMenuGrade(){
    return menuGrade;
}


public void setMenuGrade(Integer menuGrade){
    this.menuGrade = menuGrade;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


public Long getParentId(){
    return parentId;
}


}