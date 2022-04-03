package cn.gson.oasys.model.entity.system;
 import javax.persistence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;
@Entity
@Table(name = "aoa_sys_menu")
public class SystemMenu {

@Id
@Column(name = "menu_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long menuId;

@Column(name = "parent_id")
 private  Long parentId;

@Column(name = "menu_name")
@NotEmpty(message = "菜单名字不能为空")
 private  String menuName;

@Column(name = "menu_url")
@NotEmpty(message = "{sysMenu.menuUrl.NotNull}")
 private  String menuUrl;

@Column(name = "menu_icon")
 private  String menuIcon;

@Column(name = "sort_id")
 private  Integer sortId;

@Column(name = "is_show")
 private  Boolean show;

@Column(name = "menu_grade")
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
public void setSortId(Integer sortId){
    this.sortId = sortId;
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


public String getMenuIcon(){
    return menuIcon;
}


public Boolean getShow(){
    return show;
}


public void setParentId(Long parentId){
    this.parentId = parentId;
}


public String getMenuName(){
    return menuName;
}


@Override
public String toString(){
    return "SystemMenu [MenuId=" + menuId + ", parentId=" + parentId + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", menuIcon=" + menuIcon + ", sortId=" + sortId + ", show=" + show + ", menuGrade=" + menuGrade + "]";
}


public Integer getMenuGrade(){
    return menuGrade;
}


public void setMenuGrade(Integer menuGrade){
    this.menuGrade = menuGrade;
}


public Long getParentId(){
    return parentId;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


}