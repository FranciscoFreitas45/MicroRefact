package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.kingen.vo.Comboable;
@Entity
@Table(name = "t_client")
public class Client implements Comboable{

 private  String id;

 private  String unitName;

 private  String addr;

 private  String postCode;

// Constructors
/**
 * default constructor
 */
public Client() {
}/**
 * full constructor
 */
public Client(String unitName, String addr, String postCode) {
    this.unitName = unitName;
    this.addr = addr;
    this.postCode = postCode;
}
@Column(name = "unit_name", length = 50)
public String getUnitName(){
    return this.unitName;
}


@Column(name = "post_code", length = 15)
public String getPostCode(){
    return this.postCode;
}


@Override
@Transient
public String getName(){
    return unitName;
}


public void setAddr(String addr){
    this.addr = addr;
}


public void setPostCode(String postCode){
    this.postCode = postCode;
}


public void setId(String id){
    this.id = id;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


@Column(name = "addr", length = 100)
public String getAddr(){
    return this.addr;
}


@Override
@Transient
public String getCode(){
    return id;
}


public void setUnitName(String unitName){
    this.unitName = unitName;
}


}