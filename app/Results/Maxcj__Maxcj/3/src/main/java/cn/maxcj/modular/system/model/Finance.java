package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
@TableName("sys_finance")
public class Finance extends Model<Finance>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Integer id;

 private  Integer deptid;

 private  Integer category;

 private  Integer activityid;

 private  Double money;

 private  Double balance;

 private  Date costtime;

 private  Integer agree;

 private  Date agreetime;


public void setMoney(Double money){
    this.money = money;
}


public Integer getCategory(){
    return category;
}


public Date getCosttime(){
    return costtime;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


public void setActivityid(Integer activityid){
    this.activityid = activityid;
}


public void setCosttime(Date costtime){
    this.costtime = costtime;
}


public Double getBalance(){
    return balance;
}


public Integer getAgree(){
    return agree;
}


public void setCategory(Integer category){
    this.category = category;
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
}


public void setId(Integer id){
    this.id = id;
}


public void setAgree(Integer agree){
    this.agree = agree;
}


public Date getAgreetime(){
    return agreetime;
}


public void setAgreetime(Date agreetime){
    this.agreetime = agreetime;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Finance{" + "id=" + id + ", deptid=" + deptid + ", category=" + category + ", activityid=" + activityid + ", money=" + money + ", balance=" + balance + ", costtime=" + costtime + ", agree=" + agree + ", agreetime=" + agreetime + '}';
}


public Double getMoney(){
    return money;
}


public Integer getActivityid(){
    return activityid;
}


public void setBalance(Double balance){
    this.balance = balance;
}


}