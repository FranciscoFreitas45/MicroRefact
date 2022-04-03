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
@Table(name = "t_service_lv")
public class ServiceLv implements Comboable{

 private  String id;

 private  String name;

 private  String type;

 private  Integer servBeiginHour;

 private  Integer servEndHour;

 private  Integer highPriRespHour;

 private  Integer highPriSolveHour;

 private  Integer midPriRespHour;

 private  Integer midPriSolveHour;

 private  Integer lowPriRespHour;

 private  Integer lowPriSolveHour;

// Constructors
/**
 * default constructor
 */
public ServiceLv() {
}/**
 * full constructor
 */
public ServiceLv(String name, String type, Integer servBeiginHour, Integer servEndHour, Integer highPriRespHour, Integer highPriSolveHour, Integer midPriRespHour, Integer midPriSolveHour, Integer lowPriRespHour, Integer lowPriSolveHour) {
    this.name = name;
    this.type = type;
    this.servBeiginHour = servBeiginHour;
    this.servEndHour = servEndHour;
    this.highPriRespHour = highPriRespHour;
    this.highPriSolveHour = highPriSolveHour;
    this.midPriRespHour = midPriRespHour;
    this.midPriSolveHour = midPriSolveHour;
    this.lowPriRespHour = lowPriRespHour;
    this.lowPriSolveHour = lowPriSolveHour;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "mid_pri_solve_hour")
public Integer getMidPriSolveHour(){
    return this.midPriSolveHour;
}


@Column(name = "name", length = 50)
public String getName(){
    return this.name;
}


public void setHighPriRespHour(Integer highPriRespHour){
    this.highPriRespHour = highPriRespHour;
}


public void setMidPriSolveHour(Integer midPriSolveHour){
    this.midPriSolveHour = midPriSolveHour;
}


@Column(name = "serv_end_hour")
public Integer getServEndHour(){
    return this.servEndHour;
}


public void setHighPriSolveHour(Integer highPriSolveHour){
    this.highPriSolveHour = highPriSolveHour;
}


public void setMidPriRespHour(Integer midPriRespHour){
    this.midPriRespHour = midPriRespHour;
}


public void setLowPriRespHour(Integer lowPriRespHour){
    this.lowPriRespHour = lowPriRespHour;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


public void setType(String type){
    this.type = type;
}


@Column(name = "serv_beigin_hour")
public Integer getServBeiginHour(){
    return this.servBeiginHour;
}


@Column(name = "mid_pri_resp_hour")
public Integer getMidPriRespHour(){
    return this.midPriRespHour;
}


public void setServBeiginHour(Integer servBeiginHour){
    this.servBeiginHour = servBeiginHour;
}


@Column(name = "type", length = 10)
public String getType(){
    return this.type;
}


@Column(name = "low_pri_resp_hour")
public Integer getLowPriRespHour(){
    return this.lowPriRespHour;
}


public void setId(String id){
    this.id = id;
}


public void setLowPriSolveHour(Integer lowPriSolveHour){
    this.lowPriSolveHour = lowPriSolveHour;
}


@Column(name = "low_pri_solve_hour")
public Integer getLowPriSolveHour(){
    return this.lowPriSolveHour;
}


public void setServEndHour(Integer servEndHour){
    this.servEndHour = servEndHour;
}


@Override
@Transient
public String getCode(){
    return id;
}


@Column(name = "high_pri_resp_hour")
public Integer getHighPriRespHour(){
    return this.highPriRespHour;
}


@Column(name = "high_pri_solve_hour")
public Integer getHighPriSolveHour(){
    return this.highPriSolveHour;
}


}