package com.example.demo.service;
 import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SessionService {

@Autowired
 private HttpSession session;


public void set(String name,Object value){
    session.setAttribute(name, value);
}


public T get(String name,T defaultValue){
    T value = get(name);
    return value != null ? value : defaultValue;
}


public void remove(String name){
    session.removeAttribute(name);
}


}