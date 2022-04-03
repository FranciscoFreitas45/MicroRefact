package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.DTO.User;
import com.cg.hbm.Request.UserRequest;
import com.cg.hbm.*;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.http.converter.json.*;


@Service
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();


public User getUser(int user_id){

    try {
        User aux = restTemplate.getForObject("http://127.0.0.1:8080/BookingDetails/{id}/User/getUser", User.class, user_id);
        return aux;

    }catch (Exception e) {
        return null;
    }
}


public void setUser(User user,int user_id){
    System.out.println("ID DO SET  " +user_id);
 restTemplate.put("http://127.0.0.1:8080/BookingDetails/{id}/User/setUser",user,user_id);
 return ;
}


}