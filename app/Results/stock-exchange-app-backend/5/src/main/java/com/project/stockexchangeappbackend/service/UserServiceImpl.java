package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.ChangePasswordDTO;
import com.project.stockexchangeappbackend.dto.EditUserDetailsDTO;
import com.project.stockexchangeappbackend.dto.EditUserNameDTO;
import com.project.stockexchangeappbackend.dto.RegistrationUserDTO;
import com.project.stockexchangeappbackend.entity.Role;
import com.project.stockexchangeappbackend.entity.Tag;
import com.project.stockexchangeappbackend.entity.User;
import com.project.stockexchangeappbackend.exception.InvalidInputDataException;
import com.project.stockexchangeappbackend.repository.AllOrdersRepository;
import com.project.stockexchangeappbackend.repository.ResourceRepository;
import com.project.stockexchangeappbackend.repository.UserRepository;
import com.project.stockexchangeappbackend.security.BannedAccessTokens;
import com.project.stockexchangeappbackend.util.timemeasuring.LogicBusinessMeasureTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.project.stockexchangeappbackend.Interface.UserRepository;
import com.project.stockexchangeappbackend.Interface.AllOrdersRepository;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import com.project.stockexchangeappbackend.Interface.TagService;
import com.project.stockexchangeappbackend.Interface.BannedAccessTokens;
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

 private  UserRepository userRepository;

 private  AllOrdersRepository allOrdersRepository;

 private  ResourceRepository resourceRepository;

 private  PasswordEncoder passwordEncoder;

 private  TagService tagService;

 private  BannedAccessTokens bannedAccessTokens;


@Override
@LogicBusinessMeasureTime
@Transactional
public void changeUserPassword(ChangePasswordDTO changePasswordDTO,Principal principal){
    User user = findUserByEmail(principal.getName());
    if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
        throw new AccessDeniedException("User credential's are incorrect.");
    }
    user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
    userRepository.save(user);
    log.info("User " + user.getEmail() + "'s password was successfully changed.");
}


@Override
@LogicBusinessMeasureTime
@Transactional
public void changeUserDetails(EditUserNameDTO editUserNameDTO,Principal principal){
    User user = userRepository.findByEmailIgnoreCase(principal.getName()).orElseThrow(() -> new InvalidInputDataException("User Not Found", null));
    user.setFirstName(editUserNameDTO.getFirstName().trim());
    user.setLastName(editUserNameDTO.getLastName().trim());
    userRepository.save(user);
    log.info("User " + user.getEmail() + "'s details were successfully changed.");
}


@Override
@Transactional(readOnly = true)
@LogicBusinessMeasureTime
public Page<User> getUsers(Pageable pageable,Specification<User> specification){
    return userRepository.findAll(specification, pageable);
}


@Override
@Transactional
@LogicBusinessMeasureTime
public void registerUser(RegistrationUserDTO registrationUserDTO,String tag){
    if (userRepository.findByEmailIgnoreCase(registrationUserDTO.getEmail()).isPresent()) {
        throw new EntityExistsException("User with given email already exists.");
    }
    Tag tagObject = tagService.getTag(tag.trim()).orElseThrow(() -> new InvalidInputDataException("Tag not found", null));
    userRepository.save(User.builder().email(registrationUserDTO.getEmail().trim()).password(passwordEncoder.encode(registrationUserDTO.getPassword())).firstName(registrationUserDTO.getFirstName().trim()).lastName(registrationUserDTO.getLastName().trim()).role(Role.USER).money(BigDecimal.ZERO).tag(tagObject).isActive(true).build());
    log.info("User " + registrationUserDTO.getEmail() + " was successfully registered.");
}


@Override
@Transactional(readOnly = true)
@LogicBusinessMeasureTime
public User findUserById(Long id){
    return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
}


@Override
@Transactional
@LogicBusinessMeasureTime
public void updateUser(Long id,EditUserDetailsDTO editUserDetailsDTO){
    User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    Map<String, List<String>> errors = new HashMap<>();
    if (user.getRole().equals(Role.ADMIN)) {
        if (editUserDetailsDTO.getRole().equals(Role.USER) && userRepository.countByRole(Role.ADMIN).equals(1L)) {
            errors.put("role", List.of("Edited user is last admin. System must have at least one admin."));
        }
        if (editUserDetailsDTO.getRole().equals(Role.ADMIN) && !editUserDetailsDTO.getIsActive()) {
            errors.put("isActive", List.of("Admin cannot be blocked."));
        }
    } else if (user.getRole().equals(Role.USER) && editUserDetailsDTO.getRole().equals(Role.ADMIN)) {
        if (!editUserDetailsDTO.getIsActive()) {
            errors.put("isActive", List.of("Admin cannot be blocked."));
        }
        if (!allOrdersRepository.countByUser(user).equals(0L)) {
            errors.putIfAbsent("role", new ArrayList<>());
            errors.get("role").add("Admin cannot possess any orders.");
        }
        if (!resourceRepository.countByUser(user).equals(0L)) {
            errors.putIfAbsent("role", new ArrayList<>());
            errors.get("role").add("Admin cannot possess any stocks.");
        }
    }
    if (!errors.isEmpty()) {
        throw new InvalidInputDataException("Input Validation.", errors);
    }
    if (!editUserDetailsDTO.getIsActive() || !editUserDetailsDTO.getRole().equals(user.getRole())) {
        bannedAccessTokens.addUser(user.getEmail());
    }
    user.setFirstName(editUserDetailsDTO.getFirstName().trim());
    user.setLastName(editUserDetailsDTO.getLastName().trim());
    user.setRole(editUserDetailsDTO.getRole());
    user.setIsActive(editUserDetailsDTO.getIsActive());
    userRepository.save(user);
    log.info("User " + user.getEmail() + " was successfully updated.");
}


@Override
@Transactional(readOnly = true)
@LogicBusinessMeasureTime
public User findUserByEmail(String email){
    return userRepository.findByEmailIgnoreCase(email.trim()).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
}


}