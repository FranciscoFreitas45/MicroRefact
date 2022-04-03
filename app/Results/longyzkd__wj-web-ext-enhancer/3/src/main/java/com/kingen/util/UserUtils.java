package com.kingen.util;
 import org.apache.shiro.SecurityUtils;
import com.kingen.bean.User;
public class UserUtils {


public User getCurrentUser(){
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    return user;
}


}