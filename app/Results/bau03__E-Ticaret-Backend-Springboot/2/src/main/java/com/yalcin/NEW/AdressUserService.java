package com.yalcin.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.repository.AdressRepository;
import com.yalcin.entity.Adress;
@Service
public class AdressUserService {

@Autowired
 private AdressRepository adressrepository;


public void setAdress(Integer id,Set<Adress> adress){
adressrepository.setAdress(id,adress);
}


public Set<Adress> getAdress(Integer id){
return adressrepository.getAdress(id);
}


}