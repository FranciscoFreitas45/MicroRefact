package com.cg.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oms.converter.UserConverter;
import com.cg.oms.exception.UserNotFoundException;
import com.cg.oms.model.User;
import com.cg.oms.repository.UserRepository;
import com.cg.oms.vo.UserVo;

/**
 * 
 * @author Arivazhagan
 *
 */
@Service
public class UserServiceImpl implements UserService
{

	public static final String EXCEPTION_MESSAGE = "No user found with this id ";

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;

	/**
	 * Displays List of users.
	 * 
	 * @return all users.
	 */
	@Override
	public List<UserVo> getAllUser()
	{
		List<User> user = userRepository.findAll();
		return userConverter.modelToVo(user);
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @return the user with the given id or if none found.
	 * @throws UserNotFoundException.
	 * 
	 */
	@Override
	public UserVo getUserById(Long userId) throws UserNotFoundException
	{
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(EXCEPTION_MESSAGE + userId));
		return userConverter.modelToVo(user);
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @return the user with the given id or if none found.
	 * @throws UserNotFoundException.
	 * 
	 */
	@Override
	public UserVo getUserByEmailId(String emailId)
	{
		User user = userRepository.findUserByEmailId(emailId);

		return userConverter.modelToVo(user);
	}

	/**
	 * 
	 * @param userVo object from UserVo model.
	 * @return saved user.
	 */
	@Override
	public String saveUser(UserVo userVo)
	{
		User user = userConverter.voToModel(userVo);
		user = userRepository.save(user);
		UserVo savedVo = userConverter.modelToVo(user);
		return "Registered SuccessFully!!! " + savedVo.toString();
	}

	/**
	 * 
	 * @param id       must not be null.
	 * @param newRole.
	 * @return updated user with given id or if none found.
	 * @throws UserNotFoundException.
	 */
	@Override
	public String updateUser(Long id, UserVo newUser) throws UserNotFoundException
	{
		User oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(EXCEPTION_MESSAGE + id));
		oldUser.setEmailId(newUser.getEmailId());
		oldUser.setFirstName(newUser.getFirstName());
		oldUser.setLastName(newUser.getLastName());
		oldUser.setUserGender(newUser.getUserGender());
		oldUser.setUserPhoneNumber(newUser.getUserPhoneNumber());
		oldUser.setUserAge(newUser.getUserAge());
		oldUser.setUserPassword(newUser.getUserPassword());
//		oldUser.setConfirmPassword(newUser.getConfirmPassword());
		oldUser.setPreviousPassword1(newUser.getPreviousPassword1());
		oldUser.setPreviousPassword2(newUser.getPreviousPassword2());
		oldUser.setRole(newUser.getRole());
		oldUser.setCreatedDate(newUser.getCreatedDate());
		oldUser.setUserAddress(newUser.getUserAddress());
		User updatedUser = userRepository.save(oldUser);
		UserVo savedVo = userConverter.modelToVo(updatedUser);
		return "User updated successfully" + savedVo.toString();
	}

	/**
	 * 
	 * @param userId must not be null.
	 * @return deleted user or if none found.
	 * @throws UserNotFoundException.
	 */
	@Override
	public String deleteUser(Long userId) throws UserNotFoundException
	{
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(EXCEPTION_MESSAGE + userId));
		userRepository.delete(user);
		return "User Deleted Successfully!!";
	}
}
