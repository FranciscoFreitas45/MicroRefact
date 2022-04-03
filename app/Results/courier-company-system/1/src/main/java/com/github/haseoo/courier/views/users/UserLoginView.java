package com.github.haseoo.courier.views.users;
 import com.github.haseoo.courier.enums.UserType;
import com.github.haseoo.courier.security.JwtAuthenticationResponse;
import com.github.haseoo.courier.servicedata.users.UserData;
import lombok.Builder;
import lombok.Value;
import lombok.AccessLevel.PRIVATE;
@Value
@Builder(access = PRIVATE)
public class UserLoginView {

 private  Long id;

 private  String userName;

 private  UserType userType;

 private  JwtAuthenticationResponse response;


public UserLoginView of(UserData userData,JwtAuthenticationResponse response){
    return UserLoginView.builder().id(userData.getId()).userType(userData.getUserType()).response(response).userName(userData.getUserName()).build();
}


}