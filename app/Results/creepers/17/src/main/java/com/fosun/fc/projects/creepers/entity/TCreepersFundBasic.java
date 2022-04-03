package com.fosun.fc.projects.creepers.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_FUND_BASIC")
@NamedQuery(name = "TCreepersFundBasic.findAll", query = "SELECT t FROM TCreepersFundBasic t")
public class TCreepersFundBasic {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_FUND_BASIC_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_FUND_BASIC")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_FUND_BASIC_ID_GENERATOR")
 private  long id;

@Column(name = "ACCOUNT_DT")
 private  String accountDt;

@Column(name = "ACCOUNT_NO")
 private  String accountNo;

@Column(name = "ACCOUNT_STATUS")
 private  String accountStatus;

@Column(name = "CERTIFICATE_STATUS")
 private  String certificateStatus;

@Column(name = "END_DT")
 private  String endDt;

 private  String mobile;

@Column(name = "MONTHLY_AMOUNT")
 private  BigDecimal monthlyAmount;

 private  String name;

@Column(name = "SUM_AMOUNT")
 private  BigDecimal sumAmount;

 private  String unit;

@Column(name = "LOGIN_NAME")
 private  String loginName;

@Column(name = "PASSWORD")
 private  String password;

public TCreepersFundBasic() {
}
public void setName(String name){
    this.name = name;
}


public void setAccountDt(String accountDt){
    this.accountDt = accountDt;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return this.name;
}


public String getAccountStatus(){
    return this.accountStatus;
}


public long getId(){
    return this.id;
}


public void setMonthlyAmount(BigDecimal monthlyAmount){
    this.monthlyAmount = monthlyAmount;
}


public void setSumAmount(BigDecimal sumAmount){
    this.sumAmount = sumAmount;
}


public void setCertificateStatus(String certificateStatus){
    this.certificateStatus = certificateStatus;
}


public void setAccountNo(String accountNo){
    this.accountNo = accountNo;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setAccountStatus(String accountStatus){
    this.accountStatus = accountStatus;
}


public void setId(long id){
    this.id = id;
}


public String getAccountNo(){
    return this.accountNo;
}


public String getAccountDt(){
    return this.accountDt;
}


public void setEndDt(String endDt){
    this.endDt = endDt;
}


public String getCertificateStatus(){
    return this.certificateStatus;
}


public String getLoginName(){
    return loginName;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getPassword(){
    return password;
}


public String getEndDt(){
    return this.endDt;
}


public BigDecimal getMonthlyAmount(){
    return this.monthlyAmount;
}


public BigDecimal getSumAmount(){
    return sumAmount;
}


public String getMobile(){
    return this.mobile;
}


public String getUnit(){
    return this.unit;
}


}