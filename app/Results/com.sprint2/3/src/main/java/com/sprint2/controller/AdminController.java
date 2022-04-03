package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Admin;
import com.sprint2.service.AdminService;
@Controller
// Maps a specific request path or pattern onto a controller
@RequestMapping("/Admin")
public class AdminController implements IAdminController{

 private Logger logger= LoggerFactory.getLogger(AdminController.class);

// To establish relationship with admin service
@Autowired
 private  AdminService adminService;


// @DeleteMapping is used to delete admin records
@DeleteMapping("/{id}")
@ResponseBody
public boolean deleteAdmin(Integer id){
    return adminService.deleteAdminbyId(id);
}


// @PutMapping is used to update admin records
@PutMapping("/")
@ResponseBody
public Admin updateAdmin(Admin admin){
    return adminService.updateAdmin(admin);
}


// To get the admin record based on admin id
@GetMapping("/{id}")
@ResponseBody
public Admin getAdminById(Integer id){
    logger.info("User is trying to get records based on id. ");
    return adminService.getAdminById(id);
}


// @PostMapping is used to insert admin records
@PostMapping("/")
@ResponseBody
public Admin insertAdmin(Admin admin){
    return adminService.insertAdmin(admin);
}


// To get all the records in the table
@GetMapping("/")
@ResponseBody
public List<Admin> getAllAdmin(){
    List<Admin> adminlist = adminService.getAllAdmins();
    return adminlist;
}


}