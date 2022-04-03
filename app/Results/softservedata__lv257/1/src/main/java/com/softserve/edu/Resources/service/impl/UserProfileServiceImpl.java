package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
import java.util.Optional;
import com.softserve.edu.Resources.Interface.RoleDAO;
@Service
public class UserProfileServiceImpl implements UserProfileService{

@Autowired
 private UserDetailsDAO userDetailsDAO;

@Autowired
 private UserDAO userDAO;

@Autowired
 private RoleDAO roleDAO;

@Autowired
 private  UserDetailsService userDetailsService;

@Autowired
 private  UserService userService;


@Transactional
public UserProfileDTO getUserProfileByUserId(Long id){
    UserProfileDTO userProfileDTO = new UserProfileDTO();
    return userProfileDTO;
}


@Override
@Transactional
public UserProfileDTO createPasswordDTO(Principal principal){
    String userName = principal.getName();
    User user = userService.findByEmail(userName);
    UserProfileDTO userProfileDTO = new UserProfileDTO();
    return userProfileDTO;
}


@Override
@Transactional
public UserDetails getUserDetailsByDTO(UserProfileDTO userProfileDTO){
    long id = userProfileDTO.getId();
    Optional<UserDetails> optUD = userDetailsDAO.findByUserId(id);
    if (!optUD.isPresent())
        // should be not found exception
        throw new IllegalArgumentException("Requested User Detail's ID is not found");
    UserDetails userDetails = optUD.get();
    userDetails.setFirstName(userProfileDTO.getFirstName());
    return userDetails;
}


@Transactional
public UserProfileDTO getUserProfileByEmail(String email){
    UserProfileDTO userProfileDTO = new UserProfileDTO();
    System.out.println("Privileges extracted (commented)");
    return userProfileDTO;
}


@Override
@Transactional
public UserProfileDTO createUserProfileDTO(Principal principal){
    String userName = principal.getName();
    User user = userService.findByEmail(userName);
    Optional<UserDetails> optDetails = userDetailsService.getUserDetailsByUserId(user.getId());
    System.out.printf("id is %d", user.getId());
    if (!optDetails.isPresent())
        /**
         * todo LA not found exception should be
         */
        throw // not found exception should be
        new IllegalArgumentException("Provided user detail's ID is illegal");
    UserDetails details = optDetails.get();
    UserProfileDTO userProfileDTO = new UserProfileDTO();
    userProfileDTO.setId(details.getId());
    userProfileDTO.setFirstName(details.getFirstName());
    userProfileDTO.setMiddleName(details.getMiddleName());
    userProfileDTO.setSecondName(details.getSecondName());
    userProfileDTO.setPassportNumber(details.getPassportNumber());
    userProfileDTO.setPassportSeries(details.getPassportSeries());
    userProfileDTO.setPhone(details.getPhone());
    userProfileDTO.setIdAddress(details.getIdAddress());
    userProfileDTO.setBankId(details.getBankId());
    userProfileDTO.setUser(details.getUser());
    System.out.println();
    return userProfileDTO;
}


@Override
@Transactional
public void saveUserProfile(UserProfileDTO userProfileDTO){
    UserDetails userDetails = getUserDetailsByDTO(userProfileDTO);
    userDetails.setFirstName(userProfileDTO.getFirstName());
    userDetails.setMiddleName(userProfileDTO.getMiddleName());
    userDetails.setSecondName(userProfileDTO.getSecondName());
    userDetails.setPassportNumber(userProfileDTO.getPassportNumber());
    userDetails.setPassportSeries(userProfileDTO.getPassportSeries());
    userDetails.setPhone(userProfileDTO.getPhone());
    userDetails.setIdAddress(userProfileDTO.getIdAddress());
    userDetails.setBankId(userProfileDTO.getBankId());
    userDetails.setUser(userService.getUserById(userProfileDTO.getId()));
}


}