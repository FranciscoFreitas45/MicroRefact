package com.ipe.module.core.entity;
 import com.ipe.common.entity.IDEntity;
import javax.persistence;
@Entity
@Table(name = "t_sys_shortcut", schema = "", catalog = "db_work")
public class Shortcut extends IDEntity{

 private  String shortcutName;

 private  String shortcutCode;

 private  String enabled;

 private  String shortcutDesc;

 private  String shortcutClass;


public void setShortcutName(String shortcutName){
    this.shortcutName = shortcutName;
}


public void setEnabled(String enabled){
    this.enabled = enabled;
}


@Basic
@Column(name = "shortcut_class", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
public String getShortcutClass(){
    return shortcutClass;
}


@Basic
@Column(name = "enabled_", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
public String getEnabled(){
    return enabled;
}


public void setShortcutDesc(String shortcutDesc){
    this.shortcutDesc = shortcutDesc;
}


public void setShortcutCode(String shortcutCode){
    this.shortcutCode = shortcutCode;
}


@Basic
@Column(name = "shortcut_desc", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
public String getShortcutDesc(){
    return shortcutDesc;
}


@Basic
@Column(name = "shortcut_code", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
public String getShortcutCode(){
    return shortcutCode;
}


@Basic
@Column(name = "shortcut_name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
public String getShortcutName(){
    return shortcutName;
}


public void setShortcutClass(String shortcutClass){
    this.shortcutClass = shortcutClass;
}


}