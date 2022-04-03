package com.example.smartkitchenbackend.repositories.role;
 import com.example.smartkitchenbackend.entities.Role;
import com.example.smartkitchenbackend.entities.RoleName;
import com.example.smartkitchenbackend.expection.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class DefaultRoleRepository implements RoleRepository{

 private  RoleJPARepository roleJPARepository;


@Override
public Role findByName(RoleName roleName){
    return roleJPARepository.findByName(roleName).orElseThrow(() -> new AppException("User Role not set."));
}


}