package org.jeecgframework.jwt.service;
 import org.jeecgframework.jwt.model.TokenModel;
import org.jeecgframework.web.system.pojo.base.TSUser;
public interface TokenManager {


public TokenModel getToken(String token,String userid)
;

public String createToken(TSUser user)
;

public void deleteToken(String username)
;

public boolean checkToken(TokenModel model)
;

}