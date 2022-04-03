package com.tech.services.user;
 import com.tech.models.entities.user.UserRole;
import com.tech.repositories.IUserRolesRepository;
import com.tech.services.interfaces.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserRoleService implements IUserRoleService{

@Autowired
 private  IUserRolesRepository repository;


@Override
public void deleteUserRole(UserRole userRole){
    repository.delete(userRole);
}


@Override
public List<UserRole> getUserRolesByRoles(String role){
    return repository.findByRole(role);
}


@Override
public void addUserRole(UserRole userRole){
    repository.save(userRole);
}


@Override
public void modifyUserRole(UserRole newRole){
    repository.setUserRoleById(newRole.getRole(), newRole.getUserID());
}


@Override
public List<UserRole> getAllUserRoles(){
    return repository.findAll();
}


@Override
public String getRoleByUserID(Long userid){
    return repository.findByUserID(userid).getRole();
}


}