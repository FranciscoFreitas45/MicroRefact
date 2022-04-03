package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_sysdic")
@org.hibernate.annotations.Proxy(lazy = false)
public class SysDic {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String title;

 private  String code;

 private  String orgi;

 private  String ctype;

 private  String parentid;

 private  String iconstr;

 private  String iconskin;

 private  String description;

 private  String catetype;

 private  String memo;

 private  String creater;

 private  boolean haschild;

 private  boolean discode;

 private  Date createtime;

 private  Date updatetime;

 private  int sortindex;

 private  String dicid;

 private  String menutype;

 private  String rules;

 private  String module;

 private  String url;

 private  String mlevel;

 private  boolean defaultvalue;


public String getIconskin(){
    return iconskin;
}


public String getModule(){
    return module;
}


public boolean isDefaultvalue(){
    return defaultvalue;
}


public String getName(){
    return name;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


public void setCtype(String ctype){
    this.ctype = ctype;
}


public void setCatetype(String catetype){
    this.catetype = catetype;
}


public String getTitle(){
    return title;
}


public String getMlevel(){
    return mlevel;
}


public void setId(String id){
    this.id = id;
}


public String getIconstr(){
    return iconstr;
}


public String getCatetype(){
    return catetype;
}


public void setRules(String rules){
    this.rules = rules;
}


public String getCode(){
    return code;
}


public String getCtype(){
    return ctype;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setTitle(String title){
    this.title = title;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getMemo(){
    return memo;
}


public String getDicid(){
    return dicid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setIconstr(String iconstr){
    this.iconstr = iconstr;
}


public void setMenutype(String menutype){
    this.menutype = menutype;
}


public void setName(String name){
    this.name = name;
}


public boolean isHaschild(){
    return haschild;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public Date getCreatetime(){
    return createtime;
}


public void setMlevel(String mlevel){
    this.mlevel = mlevel;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getRules(){
    return rules;
}


public void setIconskin(String iconskin){
    this.iconskin = iconskin;
}


public String getParentid(){
    return parentid;
}


public boolean isDiscode(){
    return discode;
}


public String getMenutype(){
    return menutype;
}


public void setDiscode(boolean discode){
    this.discode = discode;
}


public void setDefaultvalue(boolean defaultvalue){
    this.defaultvalue = defaultvalue;
}


public void setHaschild(boolean haschild){
    this.haschild = haschild;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getOrgi(){
    return orgi;
}


public void setModule(String module){
    this.module = module;
}


public String toString(){
    return this.name;
}


public int getSortindex(){
    return sortindex;
}


}