package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
@Component
public interface UserService extends UserDetailsService{


public User findByUserName(String username)
;

public User getCurrentLoggedInUser()
;

public User findById(Long id)
;

public void save(User user)
;

public Long count()
;

public Page<User> findAll(Pageable pageable)
;

}