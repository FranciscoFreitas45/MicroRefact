package com.ipe.module.core.entity;
 import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence;
import java.sql.Timestamp;
import java.util.Set;
@Table(name = "t_cor_menu", schema = "", catalog = "db_work")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({ "parent" })
public class Menu extends IDEntity{

 private  String menuName;

 private  String menuType;

 private  String menuUrl;

 private  String menuStyle;

 private  Integer sno;

 private  String enabled;

 private  String remark;

 private  Timestamp createdDate;

 private  Timestamp updatedDate;

 private  Resource resource;

 private  Set<Menu> rows;

 private  Menu parent;

 private  boolean leaf;

 private  boolean expanded;

 private  Boolean checked;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "resource_id", referencedColumnName = "id_")
@NotFound(action = NotFoundAction.IGNORE)
public Resource getResource(){
    return resource;
}


public void setExpanded(boolean expanded){
    this.expanded = expanded;
}


public void setMenuStyle(String menuStyle){
    this.menuStyle = menuStyle;
}


@Column(name = "updated_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
@Basic
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Timestamp getUpdatedDate(){
    return updatedDate;
}


@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent", fetch = FetchType.LAZY)
@OrderBy(value = "sno")
@JSONField(name = "menu")
public Set<Menu> getRows(){
    return rows;
}


@Transient
public boolean isLeaf(){
    return leaf;
}


public void setRows(Set<Menu> rows){
    this.rows = rows;
    if (rows == null || rows.isEmpty()) {
        this.leaf = true;
    }
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "enabled_", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
@Basic
public String getEnabled(){
    return enabled;
}


@Column(name = "remark_", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
@Basic
public String getRemark(){
    return remark;
}


@Column(name = "menu_style", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
@Basic
public String getMenuStyle(){
    return menuStyle;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


@Column(name = "menu_name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
@JSONField(name = "text")
public String getMenuName(){
    return menuName;
}


@Column(name = "created_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
@Basic
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Timestamp getCreatedDate(){
    return createdDate;
}


public void setParent(Menu parent){
    this.parent = parent;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


@Transient
public boolean isExpanded(){
    return expanded;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "pid")
public Menu getParent(){
    return parent;
}


@Column(name = "menu_url", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
public String getMenuUrl(){
    return menuUrl;
}


public void setLeaf(boolean leaf){
    this.leaf = leaf;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


@Transient
public Boolean isChecked(){
    return checked;
}


public void setMenuType(String menuType){
    this.menuType = menuType;
}


public void setResource(Resource resource){
    this.resource = resource;
}


public void setSno(Integer sno){
    this.sno = sno;
}


public void setEnabled(String enabled){
    this.enabled = enabled;
}


@Column(name = "menu_type", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
public String getMenuType(){
    return menuType;
}


public void setCreatedDate(Timestamp createdDate){
    this.createdDate = createdDate;
}


@Column(name = "sno_", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
@Basic
public Integer getSno(){
    return sno;
}


public void setUpdatedDate(Timestamp updatedDate){
    this.updatedDate = updatedDate;
}


}