package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.DAO.PermissionsDAO;
import com.app.pojo.Permissions;
import com.app.pojo.Teacher;
import com.app.service.PermissionsService;
@Service("PermissionsService")
public class PermissionsServiceImpl implements PermissionsService{

@Autowired
 private PermissionsDAO permissionsDAO;


@Override
public List<Permissions> getall(){
    return permissionsDAO.getall();
}


@Override
public Permissions edit(int id){
    return permissionsDAO.edit(id);
}


@Override
public Permissions GetPermissionOfTeacher(Teacher t){
    return permissionsDAO.GetPermissionOfTeacher(t);
}


@Override
public Permissions find(int id){
    return permissionsDAO.find(id);
}


@Override
public void create(Permissions permissions){
    permissionsDAO.create(permissions);
}


@Override
public void update(Permissions teacher){
    permissionsDAO.update(teacher);
}


@Override
public void delet(int id){
    permissionsDAO.delet(id);
}


}