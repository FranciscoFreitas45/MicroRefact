package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Repository("userDAO")
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User, Long>implements UserDAO{

 static  Logger LOGGER;

@PersistenceContext
 private  EntityManager entityManager;

public UserDAOImpl() {
    super(User.class, LOGGER);
}
public List<User> getAllUsers(){
    List<?> list = entityManager.createQuery("SELECT p FROM User p").getResultList();
    return (List<User>) list;
}


@Override
public List<User> getUsersWithRoles(String roleNames){
    final String getUsersWithRoles = "select u from User u join u.roles r where r.name in (:names)";
    return queryResultList(getUsersWithRoles, "names", Arrays.asList(roleNames));
}


public User findByEmail(String username){
    String query = "select i from User i where i.username = :username";
    Optional<User> result = querySingleResult(query, "username", username);
    return result.orElse(null);
}


public User getUser(Long idO2KS)

public void setUser(Long idO2KS,User user)

public User getUser(Long idXLHJ)

public VerificationToken setUser(Long idXLHJ,User user)

public ResourceRequest setResourcesAdmin(Long idSND5,User resourcesAdmin)

public ResourceRequest setRegister(Long idSND5,User register)

public User getRegister(Long idSND5)

public User getResourcesAdmin(Long idSND5)

public ResourceType setAssigner(Long id93GK,User assigner)

public User getAssigner(Long id93GK)

public User setRoles(Long id,Collection<Role> roles)

public User setPassword(Long id,String password)

public User setUsername(Long id,String username)

public User setUserDetails(Long id,UserDetails userDetails)

public boolean isEnabled(Long id)

public User setEnabled(Long id,boolean enabled)

}