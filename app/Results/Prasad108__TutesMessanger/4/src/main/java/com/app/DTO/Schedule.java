package com.app.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
public class Schedule {

 private  Integer id;

 private  Division division;

 private  String string;

public Schedule() {
}public Schedule(Division division, String string) {
    this.division = division;
    this.string = string;
}
public void setDivision(Division division){
    this.division = division;
}


public void setString(String string){
    this.string = string;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "division", unique = true)
public Division getDivision(){
    return this.division;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Override
public String toString(){
    return "Schedule [id=" + id + ", string=" + string + "]";
}


@Column(name = "string", length = 1000)
public String getString(){
    return this.string;
}


}