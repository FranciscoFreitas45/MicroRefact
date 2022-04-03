package com.cg.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oms.exception.InvalidInputException;
import com.cg.oms.exception.UserNotFoundException;
import com.cg.oms.service.UserServiceImpl;
import com.cg.oms.validator.UserValidator;
import com.cg.oms.vo.UserVo;

/**
 * 
 * @author Arivazhagan
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController
{
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserValidator userValidator;

	/**
	 * Displays list of users.
	 * 
	 * @return all the users from the database.
	 */
	@GetMapping("/user/all")
	public ResponseEntity<List<UserVo>> getAllUser()
	{
		return ResponseEntity.ok(userServiceImpl.getAllUser());
	}

	/**
	 * Creates new user
	 * 
	 * @param userVo object of UserVo class
	 * @return created user, if passes the validation and if not then
	 * @throws InvalidInputException
	 */
	@PostMapping("/user/add")
	public ResponseEntity<UserVo> createUser(@RequestBody UserVo userVo) throws InvalidInputException
	{
		if (userValidator.validateUser(userVo))
		{
			userServiceImpl.saveUser(userVo);
		}
		return ResponseEntity.ok(userVo);
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @return user with the given id of if none found.
	 * @throws UserNotFoundException
	 */
	@GetMapping("/user/get/{userId}")
	public ResponseEntity<UserVo> getUserById(@PathVariable(value = "userId") Long userId) throws UserNotFoundException
	{
		UserVo userVo = userServiceImpl.getUserById(userId);
		return ResponseEntity.ok().body(userVo);
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @return user with the given id of if none found.
	 * @throws UserNotFoundException
	 */
	@GetMapping("/user/getemail/{emailId}")
	public ResponseEntity<UserVo> getUserByEmailId(@PathVariable(value = "emailId") String emailId)
			throws UserNotFoundException
	{
		UserVo userVo = userServiceImpl.getUserByEmailId(emailId);
		return ResponseEntity.ok().body(userVo);
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @param userVo
	 * @return updated user with the given id of if none found.
	 * @throws UserNotFoundException
	 */
	@PutMapping("/user/update/{userId}")
	public String updateUser(@PathVariable(value = "userId") Long userId, @RequestBody UserVo userVo)
			throws UserNotFoundException
	{
		return userServiceImpl.updateUser(userId, userVo);
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @return deleted user with the given id of if none found then.
	 * @throws UserNotFoundException.
	 * 
	 */
	@DeleteMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable(value = "userId") Long userId) throws UserNotFoundException
	{
		return userServiceImpl.deleteUser(userId);
	}
}