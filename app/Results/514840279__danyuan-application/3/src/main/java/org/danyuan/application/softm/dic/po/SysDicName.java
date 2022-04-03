package org.danyuan.application.softm.dic.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dic_name")
@NamedQuery(name = "SysDicName.findAll", query = "SELECT s FROM SysDicName s")
public class SysDicName extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "button_type")
 private  String buttonType;

@Column(name = "code")
 private  String code;

@Column(name = "name")
 private  String name;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDicName() {
    super();
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setCode(String code){
    this.code = code;
}


public void setButtonType(String buttonType){
    this.buttonType = buttonType;
}


public String getButtonType(){
    return buttonType;
}


public String getCode(){
    return code;
}


}