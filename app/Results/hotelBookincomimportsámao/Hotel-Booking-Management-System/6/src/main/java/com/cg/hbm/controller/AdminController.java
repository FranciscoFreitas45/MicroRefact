package com.cg.hbm.controller;
 import com.cg.hbm.entites.Admin;
import com.cg.hbm.util.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/admin")
public class AdminController {

@Autowired
 private  LoginService ls;


@GetMapping("/admin/{admin_id}/{password}")
public String validateAdmin(int admin_id,String password){
    Admin admin = new Admin();
    admin.setAdmin_id(admin_id);
    admin.setPassword(password);
    return ls.validateCredintals(admin);
}


}