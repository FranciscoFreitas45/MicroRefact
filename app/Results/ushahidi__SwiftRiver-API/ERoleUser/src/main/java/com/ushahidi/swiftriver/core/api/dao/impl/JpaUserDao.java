package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.UserDao;
import com.ushahidi.swiftriver.core.model.User;
@Repository
public class JpaUserDao extends AbstractJpaDao<User>implements UserDao{

 final  Logger logger;


public User findByUsernameOrEmail(String search){
    String qlString = "FROM User u WHERE email = :email OR username = :username";
    TypedQuery<User> query = em.createQuery(qlString, User.class);
    query.setParameter("email", search);
    query.setParameter("username", search);
    User user = null;
    try {
        user = query.getSingleResult();
    } catch (NoResultException e) {
        logger.info("No user with email {} or username {} exists", search, search);
    }
    return user;
}


@Override
public User findByUsername(String username){
    String query = "SELECT u FROM User u WHERE u.username = :username";
    User user = null;
    try {
        user = (User) em.createQuery(query).setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
    // Do nothing
    }
    return user;
}


public void updateLastLogin(User user){
    // Set the last login date to the current date
    user.setLastLoginDate(new Date());
    // Increment the no. of user logins
    int logins = user.getLogins() + 1;
    user.setLogins(logins);
    this.update(user);
}


@Override
public User create(User user){
    // Set the date created
    user.setDateCreated(new Date());
    return super.create(user);
}


public User getOwner(long idHR3O)

public void setOwner(long idHR3O,User owner)

public void setPassword(long id,String password)

public void setActive(long id,Boolean active)

public void setName(long id,String name)

public void setEmail(long id,String email)

public void setUsername(long id,String username)

public void setExpired(long id,Boolean expired)

public void setLocked(long id,Boolean locked)

public void setRoles(long id,Set<Role> roles)

}