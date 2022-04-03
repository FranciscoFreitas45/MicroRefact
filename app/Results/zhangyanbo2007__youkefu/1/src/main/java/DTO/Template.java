package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class Template {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String userid;

 private  String groupid;

 private  String description;

 private  String templettitle;

 private  String templettext;

 private  String templettype;

 private  Date createtime;

 private  String orgi;

 private  String iconstr;

 private  String memo;

 private  String typeid;

 private  int layoutcols;

 private  String datatype;

 private  String charttype;


public String getName(){
    return name;
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


public void setTypeid(String typeid){
    this.typeid = typeid;
}


public String getDescription(){
    return description;
}


public String getDatatype(){
    return datatype;
}


@Transient
public String getTitle(){
    return this.groupid;
}


public Date getCreatetime(){
    return createtime;
}


public String getTemplettext(){
    return templettext;
}


public void setId(String id){
    this.id = id;
}


public String getIconstr(){
    return iconstr;
}


public String getTemplettitle(){
    return templettitle;
}


public String getCode(){
    return code;
}


public String getUserid(){
    return userid;
}


public String getCharttype(){
    return charttype;
}


public String getTemplettype(){
    return templettype;
}


public void setDatatype(String datatype){
    this.datatype = datatype;
}


public void setCode(String code){
    this.code = code;
}


public String getTypeid(){
    return typeid;
}


public String getGroupid(){
    return groupid;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public int getLayoutcols(){
    return layoutcols;
}


public String getOrgi(){
    return orgi;
}


}