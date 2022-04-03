package com.gp.cricket.report.entity;
 public class TournamentMatchFutureReport {

 private  Integer no;

 private  String club1;

 private  String club2;

 private  String type;

 private  String date;

 private  String time;

 private  String stadium;

public TournamentMatchFutureReport() {
    super();
// TODO Auto-generated constructor stub
}public TournamentMatchFutureReport(Integer no, String club1, String club2, String type, String date, String time, String stadium) {
    super();
    this.no = no;
    this.club1 = club1;
    this.club2 = club2;
    this.type = type;
    this.date = date;
    this.time = time;
    this.stadium = stadium;
}
public String getClub2(){
    return club2;
}


public String getClub1(){
    return club1;
}


public String getTime(){
    return time;
}


public void setNo(Integer no){
    this.no = no;
}


public void setClub1(String club1){
    this.club1 = club1;
}


public void setClub2(String club2){
    this.club2 = club2;
}


public Integer getNo(){
    return no;
}


public void setType(String type){
    this.type = type;
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
    return "TournamentMatchFutureReport [no=" + no + ", club1=" + club1 + ", club2=" + club2 + ", type=" + type + ", date=" + date + ", time=" + time + ", stadium=" + stadium + "]";
}


public void setTime(String time){
    this.time = time;
}


}