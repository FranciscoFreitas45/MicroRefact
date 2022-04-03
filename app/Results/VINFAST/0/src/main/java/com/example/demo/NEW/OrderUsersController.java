package com.example.demo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Order;
@RestController
@CrossOrigin
public class OrderUsersController {

@Autowired
 private OrderUsersService orderusersservice;


}