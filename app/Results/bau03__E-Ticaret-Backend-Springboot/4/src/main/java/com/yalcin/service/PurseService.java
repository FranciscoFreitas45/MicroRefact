package com.yalcin.service;
 import com.yalcin.dto.request.AddProductForm;
import com.yalcin.dto.request.PurseForm;
import com.yalcin.entity;
import com.yalcin.repository.PurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.yalcin.Interface.UserServiceImpl;
@Service
public class PurseService {

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private PurseRepository purseRepository;


public Purse getPurse(){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    return purseRepository.findAllByUser_Id(user.getId());
}


public void purseAdd(PurseForm purseForm){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    if (purseRepository.findAllByUser_Id(user.getId()) != null) {
        Purse purse = purseRepository.findAllByUser_Id(user.getId());
        purse.setBalance(purse.getBalance() + Float.parseFloat(purseForm.getBalance()));
        purse.setTimestap(new Date());
        purseRepository.save(purse);
    } else {
        Purse purse = new Purse(Float.parseFloat(purseForm.getBalance()));
        purse.setUser(user);
        purse.setTimestap(new Date());
        purseRepository.save(purse);
    }
}


}