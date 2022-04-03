package com.sprint2.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.model.User;
import com.sprint2.repository.UserRepository;
// @Service annotation is used to mark the class as a service provider
@Service
public class UserService implements IUserService{

// To establish relationship between UserRepository and UserService
@Autowired
 private  UserRepository userrepository;


public boolean deleteAdminbyName(String userName){
    if (userName != null) {
        // DeleteById method is presesnt in JpaRepository used to delete a record based on Id
        userrepository.deleteById(userName);
        return true;
    } else {
        throw new InvalidOperation("User deleted");
    }
}


public String logout(User user){
    return user.getUserName() + " has logged out successfully";
}


public User insertUser(User user){
    if (user != null) {
        // Save() method is used to insert record into the table.
        return userrepository.save(user);
    } else {
        throw new InvalidOperation("user not added");
    }
}


public String login(User user){
    String userName = user.getUserName();
    // findById is used to find any particular record using id
    User loginUser = userrepository.findById(userName).get();
    if (loginUser == null) {
        try {
            throw new InvalidOperation("Please enter correct details");
        } catch (// throws the user defined exceptionwhen details entered by the user is does not match the record in database
        InvalidOperation ie) {
            System.out.println(ie);
        }
    } else {
        String pwd = loginUser.getUserPassword();
        String uname = loginUser.getUserName();
        if (pwd.equals(user.getUserPassword()) && uname.equalsIgnoreCase(user.getUserName())) {
            return loginUser.getUserName() + " has successfully logged in";
        }
    }
    return null;
}


}