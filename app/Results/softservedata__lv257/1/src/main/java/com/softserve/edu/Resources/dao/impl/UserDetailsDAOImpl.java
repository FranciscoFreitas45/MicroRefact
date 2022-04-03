package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
@Repository
@Qualifier("userDrtailsDAO")
@Transactional
public class UserDetailsDAOImpl extends GenericDAOImpl<UserDetails, Long>implements UserDetailsDAO{

 static  Logger LOGGER;

@PersistenceContext
 private  EntityManager entityManager;

protected UserDetailsDAOImpl() {
    super(UserDetails.class, LOGGER);
}public UserDetailsDAOImpl(EntityManager entityManager) {
    super(UserDetails.class, LOGGER);
    this.entityManager = entityManager;
}
public List<UserDetails> getAllUserDetails(){
    List<?> list = entityManager.createQuery("SELECT p FROM UserDetails p").getResultList();
    return (List<UserDetails>) list;
}


public Optional<UserDetails> findByUserId(long id){
    String queryUser = "select i from UserDetails i where i.user.id = :id";
    return querySingleResult(queryUser, "id", id);
}


public void save(UserDetails userDetails){
    final UserDetails userDetailsToEntity1 = makePersistent(userDetails);
}


@Override
public Optional<UserDetails> findByEmail(String email){
    String queryUserDetails = "select i from User i where i.username = :username";
    return querySingleResult(queryUserDetails, "username", email);
}


public User setUserDetails(Long idO4D4,UserDetails userDetails)

public UserDetails getUserDetails(Long idO4D4)

}