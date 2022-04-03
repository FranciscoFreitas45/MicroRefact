package com.example.steam.DTO;
 import java.util.Date;
public class SpikeGameDetail {

 private  Long id;

 private  Long gameId;

 private  Integer spikePrice;

 private  Integer stockCount;

 private  Date startTime;

 private  Date endTime;

 private  Integer gamePrice;

 private  String posterImage;

public SpikeGameDetail() {
}
public String getPosterImage(){
    return posterImage;
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


public void setStockCount(Integer stockCount){
    this.stockCount = stockCount;
}


public Date getEndTime(){
    return endTime;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setSpikePrice(Integer spikePrice){
    this.spikePrice = spikePrice;
}


public Date getStartTime(){
    return startTime;
}


}