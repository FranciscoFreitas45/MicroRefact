package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.UserProfileDAO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.invoke.MethodHandles;
import java.util.Optional;
@Repository
@Qualifier("userProfileDAO")
@Transactional
public class UserProfileDAOImpl extends GenericDAOImpl<UserDetails, Long>implements UserProfileDAO{

 static  Logger LOGGER;

@PersistenceContext
 private  EntityManager entityManager;

protected UserProfileDAOImpl() {
    super(UserDetails.class, LOGGER);
}
public void saveProfile(UserDetails userDetails){
    final UserDetails userDetailsToEntity = makePersistent(userDetails);
}


@Override
public Optional<UserDetails> findByEmail(String email){
    String queryUserDetails = "select i from User i where i.username = :username";
    return querySingleResult(queryUserDetails, "username", email);
}


}