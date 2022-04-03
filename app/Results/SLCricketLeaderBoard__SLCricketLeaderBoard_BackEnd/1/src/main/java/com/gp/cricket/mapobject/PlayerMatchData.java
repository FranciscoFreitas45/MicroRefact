package com.gp.cricket.mapobject;
 public class PlayerMatchData {

 private  String tournament;

 private  String match;

 private  String result1;

 private  String result2;

public PlayerMatchData() {
    super();
// TODO Auto-generated constructor stub
}public PlayerMatchData(String tournament, String match, String result1, String result2) {
    super();
    this.tournament = tournament;
    this.match = match;
    this.result1 = result1;
    this.result2 = result2;
}
public String getMatch(){
    return match;
}


public String getResult1(){
    return result1;
}


public String getResult2(){
    return result2;
}


public void setMatch(String match){
    this.match = match;
}


public void setResult2(String result2){
    this.result2 = result2;
}


@Override
public String toString(){
    return "PlayerMatchData [tournament=" + tournament + ", match=" + match + ", result1=" + result1 + ", result2=" + result2 + "]";
}


public void setResult1(String result1){
    this.result1 = result1;
}


public String getTournament(){
    return tournament;
}


public void setTournament(String tournament){
    this.tournament = tournament;
}


}