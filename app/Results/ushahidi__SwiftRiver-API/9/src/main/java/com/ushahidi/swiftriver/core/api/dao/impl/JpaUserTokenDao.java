package com.ushahidi.swiftriver.core.api.dao.impl;
 import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.UserTokenDao;
import com.ushahidi.swiftriver.core.model.UserToken;
@Repository
public class JpaUserTokenDao extends AbstractJpaDao<UserToken>implements UserTokenDao{


public UserToken findByToken(String token){
    String query = "SELECT u FROM UserToken u WHERE u.token = :token";
    UserToken userToken = null;
    try {
        userToken = (UserToken) em.createQuery(query).setParameter("token", token).getSingleResult();
    } catch (NoResultException e) {
    // Do nothing
    }
    return userToken;
}


public void delete(String token){
    em.createQuery("DELETE FROM UserToken WHERE token = :token").setParameter("token", token).executeUpdate();
}


public Set<UserToken> getTokens(long id)

public void setTokens(long id,Set<UserToken> tokens)

public void setExpires(long id,Date expires)

public void setUser(long id,User user)

public void setCreated(long id,Date created)

}