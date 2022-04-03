package com.gp.cricket.report.entity;
 public class TournamentReport {

 private  Integer num;

 private  String tournamentName;

 private  String startDate;

 private  String finishDate;

public TournamentReport() {
    super();
// TODO Auto-generated constructor stub
}public TournamentReport(Integer num, String tournamentName, String startDate, String finishDate) {
    super();
    this.num = num;
    this.tournamentName = tournamentName;
    this.startDate = startDate;
    this.finishDate = finishDate;
}
public String getStartDate(){
    return startDate;
}


public void setStartDate(String startDate){
    this.startDate = startDate;
}


public void setTournamentName(String tournamentName){
    this.tournamentName = tournamentName;
}


public void setFinishDate(String finishDate){
    this.finishDate = finishDate;
}


public void setNum(Integer num){
    this.num = num;
}


public String getTournamentName(){
    return tournamentName;
}


public Integer getNum(){
    return num;
}


@Override
public String toString(){
    return "TournamentReport [num=" + num + ", tournamentName=" + tournamentName + ", startDate=" + startDate + ", finishDate=" + finishDate + "]";
}


public String getFinishDate(){
    return finishDate;
}


}