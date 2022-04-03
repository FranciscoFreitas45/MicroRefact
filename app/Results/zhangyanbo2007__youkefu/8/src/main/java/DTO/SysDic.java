package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
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


public String getName(){
    return name;
}


public void setCtype(String ctype){
    this.ctype = ctype;
}


public String getTitle(){
    return title;
}


public String getMlevel(){
    return mlevel;
}


public String getIconstr(){
    return iconstr;
}


public String getCatetype(){
    return catetype;
}


public String getCode(){
    return code;
}


public String getCtype(){
    return ctype;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
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


public void setIconstr(String iconstr){
    this.iconstr = iconstr;
}


public void setName(String name){
    this.name = name;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getDescription(){
    return description;
}


public Date getCreatetime(){
    return createtime;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public String getCreater(){
    return creater;
}


public String getRules(){
    return rules;
}


public String getParentid(){
    return parentid;
}


public String getMenutype(){
    return menutype;
}


public void setDefaultvalue(boolean defaultvalue){
    this.defaultvalue = defaultvalue;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getOrgi(){
    return orgi;
}


public String toString(){
    return this.name;
}


public int getSortindex(){
    return sortindex;
}


}