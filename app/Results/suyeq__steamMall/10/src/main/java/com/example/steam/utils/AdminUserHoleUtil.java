package com.example.steam.utils;
 import com.example.steam.entity.User;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
public class AdminUserHoleUtil {

 private  Map<String,AdminUserHolder> adminMap;

 private long startTime;

 private long expiredTime;

 private User user;


public void removeUser(String email){
    adminMap.remove(email);
}


public void addUser(User user){
    adminMap.put(user.getEmail(), new AdminUserHolder(user));
}


public User getUser(){
    long now = System.currentTimeMillis();
    if (now - this.startTime > this.expiredTime) {
        System.out.println("啦啦啦");
        return null;
    }
    return this.user;
}


}