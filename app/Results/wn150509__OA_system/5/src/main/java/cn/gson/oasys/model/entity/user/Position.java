package cn.gson.oasys.model.entity.user;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "aoa_position")
public class Position {

@Id
@Column(name = "position_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(unique = true)
 private  String name;

 private  Integer level;

 private  String describtion;

 private  Long deptid;

public Position() {
}public Position(String name, Integer level) {
    this.name = name;
    this.level = level;
}
public void setName(String name){
    this.name = name;
}


public Integer getLevel(){
    return level;
}


public String getName(){
    return name;
}


public void setDeptid(Long deptid){
    this.deptid = deptid;
}


public void setDescribtion(String describtion){
    this.describtion = describtion;
}


public void setId(Long id){
    this.id = id;
}


public Long getDeptid(){
    return deptid;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "Position [id=" + id + ", name=" + name + ", level=" + level + ", describtion=" + describtion + ", deptid=" + deptid + "]";
}


public String getDescribtion(){
    return describtion;
}


public void setLevel(Integer level){
    this.level = level;
}


}