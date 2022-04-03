package com.cg.oms.service;

import java.util.List;

import com.cg.oms.exception.UserNotFoundException;
import com.cg.oms.vo.UserVo;

/**
 * Interface to be implemented by the userServiceImpl class
 * 
 * @author Arivazhagan
 *
 */
public interface UserService
{
	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public List<UserVo> getAllUser();

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public UserVo getUserById(Long userId) throws UserNotFoundException;

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public UserVo getUserByEmailId(String emailId) throws UserNotFoundException;

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String saveUser(UserVo userVo);

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String updateUser(Long id, UserVo newRole) throws UserNotFoundException;

	/**
	 * Method to be override by the implementing class
	 * 
	 */
	public String deleteUser(Long userId) throws UserNotFoundException;

}
