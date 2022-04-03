package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_sale_count")
@org.hibernate.annotations.Proxy(lazy = false)
public class CalloutSaleCount {

 private  long serialVersionUID;

 private  String id;

 private  String dataid;

 private  String type;

 private  int namenum;

 private  int notcall;

 private  int callsuccess;

 private  int callfaild;

 private  int aptrue;

 private  int apfalse;

 private  Date createtime;

 private  String orgi;


public int getCallsuccess(){
    return callsuccess;
}


public void setCallfaild(int callfaild){
    this.callfaild = callfaild;
}


public void setNotcall(int notcall){
    this.notcall = notcall;
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


public void setType(String type){
    this.type = type;
}


public void setAptrue(int aptrue){
    this.aptrue = aptrue;
}


public int getApfalse(){
    return apfalse;
}


public int getNotcall(){
    return notcall;
}


public int getAptrue(){
    return aptrue;
}


public void setCallsuccess(int callsuccess){
    this.callsuccess = callsuccess;
}


public String getDataid(){
    return dataid;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public int getNamenum(){
    return namenum;
}


public void setApfalse(int apfalse){
    this.apfalse = apfalse;
}


public void setNamenum(int namenum){
    this.namenum = namenum;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public int getCallfaild(){
    return callfaild;
}


}