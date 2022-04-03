package cn.gson.oasys.DTO;
 import javax.persistence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;
public class SystemMenu {

 private  Long menuId;

 private  Long parentId;

 private  String menuName;

 private  String menuUrl;

 private  String menuIcon;

 private  Integer sortId;

 private  Boolean show;

 private  Integer menuGrade;

public SystemMenu() {
}public SystemMenu(Long menuId, Long parentId, String menuName, String menuUrl, String menuIcon, Integer sortId, Boolean show, Integer menuGrade) {
    super();
    System.out.println("parentId" + parentId);
    if (parentId != null) {
        this.parentId = parentId;
    }
    this.menuId = menuId;
    this.menuName = menuName;
    this.menuUrl = menuUrl;
    this.menuIcon = menuIcon;
    this.sortId = sortId;
    this.show = show;
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


public String getMenuIcon(){
    return menuIcon;
}


public Boolean getShow(){
    return show;
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