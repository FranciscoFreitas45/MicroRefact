package com.gp.cricket.report.entity;
 public class TournamentMatchPastReport {

 private  String club1;

 private  String club2;

 private  String type;

 private  String date;

 private  String stadium;

 private  String winClub;

public TournamentMatchPastReport() {
    super();
// TODO Auto-generated constructor stub
}public TournamentMatchPastReport(String club1, String club2, String type, String date, String stadium, String winClub) {
    super();
    this.club1 = club1;
    this.club2 = club2;
    this.type = type;
    this.date = date;
    this.stadium = stadium;
    this.winClub = winClub;
}
public String getClub2(){
    return club2;
}


public String getClub1(){
    return club1;
}


public void setClub1(String club1){
    this.club1 = club1;
}


public void setClub2(String club2){
    this.club2 = club2;
}


public void setType(String type){
    this.type = type;
}


public void setWinClub(String winClub){
    this.winClub = winClub;
}


public String getWinClub(){
    return winClub;
}


public String getType(){
    return type;
}


public String getStadium(){
    return stadium;
}


public void setStadium(String stadium){
    this.stadium = stadium;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "TournamentMatchPastReport [club1=" + club1 + ", club2=" + club2 + ", type=" + type + ", date=" + date + ", stadium=" + stadium + ", winClub=" + winClub + "]";
}


}