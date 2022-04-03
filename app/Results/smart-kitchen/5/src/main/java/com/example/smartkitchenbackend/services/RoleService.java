package com.example.smartkitchenbackend.services;
 import com.example.smartkitchenbackend.entities.Role;
import com.example.smartkitchenbackend.entities.RoleName;
import com.example.smartkitchenbackend.expection.AppException;
import com.example.smartkitchenbackend.repositories.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RoleService {

 private  RoleRepository roleRepository;


public Role findByName(RoleName roleName){
    return roleRepository.findByName(roleName);
}


}