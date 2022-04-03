package com.yalcin.security.services;
 import com.yalcin.dto.request.AdressFrom;
import com.yalcin.entity.Adress;
import com.yalcin.entity.User;
import com.yalcin.repository.AdressRepository;
import com.yalcin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.yalcin.Interface.UserServiceImpl;
@Service
public class AdressService {

@Autowired
 private AdressService adressService;

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private AdressRepository adressRepository;


public void adressSave(AdressFrom adressFrom){
    Adress adress = new Adress(adressFrom.getCountry(), adressFrom.getProvince(), adressFrom.getDistrict(), adressFrom.getStreet(), adressFrom.getBuildingNumber(), adressFrom.getAdressType());
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    adress.setUser(user);
    adressRepository.save(adress);
}


public List<Adress> getAdress(String userId){
    return adressRepository.findAllByUser_Id(Integer.parseInt(userId));
}


}