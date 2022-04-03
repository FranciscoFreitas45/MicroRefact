package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("activity_statistics")
public class ActivityStatistics extends Model<ActivityStatistics>{

 private  long serialVersionUID;

@TableField("id")
 private  Integer Id;

@TableField("club_id")
 private  Integer clubId;

@TableField("club_name")
 private  String clubName;

@TableField("common_num")
 private  Integer commonNum;

@TableField("great_num")
 private  Integer greatNum;

 private  Integer total;


public void setTotal(Integer total){
    this.total = total;
}


public void setGreatNum(Integer greatNum){
    this.greatNum = greatNum;
}


public Integer getClubId(){
    return clubId;
}


public void setCommonNum(Integer commonNum){
    this.commonNum = commonNum;
}


public Integer getId(){
    return Id;
}


public Integer getGreatNum(){
    return greatNum;
}


public void setClubId(Integer clubId){
    this.clubId = clubId;
}


public String getClubName(){
    return clubName;
}


public Integer getCommonNum(){
    return commonNum;
}


public void setId(Integer id){
    Id = id;
}


public Integer getTotal(){
    return total;
}


@Override
public Serializable pkVal(){
    return null;
}


@Override
public String toString(){
    return "ActivityStatistics{" + "Id=" + Id + ", clubId=" + clubId + ", clubName='" + clubName + '\'' + ", commonNum=" + commonNum + ", greatNum=" + greatNum + ", total=" + total + '}';
}


public void setClubName(String clubName){
    this.clubName = clubName;
}


}