package com.example.steam.utils.AdminUserHoleUtil;
 import com.example.steam.entity.User;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
public class AdminUserHolder {

 private long startTime;

 private long expiredTime;

 private User user;

AdminUserHolder(User user) {
    this.startTime = System.currentTimeMillis();
    this.expiredTime = 60L * 60 * 2 * 1000;
    this.user = user;
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