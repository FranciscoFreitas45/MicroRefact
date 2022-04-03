package com.example.steam.entity;
 import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class SpikeGame {

 private  Long id;

 private  Long gameId;

 private  Long posterGame;

 private  Integer spikePrice;

 private  Integer stockCount;

 private  Date startTime;

 private  Date endTime;

 private  Integer gamePrice;

public SpikeGame() {
}
public Integer getSpikePrice(){
    return spikePrice;
}


public Integer getGamePrice(){
    return gamePrice;
}


public Long getGameId(){
    return gameId;
}


public Integer getStockCount(){
    return stockCount;
}


public Long getId(){
    return id;
}


public void setGamePrice(Integer gamePrice){
    this.gamePrice = gamePrice;
}


public void setPosterGame(Long posterGame){
    this.posterGame = posterGame;
}


public void setStockCount(Integer stockCount){
    this.stockCount = stockCount;
}


public Date getEndTime(){
    return endTime;
}


public void setGameId(Long gameId){
    this.gameId = gameId;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setId(Long id){
    this.id = id;
}


public void setSpikePrice(Integer spikePrice){
    this.spikePrice = spikePrice;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public Long getPosterGame(){
    return posterGame;
}


}