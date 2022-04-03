package cn.gson.oasys.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class Position {

 private  Long id;

 private  String name;

 private  Integer level;

 private  String describtion;

 private  Long deptid;

public Position() {
}public Position(String name, Integer level) {
    this.name = name;
    this.level = level;
}
public Integer getLevel(){
    return level;
}


public String getName(){
    return name;
}


public Long getDeptid(){
    return deptid;
}


public Long getId(){
    return id;
}


public String getDescribtion(){
    return describtion;
}


}