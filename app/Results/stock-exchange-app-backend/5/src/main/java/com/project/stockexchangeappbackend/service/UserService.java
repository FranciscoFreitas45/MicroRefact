package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.ChangePasswordDTO;
import com.project.stockexchangeappbackend.dto.EditUserDetailsDTO;
import com.project.stockexchangeappbackend.dto.EditUserNameDTO;
import com.project.stockexchangeappbackend.dto.RegistrationUserDTO;
import com.project.stockexchangeappbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.security.Principal;
public interface UserService {


public void changeUserPassword(ChangePasswordDTO changePasswordDTO,Principal principal)
;

public void changeUserDetails(EditUserNameDTO editUserNameDTO,Principal principal)
;

public Page<User> getUsers(Pageable pageable,Specification<User> specification)
;

public void registerUser(RegistrationUserDTO registrationUserDTO,String tag)
;

public User findUserById(Long id)
;

public void updateUser(Long id,EditUserDetailsDTO editUserDetailsDTO)
;

public User findUserByEmail(String email)
;

}