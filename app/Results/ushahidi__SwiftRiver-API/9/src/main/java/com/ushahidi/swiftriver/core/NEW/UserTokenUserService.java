package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaUserTokenDao;
import com.ushahidi.swiftriver.core.model.UserToken;
@Service
public class UserTokenUserService {

@Autowired
 private JpaUserTokenDao jpausertokendao;


public Set<UserToken> getTokens(long id){
return jpausertokendao.getTokens(id);
}


public void setTokens(long id,Set<UserToken> tokens){
jpausertokendao.setTokens(id,tokens);
}


}