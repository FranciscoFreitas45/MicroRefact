package com.example.steam.service;
 import com.example.steam.dao.SpikeShopCartDao;
import com.example.steam.entity.SpikeShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SpikeShopCartService {

@Autowired
 private SpikeShopCartDao spikeShopCartDao;


public int addSpikeShopCart(SpikeShopCart spikeShopCart){
    return spikeShopCartDao.addSpikeShopCart(spikeShopCart);
}


public SpikeShopCart findSpikeShopCartByUserEmail(String email){
    return spikeShopCartDao.findSpikeShopCartByUserEmail(email);
}


public int deleteSpikeShopCartByUserEmail(String email){
    return spikeShopCartDao.deleteSpikeShopCart(email);
}


public SpikeShopCart findSpikeShopCart(String email,long spikeGameId){
    return spikeShopCartDao.findSpikeShopCart(email, spikeGameId);
}


}