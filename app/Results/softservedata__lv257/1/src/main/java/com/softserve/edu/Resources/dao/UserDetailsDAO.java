package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.UserDetails;
import java.util.List;
import java.util.Optional;
public interface UserDetailsDAO {


public List<UserDetails> getAllUserDetails()
;

public Optional<UserDetails> findByUserId(long id)
;

public void save(UserDetails userDetails)
;

public Optional<UserDetails> findByEmail(String email)
;

}