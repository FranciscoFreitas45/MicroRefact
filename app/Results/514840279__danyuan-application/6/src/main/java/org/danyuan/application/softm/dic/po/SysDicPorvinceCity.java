package org.danyuan.application.softm.dic.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dic_porvince_city")
@NamedQuery(name = "SysDicPorvinceCity.findAll", query = "SELECT s FROM SysDicPorvinceCity s")
public class SysDicPorvinceCity extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "province")
 private  String province;

@Column(name = "city")
 private  String city;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDicPorvinceCity() {
    super();
}
public void setProvince(String province){
    this.province = province;
}


public void setCity(String city){
    this.city = city;
}


public String getProvince(){
    return province;
}


public String getCity(){
    return city;
}


}