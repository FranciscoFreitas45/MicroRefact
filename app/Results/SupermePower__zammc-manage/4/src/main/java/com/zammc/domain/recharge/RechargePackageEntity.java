package com.zammc.domain.recharge;
 import javax.persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@Table(name = "recharge_package")
public class RechargePackageEntity {

 private  long packageId;

 private  String packageName;

 private  BigDecimal rechargeMoney;

 private  BigDecimal payMoney;

 private  Timestamp createTime;

 private  Timestamp updateTime;

 private  int version;

 private  byte dataStatus;


@Basic
@Column(name = "version")
public int getVersion(){
    return version;
}


@Basic
@Column(name = "create_time")
public Timestamp getCreateTime(){
    return createTime;
}


public void setPayMoney(BigDecimal payMoney){
    this.payMoney = payMoney;
}


@Basic
@Column(name = "data_status")
public byte getDataStatus(){
    return dataStatus;
}


public void setDataStatus(byte dataStatus){
    this.dataStatus = dataStatus;
}


public void setVersion(int version){
    this.version = version;
}


public void setPackageName(String packageName){
    this.packageName = packageName;
}


public void setCreateTime(Timestamp createTime){
    this.createTime = createTime;
}


@Basic
@Column(name = "pay_money")
public BigDecimal getPayMoney(){
    return payMoney;
}


@Basic
@Column(name = "update_time")
public Timestamp getUpdateTime(){
    return updateTime;
}


public void setPackageId(long packageId){
    this.packageId = packageId;
}


@Basic
@Column(name = "recharge_money")
public BigDecimal getRechargeMoney(){
    return rechargeMoney;
}


public void setUpdateTime(Timestamp updateTime){
    this.updateTime = updateTime;
}


@Override
public String toString(){
    return "RechargePackageEntity{" + "packageId=" + packageId + ", packageName='" + packageName + '\'' + ", rechargeMoney=" + rechargeMoney + ", payMoney=" + payMoney + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", dataStatus=" + dataStatus + '}';
}


@Basic
@Column(name = "package_name")
public String getPackageName(){
    return packageName;
}


public void setRechargeMoney(BigDecimal rechargeMoney){
    this.rechargeMoney = rechargeMoney;
}


@Id
@Column(name = "package_id")
public long getPackageId(){
    return packageId;
}


}