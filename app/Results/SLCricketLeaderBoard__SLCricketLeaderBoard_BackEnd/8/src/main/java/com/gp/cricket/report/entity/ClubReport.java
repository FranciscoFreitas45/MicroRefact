package com.gp.cricket.report.entity;
 import javax.persistence.Entity;
public class ClubReport {

 private  Integer clubId;

 private  String clubName;

 private  String manager;

 private  Integer numPlayer;

 private  String regDate;

public ClubReport() {
    super();
// TODO Auto-generated constructor stub
}public ClubReport(Integer clubId, String clubName, String manager, Integer numPlayer, String regDate) {
    super();
    this.clubId = clubId;
    this.clubName = clubName;
    this.manager = manager;
    this.numPlayer = numPlayer;
    this.regDate = regDate;
}
public String getManager(){
    return manager;
}


public String getRegDate(){
    return regDate;
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


public Integer getNumPlayer(){
    return numPlayer;
}


public void setNumPlayer(Integer numPlayer){
    this.numPlayer = numPlayer;
}


public void setRegDate(String regDate){
    this.regDate = regDate;
}


@Override
public String toString(){
    return "ClubReport [clubId=" + clubId + ", clubName=" + clubName + ", manager=" + manager + ", numPlayer=" + numPlayer + ", regDate=" + regDate + "]";
}


public void setClubName(String clubName){
    this.clubName = clubName;
}


public void setManager(String manager){
    this.manager = manager;
}


}