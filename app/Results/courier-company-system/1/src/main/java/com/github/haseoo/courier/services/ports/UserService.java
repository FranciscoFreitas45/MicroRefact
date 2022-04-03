package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.commandsdata.users.LoginCommandData;
import com.github.haseoo.courier.security.JwtAuthenticationResponse;
import com.github.haseoo.courier.servicedata.users.UserData;
import java.util.List;
public interface UserService {


public List<UserData> getList()
;

public UserData setAsInactive(Long id)
;

public void checkUsername(String username)
;

public UserData getByUsername(String userName)
;

public JwtAuthenticationResponse getJwt(LoginCommandData loginCommandData)
;

public UserData setAsActive(Long id)
;

}