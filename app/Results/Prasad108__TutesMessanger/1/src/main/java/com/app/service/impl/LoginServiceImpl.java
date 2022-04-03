package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.DAO.LoginDAO;
import com.app.pojo.Login;
import com.app.service.LoginService;
@Service("loginService")
public class LoginServiceImpl implements LoginService{

@Autowired
 private LoginDAO loginDAO;


@Override
public boolean exist(Login login){
    return loginDAO.exist(login);
}


@Override
public List<Login> getall(){
    return loginDAO.getall();
}


@Override
public Login findByUsername(String username){
    return loginDAO.findByUsername(username);
}


@Override
public Login edit(int id){
    return loginDAO.edit(id);
}


@Override
public Login find(int id){
    return loginDAO.find(id);
}


@Override
public void create(Login login){
    loginDAO.create(login);
}


@Override
public void update(Login login){
    loginDAO.update(login);
}


@Override
public Login find_By_Uname_pwd(Login l){
    return loginDAO.find_By_Uname_pwd(l);
}


@Override
public void delet(Login login){
    loginDAO.delet(login);
}


@Override
public boolean Isenabled(Login login){
    return loginDAO.Isenabled(login);
}


}