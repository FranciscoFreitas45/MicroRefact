package com.gs.common.util;
 import javax.servlet.http.HttpSession;
public class SessionUtil {


public boolean isLogin(HttpSession session){
    if (session.getAttribute("user") != null) {
        return true;
    }
    return false;
}


public boolean isOwnerLogin(HttpSession session){
    if (session.getAttribute("frontUser") != null) {
        return true;
    }
    return false;
}


}