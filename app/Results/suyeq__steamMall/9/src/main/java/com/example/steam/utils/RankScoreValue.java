package com.example.steam.utils;
 public class RankScoreValue {

 private  T value;

 private  long score;

public RankScoreValue() {
}
public T getValue(){
    return value;
}


public void setValue(T value){
    this.value = value;
}


public long getScore(){
    return score;
}


public void setScore(long score){
    this.score = score;
}


}