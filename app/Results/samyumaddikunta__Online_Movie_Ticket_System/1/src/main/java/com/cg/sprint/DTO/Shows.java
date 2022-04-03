package com.cg.sprint.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
public class Shows {

 private  int sno;

 private  String timings;

 private  String show;


public int getSno(){
    return sno;
}


public String getShow(){
    return show;
}


public String getTimings(){
    return timings;
}


}