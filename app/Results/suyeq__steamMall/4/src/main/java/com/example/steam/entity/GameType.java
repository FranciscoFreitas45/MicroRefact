package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class GameType {

 private  Long id;

 private  Long gameId;

 private  Long typeId;

public GameType() {
}public GameType(long gameId, long typeId) {
    this.gameId = gameId;
    this.typeId = typeId;
}
public void setGameId(Long gameId){
    this.gameId = gameId;
}


public Long getGameId(){
    return gameId;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Long getTypeId(){
    return typeId;
}


}