package org.danyuan.application.softm.sysmenu.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
import org.springframework.data.annotation.Transient;
@Entity
@Table(name = "sys_menu_info")
@NamedQuery(name = "SysMenuInfo.findAll", query = "SELECT s FROM SysMenuInfo s")
public class SysMenuInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "uri")
 private  String uri;

@Column(name = "checked", precision = 3)
 private  Integer checked;

@Column(name = "system_id")
 private  String systemId;

@Column(name = "icon")
 private  String icon;

@Column(name = "sort", precision = 10)
 private  Integer sort;

@Column(name = "name")
 private  String name;

@Column(name = "parents_id")
 private  String parentsId;

@Column(name = "home_page", precision = 3)
 private  Boolean homePage;

@Column(name = "type")
 private  String type;

@Column(name = "icon_skin")
 private  String iconSkin;

@Transient
 private  String moveType;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysMenuInfo() {
    super();
}
public void setName(String name){
    this.name = name;
}


public String getMoveType(){
    return moveType;
}


public String getSystemId(){
    return systemId;
}


public String getName(){
    return name;
}


public void setHomePage(Boolean homePage){
    this.homePage = homePage;
}


public void setUri(String uri){
    this.uri = uri;
}


public void setParentsId(String parentsId){
    this.parentsId = parentsId;
}


public void setType(String type){
    this.type = type;
}


public void setIconSkin(String iconSkin){
    this.iconSkin = iconSkin;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public String getIconSkin(){
    return iconSkin;
}


public void setMoveType(String moveType){
    this.moveType = moveType;
}


public String getType(){
    return type;
}


public void setSystemId(String systemId){
    this.systemId = systemId;
}


public void setChecked(Integer checked){
    this.checked = checked;
}


public Boolean getHomePage(){
    return homePage;
}


public String getParentsId(){
    return parentsId;
}


@Override
public String toString(){
    return "SysMenuInfo [uuid=" + uuid + ", systemId=" + systemId + ", parentsId=" + parentsId + ", name=" + name + ", uri=" + uri + ", icon=" + icon + ", sort=" + sort + ", discription=" + discription + ", insertDatetime=" + super.createTime + ", insertUser=" + super.createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", deleteFlag=" + deleteFlag + ", type=" + type + ", iconSkin=" + iconSkin + ", checked=" + checked + ", homePage=" + homePage + ", moveType=" + moveType + "]";
}


public Integer getChecked(){
    return checked;
}


public String getUri(){
    return uri;
}


}