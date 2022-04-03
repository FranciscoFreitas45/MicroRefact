package com.yalcin.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.repository.AdressRepository;
import com.yalcin.entity.Adress;
@Service
public class AdressOrderService {

@Autowired
 private AdressRepository adressrepository;


public void setAdress(Integer id,Adress adress){
adressrepository.setAdress(id,adress);
}


public Adress getAdress(Integer id){
return adressrepository.getAdress(id);
}


}