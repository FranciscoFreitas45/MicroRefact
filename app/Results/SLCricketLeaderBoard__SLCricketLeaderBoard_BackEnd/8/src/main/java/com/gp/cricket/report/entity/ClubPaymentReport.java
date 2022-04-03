package com.gp.cricket.report.entity;
 public class ClubPaymentReport {

 private  Integer clubId;

 private  String clubName;

 private  String manager;

 private  Double amount;

public ClubPaymentReport() {
    super();
// TODO Auto-generated constructor stub
}public ClubPaymentReport(Integer clubId, String clubName, String manager, Double amount) {
    super();
    this.clubId = clubId;
    this.clubName = clubName;
    this.manager = manager;
    this.amount = amount;
}
public String getManager(){
    return manager;
}


public void setClubId(Integer clubId){
    this.clubId = clubId;
}


public String getClubName(){
    return clubName;
}


public Integer getClubId(){
    return clubId;
}


@Override
public String toString(){
    return "ClubPaymentReport [clubId=" + clubId + ", clubName=" + clubName + ", manager=" + manager + ", amount=" + amount + "]";
}


public void setClubName(String clubName){
    this.clubName = clubName;
}


public void setManager(String manager){
    this.manager = manager;
}


public void setAmount(Double amount){
    this.amount = amount;
}


public Double getAmount(){
    return amount;
}


}