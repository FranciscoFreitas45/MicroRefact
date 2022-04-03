package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.UserToken;
public interface UserTokenDao extends GenericDao<UserToken>{


public UserToken findByToken(String token)
;

public void delete(String token)
;

}