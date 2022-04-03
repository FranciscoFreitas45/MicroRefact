package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;
import java.util.Optional;
public interface UserProfileDAO extends GenericDAO<UserDetails, Long>{


public void saveProfile(UserDetails userDetails)
;

public Optional<UserDetails> findByEmail(String email)
;

}