package org.jugbd.mnet.service;
 import org.jugbd.mnet.dao.UserDao;
import org.jugbd.mnet.domain.User;
import org.jugbd.mnet.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService{

 private  Logger log;

@Autowired
 private  UserDao userDao;

@Autowired
 private  MessageDigestPasswordEncoder messageDigestPasswordEncoder;


@Override
public User findByUserName(String username){
    try {
        return userDao.findByUsername(username);
    } catch (NoResultException e) {
        log.error("user not found by ={}", username, e);
    }
    return null;
}


@Override
public User getCurrentLoggedInUser(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.debug("getCurrentLoggedInUser() => user.getUsername() ={}, id={}", user.getUsername(), user.getId());
    return findById(user.getId());
}


@Override
public User findById(Long id){
    return userDao.findOne(id);
}


@Override
public void save(User user){
    if (user.getId() == null) {
        user.setUsername(user.getUsername().trim());
        user.setSalt(StringUtils.generateRandomString(16));
        user.setHashedPassword(messageDigestPasswordEncoder.encodePassword(user.getPassword(), user.getSalt()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userDao.save(user);
    } else {
        User savedUser = findById(user.getId());
        savedUser.setFullName(user.getFullName());
        savedUser.setEmail(user.getEmail());
        savedUser.setRoles(user.getRoles());
        savedUser.setDesignation(user.getDesignation());
        savedUser.setPhoneNumber(user.getPhoneNumber());
        String encryptedPassword = messageDigestPasswordEncoder.encodePassword(user.getPassword(), savedUser.getSalt());
        savedUser.setHashedPassword(encryptedPassword);
        userDao.save(savedUser);
    }
}


@Override
public UserDetails loadUserByUsername(String username){
    User user = userDao.findByUsername(username);
    if (user == null) {
        throw new UsernameNotFoundException("User not found by " + username);
    }
    return user;
}


@Override
public Long count(){
    return userDao.count();
}


@Override
public Page<User> findAll(Pageable pageable){
    return userDao.findAll(pageable);
}


}