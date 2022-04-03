package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.entity.UserDetails;
import java.util.List;
import java.util.Optional;
public interface UserDetailsService {


public Optional<UserDetails> getUserDetailsByUserId(Long id)
;

public Optional<UserDetails> getUserDetailsByEmail(String email)
;

public List<UserDetails> getAllUsers()
;

}