package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class GameLabel {

 private  Long id;

 private  Long gameId;

 private  Long labelId;

 private  Integer hotNum;

public GameLabel() {
}public GameLabel(long id, long gameId, long labelId, int hotNum) {
    this.id = id;
    this.gameId = gameId;
    this.labelId = labelId;
    this.hotNum = hotNum;
}
public Long getLabelId(){
    return labelId;
}


public void setGameId(Long gameId){
    this.gameId = gameId;
}


public Long getGameId(){
    return gameId;
}


public void setLabelId(Long labelId){
    this.labelId = labelId;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setHotNum(Integer hotNum){
    this.hotNum = hotNum;
}


public Integer getHotNum(){
    return hotNum;
}


}