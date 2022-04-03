package com.puffride.demo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.puffride.demo.entity.User;
@RestController
@CrossOrigin
public class UserDriverController {

@Autowired
 private UserDriverService userdriverservice;


}