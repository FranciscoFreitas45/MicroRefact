package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.PrivilegeDAO;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.PrivilegeType;
import com.softserve.edu.Resources.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PrivilegeServiceImpl implements PrivilegeService{

@Autowired
 private PrivilegeDAO privilegeDAO;


@Override
public Privilege getPrivilegeById(Long id){
    return null;
}


@Override
public void deleteAllPrivilege(){
    privilegeDAO.deleteAllPrivileges();
}


@Override
public Privilege addPrivilege(String name,String description,PrivilegeType privilegeType){
    Privilege privilege = privilegeDAO.addPrivilege(new Privilege(name, description, privilegeType));
    return privilege;
}


public List<Privilege> getAllPrivileges(){
    /*List<Privilege> list = privilegeDAO.getAllPrivileges();
        List<String> names = new ArrayList<>();

        for (Privilege x : list) {
            names.add(x.getName());
        }
        return names;*/
    return privilegeDAO.getAllPrivileges();
}


@Override
public void deletePrivilege(){
}


}