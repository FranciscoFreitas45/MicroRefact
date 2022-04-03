package org.danyuan.application.softm.dic.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dic_key_list")
@NamedQuery(name = "SysDicKeyList.findAll", query = "SELECT s FROM SysDicKeyList s")
public class SysDicKeyList extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "key_order", precision = 10)
 private  Integer keyOrder;

@Column(name = "keyword")
 private  String keyword;

@Column(name = "key_value")
 private  String keyValue;

@Column(name = "name_uuid")
 private  String nameUuid;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDicKeyList() {
    super();
}
public void setNameUuid(String nameUuid){
    this.nameUuid = nameUuid;
}


public String getKeyword(){
    return keyword;
}


public Integer getKeyOrder(){
    return keyOrder;
}


public String getNameUuid(){
    return nameUuid;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public String getKeyValue(){
    return keyValue;
}


public void setKeyValue(String keyValue){
    this.keyValue = keyValue;
}


public void setKeyOrder(Integer keyOrder){
    this.keyOrder = keyOrder;
}


}