package com.cg.hbm.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hbm.dao.IAdminRepository;
import com.cg.hbm.entites;
@Service
public class IAdminServiceImpl implements IAdminService{

@Autowired
 private  IAdminRepository loginRepository;

@Autowired
 private  IAdminRepository logoutRepository;


@Override
public Admin signIn(Admin admin){
    Admin admin1 = loginRepository.saveAndFlush(admin);
    return admin1;
}


@Override
public Admin signOut(Admin admin){
    logoutRepository.delete(admin);
    return admin;
}


}