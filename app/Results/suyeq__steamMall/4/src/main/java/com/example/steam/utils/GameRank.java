package com.example.steam.utils;
 import java.util.List;
public class GameRank {

 private  Long id;

 private  List<String> type;

public GameRank() {
}public GameRank(long id, List<String> type) {
    this.id = id;
    this.type = type;
}
public List<String> getType(){
    return type;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setType(List<String> type){
    this.type = type;
}


}