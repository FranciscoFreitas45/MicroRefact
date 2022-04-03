package cn.maxcj.modular.system.model;
 import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("sys_clubinfo")
public class Clubinfo extends Model<Clubinfo>{

 private  long serialVersionUID;

 private  Integer id;

 private  Integer deptid;

@TableField("club_category")
 private  Integer clubCategory;

@TableField("culb_create_time")
 private  Date culbCreateTime;

@TableField("club_number")
 private  Integer clubNumber;

@TableField("club_infomation")
 private  String clubInfomation;


public Integer getClubNumber(){
    return clubNumber;
}


public void setClubInfomation(String clubInfomation){
    this.clubInfomation = clubInfomation;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


public Integer getClubCategory(){
    return clubCategory;
}


public Date getCulbCreateTime(){
    return culbCreateTime;
}


public String getClubInfomation(){
    return clubInfomation;
}


public void setCulbCreateTime(Date culbCreateTime){
    this.culbCreateTime = culbCreateTime;
}


public void setClubNumber(Integer clubNumber){
    this.clubNumber = clubNumber;
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
}


public void setId(Integer id){
    this.id = id;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Clubinfo{" + ", id=" + id + ", deptid=" + deptid + ", clubCategory=" + clubCategory + ", culbCreateTime=" + culbCreateTime + ", clubNumber=" + clubNumber + ", clubInfomation=" + clubInfomation + "}";
}


public void setClubCategory(Integer clubCategory){
    this.clubCategory = clubCategory;
}


}