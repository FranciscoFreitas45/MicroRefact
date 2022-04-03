package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class SpikeShopCart {

 private  Long id;

 private  String email;

 private  Long spikeGameId;

public SpikeShopCart() {
}public SpikeShopCart(String email, Long spikeGameId) {
    this.email = email;
    this.spikeGameId = spikeGameId;
}
public void setSpikeGameId(Long spikeGameId){
    this.spikeGameId = spikeGameId;
}


public void setEmail(String email){
    this.email = email;
}


public Long getSpikeGameId(){
    return spikeGameId;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public Long getId(){
    return id;
}


}