package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "shows")
public class Shows {

@Id
@Column(length = 1)
 private  int sno;

@Column(length = 10)
 private  String timings;

@Column(length = 10)
 private  String show;


public void setSno(int sno){
    this.sno = sno;
}


public int getSno(){
    return sno;
}


public String getShow(){
    return show;
}


public void setTimings(String timings){
    this.timings = timings;
}


public String getTimings(){
    return timings;
}


public void setShow(String show){
    this.show = show;
}


}