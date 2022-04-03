package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;
public interface UserProfileService {


public UserProfileDTO getUserProfileByUserId(Long id)
;

@Transactional
public UserProfileDTO createPasswordDTO(Principal principal)
;

@Transactional
public UserDetails getUserDetailsByDTO(UserProfileDTO userProfileDTO)
;

@Transactional
public UserProfileDTO getUserProfileByEmail(String email)
;

@Transactional
public UserProfileDTO createUserProfileDTO(Principal principal)
;

@Transactional
public void saveUserProfile(UserProfileDTO userDetailsDTO)
;

}