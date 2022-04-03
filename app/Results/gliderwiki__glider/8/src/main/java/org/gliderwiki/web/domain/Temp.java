package org.gliderwiki.web.domain;
 import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
import com.google.gson.annotations.Expose;
@Table("TEMP")
public class Temp extends BaseObjectBean{

 private  long serialVersionUID;

@Column(primaryKey = true, autoIncrement = true)
@Expose
 private  Integer id;

@Column(name = "name")
 private  String name;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}