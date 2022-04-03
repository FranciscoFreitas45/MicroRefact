package com.gp.cricket.mapobject;
 public class PlayerMatchBallerRecordExport {

 private  String tournamentName;

 private  String matchtype;

 private  String date;

 private  String overs;

 private  String numRunAgainst;

 private  String wicket;

 private  String hatTrick;

 private  String ballingPoint;

 private  String numOfWides;

 private  String numOfNo;

 private  String avgSpeed;

public PlayerMatchBallerRecordExport() {
    super();
// TODO Auto-generated constructor stub
}public PlayerMatchBallerRecordExport(String tournamentName, String matchtype, String date, String overs, String numRunAgainst, String wicket, String hatTrick, String ballingPoint, String numOfWides, String numOfNo, String avgSpeed) {
    super();
    this.tournamentName = tournamentName;
    this.matchtype = matchtype;
    this.date = date;
    this.overs = overs;
    this.numRunAgainst = numRunAgainst;
    this.wicket = wicket;
    this.hatTrick = hatTrick;
    this.ballingPoint = ballingPoint;
    this.numOfWides = numOfWides;
    this.numOfNo = numOfNo;
    this.avgSpeed = avgSpeed;
}
public String getWicket(){
    return wicket;
}


public void setTournamentName(String tournamentName){
    this.tournamentName = tournamentName;
}


public void setOvers(String overs){
    this.overs = overs;
}


public void setNumOfNo(String numOfNo){
    this.numOfNo = numOfNo;
}


public void setBallingPoint(String ballingPoint){
    this.ballingPoint = ballingPoint;
}


public void setHatTrick(String hatTrick){
    this.hatTrick = hatTrick;
}


public String getNumRunAgainst(){
    return numRunAgainst;
}


public String getNumOfNo(){
    return numOfNo;
}


public String getNumOfWides(){
    return numOfWides;
}


public void setMatchtype(String matchtype){
    this.matchtype = matchtype;
}


public void setNumOfWides(String numOfWides){
    this.numOfWides = numOfWides;
}


public String getHatTrick(){
    return hatTrick;
}


public String getTournamentName(){
    return tournamentName;
}


public String getOvers(){
    return overs;
}


public void setNumRunAgainst(String numRunAgainst){
    this.numRunAgainst = numRunAgainst;
}


public void setWicket(String wicket){
    this.wicket = wicket;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


public void setAvgSpeed(String avgSpeed){
    this.avgSpeed = avgSpeed;
}


@Override
public String toString(){
    return "PlayerMatchBallerRecordExport [tournamentName=" + tournamentName + ", matchtype=" + matchtype + ", date=" + date + ", overs=" + overs + ", numRunAgainst=" + numRunAgainst + ", wicket=" + wicket + ", hatTrick=" + hatTrick + ", ballingPoint=" + ballingPoint + ", numOfWides=" + numOfWides + ", numOfNo=" + numOfNo + ", avgSpeed=" + avgSpeed + "]";
}


public String getMatchtype(){
    return matchtype;
}


public String getBallingPoint(){
    return ballingPoint;
}


public String getAvgSpeed(){
    return avgSpeed;
}


}