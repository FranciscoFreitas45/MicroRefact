package com.example.demo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Users;
@RestController
@CrossOrigin
public class UsersOrderController {

@Autowired
 private UsersOrderService usersorderservice;


}