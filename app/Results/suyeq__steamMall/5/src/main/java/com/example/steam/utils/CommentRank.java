package com.example.steam.utils;
 public class CommentRank {

 private  Long id;

 private  Long gameId;

public CommentRank() {
}public CommentRank(long id, long gameId) {
    this.id = id;
    this.gameId = gameId;
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


}