package com.cg.oms.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cg.oms.model.User;
import com.cg.oms.vo.UserVo;

/**
 * This class is used to convert the user object to the userVo Object and
 * vise-versa this is used for getting and returning the object for security
 * purpose.
 * 
 * @author Arivazhagan
 *
 */
@Component
public class UserConverter
{

	/**
	 * This method converts model object to value object
	 * 
	 * @param user
	 * @return userVo
	 */
	public UserVo modelToVo(User user)
	{
		UserVo userVo = new UserVo();
		userVo.setUserId(user.getUserId());
		userVo.setEmailId(user.getEmailId());
		userVo.setUserPhoneNumber(user.getUserPhoneNumber());
		userVo.setFirstName(user.getFirstName());
		userVo.setLastName(user.getLastName());
		userVo.setUserAge(user.getUserAge());
		userVo.setUserGender(user.getUserGender());
		userVo.setUserPassword(user.getUserPassword());
//		userVo.setConfirmPassword(user.getConfirmPassword());
		userVo.setPreviousPassword1(user.getPreviousPassword1());
		userVo.setPreviousPassword2(user.getPreviousPassword2());
		userVo.setRole(user.getRole());
		userVo.setCreatedDate(user.getCreatedDate());
		userVo.setUserAddress(user.getUserAddress());
		return userVo;
	}

	/**
	 * This method converts model object to value object
	 * 
	 * @param user
	 * @return user
	 */
	public List<UserVo> modelToVo(List<User> user)
	{
		return user.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
	}

	/**
	 * This method converts value object to model object
	 * 
	 * @param user
	 * @return userVo
	 */
	public User voToModel(UserVo userVo)
	{
		User user = new User();
		user.setUserId(userVo.getUserId());
		user.setEmailId(userVo.getEmailId());
		user.setUserPhoneNumber(userVo.getUserPhoneNumber());
		user.setFirstName(userVo.getFirstName());
		user.setLastName(userVo.getLastName());
		user.setUserAge(userVo.getUserAge());
		user.setUserGender(userVo.getUserGender());
		user.setUserPassword(userVo.getUserPassword());
//		user.setConfirmPassword(userVo.getConfirmPassword());
		user.setPreviousPassword1(userVo.getPreviousPassword1());
		user.setPreviousPassword2(userVo.getPreviousPassword2());
		user.setRole(userVo.getRole());
		user.setCreatedDate(userVo.getCreatedDate());
		user.setUserAddress(userVo.getUserAddress());
		return user;
	}

	/**
	 * This method converts value object to model object
	 * 
	 * @param user
	 * @return user
	 */
	public List<User> voToModel(List<UserVo> userVo)
	{
		return userVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
	}
}
