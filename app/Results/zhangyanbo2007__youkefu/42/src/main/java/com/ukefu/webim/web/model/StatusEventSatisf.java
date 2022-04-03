package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_callcenter_event")
@Proxy(lazy = false)
public class StatusEventSatisf implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  boolean satisf;

 private  String satisfaction;

 private  Date satisfdate;


public String getSatisfaction(){
    return satisfaction;
}


public void setSatisfaction(String satisfaction){
    this.satisfaction = satisfaction;
}


public Date getSatisfdate(){
    return satisfdate;
}


public boolean isSatisf(){
    return satisf;
}


public void setSatisfdate(Date satisfdate){
    this.satisfdate = satisfdate;
}


public void setId(String id){
    this.id = id;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setSatisf(boolean satisf){
    this.satisf = satisf;
}


}