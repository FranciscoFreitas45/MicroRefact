package com.example.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Dao.UserDAO;
import com.example.demo.entity.Users;
@Service
public class UsersOrderService {

@Autowired
 private UserDAO userdao;


}