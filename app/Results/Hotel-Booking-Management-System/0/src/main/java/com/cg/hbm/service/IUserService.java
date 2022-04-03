package com.cg.hbm.service;

import java.util.List;

import com.cg.hbm.entites.User;
import com.cg.hbm.exceptions.UserNotFoundException;
/***************************************************************************************************************
 *@author          	Navin Kumar Sharma
 *Description      	It is a IUserService Interface and provides methods for the Implementation class.  
 *Version          	1.0
 *Created Date    	31-MAR-2021
 **************************************************************************************************************/

public interface IUserService {

	User addUser(User user) throws UserNotFoundException;

	List<User> showAllUsers() throws UserNotFoundException;

	User showUser(int user_id) throws UserNotFoundException;

	User removeUser(int user_id) throws UserNotFoundException;

	User updateUser(int userId, User user) throws UserNotFoundException;
	
}
