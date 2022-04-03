package com.sprint2.service;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.model.Admin;
import com.sprint2.repository.AdminRepository;
import com.sprint2.utility.ValidateAdmin;
import com.sprint2.Interface.AdminRepository;
// @Service annotation is used to mark the class as a service provider
@Service
public class AdminService implements // AdminService should implement all the methods present in IAdminService interface
IAdminService{

// To establish a relationship with Admin repository
@Autowired
 private  AdminRepository adminRepository;


public Admin updateAdmin(Admin admin){
    if (// validates the password according to the pattern given in validation class
    admin.getAdminPassword().matches(ValidateAdmin.passwordregex)) {
        return adminRepository.save(admin);
    } else {
        throw new InvalidOperation("Admin not updated");
    }
}


public boolean deleteAdminbyId(Integer id){
    try {
        // deleteById() is a predefined method present in JpaRepository
        adminRepository.deleteById(id);
        return true;
    } catch (InvalidOperation ie) {
        throw new InvalidOperation("Admin not deleted");
    }
}


public List<Admin> getAllAdmins(){
    List<Admin> adminlist = new ArrayList<>();
    // findall method is used to get all the records present in the table.
    adminRepository.findAll().forEach(admin -> adminlist.add(admin));
    return adminlist;
}


public Admin getAdminById(Integer id){
    try {
        // findById is a method present in JpaRepositiry
        return adminRepository.findById(id).get();
    } catch (InvalidOperation ie) {
        // prints the default message stating the exception occured
        ie.printStackTrace();
        return null;
    }
}


public Admin insertAdmin(Admin admin){
    if (admin.getAdminName().matches(ValidateAdmin.nameregex) && admin.getAdminPassword().matches(ValidateAdmin.passwordregex)) {
        // save() is used to insert records in the table
        return adminRepository.save(admin);
    } else {
        // throws User defined exception if user enters a invalid name or password
        throw new InvalidOperation("Admin not inserted");
    }
}


}