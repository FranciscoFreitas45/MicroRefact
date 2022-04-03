package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
public class ActivityStatistics extends Model<ActivityStatistics>{

 private  long serialVersionUID;

 private  Integer Id;

 private  Integer clubId;

 private  String clubName;

 private  Integer commonNum;

 private  Integer greatNum;

 private  Integer total;


public void setGreatNum(Integer greatNum){
    this.greatNum = greatNum;
}


public Integer getClubId(){
    return clubId;
}


public Integer getId(){
    return Id;
}


public Integer getGreatNum(){
    return greatNum;
}


public String getClubName(){
    return clubName;
}


public Integer getCommonNum(){
    return commonNum;
}


public Integer getTotal(){
    return total;
}


@Override
public String toString(){
    return "ActivityStatistics{" + "Id=" + Id + ", clubId=" + clubId + ", clubName='" + clubName + '\'' + ", commonNum=" + commonNum + ", greatNum=" + greatNum + ", total=" + total + '}';
}


}