package com.circle.pojo.commission;
 public class ExtractApp {

 private  int id;

 private  int userId;

 private  int commissionId;

 private  double extractCommission;

 private  String alipayAccount;

 private  String alipayName;

 private  int status;

 private  String createTime;


public String getCreateTime(){
    return createTime;
}


public String getAlipayAccount(){
    return alipayAccount;
}


public int getCommissionId(){
    return commissionId;
}


public void setAlipayAccount(String alipayAccount){
    this.alipayAccount = alipayAccount;
}


public void setCommissionId(int commissionId){
    this.commissionId = commissionId;
}


public int getId(){
    return id;
}


public void setCreateTime(String createTime){
    this.createTime = createTime;
}


public int getStatus(){
    return status;
}


public void setStatus(int status){
    this.status = status;
}


public void setExtractCommission(double extractCommission){
    this.extractCommission = extractCommission;
}


public double getExtractCommission(){
    return extractCommission;
}


public void setAlipayName(String alipayName){
    this.alipayName = alipayName;
}


public String getAlipayName(){
    return alipayName;
}


public void setId(int id){
    this.id = id;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}