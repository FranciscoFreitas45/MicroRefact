package cn.gson.oasys.model.entity.process;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Interface.User;
import cn.gson.oasys.Interface.User;
@Table
@Entity(name = "aoa_bursement")
public class Bursement {

@Id
@Column(name = "bursement_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long bursementId;

@OneToOne()
@JoinColumn(name = "user_name")
 private  User usermoney;

 private  String name;

@Column(name = "type_id")
 private  Long typeId;

@OneToOne
@JoinColumn(name = "operation_name")
 private  User operation;

 private  Date burseTime;

 private  Integer allinvoices;

@Column(name = "manager_advice")
 private  String managerAdvice;

@Column(name = "financial_advice")
 private  String financialAdvice;

@Column(name = "all_money")
 private  Double allMoney;

@Transient
 private  String username;

@Transient
 private  String namemoney;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "burs", orphanRemoval = true)
 private List<DetailsBurse> details;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "pro_id")
 private  ProcessList proId;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Date getBurseTime(){
    return burseTime;
}


public Long getTypeId(){
    return typeId;
}


public String getUsername(){
    return username;
}


public ProcessList getProId(){
    return proId;
}


public void setUsermoney(User usermoney){
    this.usermoney = usermoney;
}


public void setFinancialAdvice(String financialAdvice){
    this.financialAdvice = financialAdvice;
}


public String getManagerAdvice(){
    return managerAdvice;
}


public void setDetails(List<DetailsBurse> details){
    this.details = details;
}


public Long getBursementId(){
    return bursementId;
}


public Integer getAllinvoices(){
    return allinvoices;
}


public void setNamemoney(String namemoney){
    this.namemoney = namemoney;
}


public Double getAllMoney(){
    return allMoney;
}


public String getFinancialAdvice(){
    return financialAdvice;
}


public void setAllMoney(Double allMoney){
    this.allMoney = allMoney;
}


public String getNamemoney(){
    return namemoney;
}


public void setUsername(String username){
    this.username = username;
}


public List<DetailsBurse> getDetails(){
    return details;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public void setBurseTime(Date burseTime){
    this.burseTime = burseTime;
}


public void setProId(ProcessList proId){
    this.proId = proId;
}


public User getUsermoney(){
    return usermoney;
}


public void setManagerAdvice(String managerAdvice){
    this.managerAdvice = managerAdvice;
}


public void setAllinvoices(Integer allinvoices){
    this.allinvoices = allinvoices;
}


public User getOperation(){
    return operation;
}


public void setOperation(User operation){
    this.operation = operation;
}


public void setBursementId(Long bursementId){
    this.bursementId = bursementId;
}


@Override
public String toString(){
    return "Bursement [bursementId=" + bursementId + ", name=" + name + ", typeId=" + typeId + ", burseTime=" + burseTime + ", allinvoices=" + allinvoices + ", managerAdvice=" + managerAdvice + ", namemoney=" + namemoney + ", financialAdvice=" + financialAdvice + ", allMoney=" + allMoney + ", username=" + username + "]";
}


}