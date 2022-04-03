package com.cg.hbm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.User;
import com.cg.hbm.exceptions.UserNotFoundException;
import com.cg.hbm.service.IUserService;
/****************************
 * @author Navin Kumar Sharma
 * Description : It is a Controller class of User Module. 
 * Version 1.0
 * Created Date 24-March-2021
 ****************************/

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService uService;
	
	/****************************
	 * Method                           : addUser
	 * Description                      : To add the user to the database
	 * @param user                      - user to be added to the database
	 * @param RequestBody               - It maps the HttpRequest body to a transfer or domain object,
                                          enabling automatic deserialization of the inbound HttpRequest 
                                          body onto a Java object.
	 * @returns User                    - returns user after adding the user to database 
	 * @throws UserNotFoundException    - It is raised when user already exists
	 ****************************/
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserNotFoundException  {
		User resultUser=uService.addUser(user);
		return new ResponseEntity<User>(resultUser,HttpStatus.OK) ;
	}
	
	
	/****************************
	 * Method                           : removeUser 
	 * Description                      : To delete the user from the database
	 * @param user_id                   - user_id is deleting from the database
	 * @param PathVariable              - It maps the HttpRequest body to a transfer or domain object,
                                          enabling automatic deserialization of the inbound HttpRequest 
                                          body onto a Java object.
	 * @returns void                    - returns void 
	 * @throws UserNotFoundException    - It is raised when user don't exists.
	 ****************************/
	@DeleteMapping("/{user_id}")
	public User removeUser(@PathVariable int user_id) throws UserNotFoundException {
		return uService.removeUser(user_id);
	}
	
	/****************************
	 * Method                           : updateUser 
	 * Description                      : To update the user in the database
	 * @param user                      - user to be updated
	 * @returns User                    - returns user after updating the user details to database 
	 * @throws UserNotFoundException    - It is raised when user not found
	 ****************************/
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
		User resultUser = uService.updateUser(user.getUser_id(),user);
		return new ResponseEntity<User>(resultUser, HttpStatus.OK);
	}
	
	
	/****************************
	 * Method                           : showAllUsers
	 * Description                      : To get all the users from the database
	 * @returns List<User>              - returns users after fetching the database 
	 * @throws UserNotFoundException    - It is raised when User is not found
	 ****************************/
	@GetMapping("/all")
	public ResponseEntity<List<User>> showAllUsers() throws UserNotFoundException{
		List<User> resultUser=uService.showAllUsers();
		return new ResponseEntity<List<User>>(resultUser, HttpStatus.OK);
	}
	
	
	/****************************
	 * Method                           : showUser 
	 * Description                      : To find the user from the database using id
	 * @param user_id                   - To fetch the user from the database
	 * @returns User                    - returns user after fetching the database
	 * @throws UserNotFoundException    - It is raised when user does not exists
	 ****************************/
	@GetMapping("/{user_id}")
	public ResponseEntity<User> showUser(@PathVariable int user_id) throws UserNotFoundException {
		User h=uService.showUser(user_id);
		if(h!=null) {
			return new ResponseEntity<User>(h,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	}
}