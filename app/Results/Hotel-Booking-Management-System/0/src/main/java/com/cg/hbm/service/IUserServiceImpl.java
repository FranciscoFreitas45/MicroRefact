package com.cg.hbm.service;
 import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.cg.hbm.entites.User;
import com.cg.hbm.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.hbm.dao.IUserRepository;

@Service
@Transactional
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	IUserRepository uDao;
	
	
	
	/****************************
	 * Method                           -addUser
	 * Description                      -To add the User to the database
	 * @param user                      - user to be added to the database
	 * @returns User                    - returns user after adding the user to database 
	 * @throws UserNotFoundException    - It is raised when user already exists
	 ****************************/
	@Override
	public User addUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> u = uDao.findById(user.getUser_id());
		if (u.isEmpty()) {
			return uDao.saveAndFlush(user);
		} else {
			throw new UserNotFoundException("User already exist");
		}
	}

	
	/****************************
	 * Method                           :showAllUsers
	 * Description                      :To get all the users from the database
	 * @returns List of User            - returns users after fetching the database 
	 * @throws UserNotFoundException    - It is raised when user does not found
	 ****************************/
	@Override
	public List<User> showAllUsers() throws UserNotFoundException {
		// TODO Auto-generated method stub
		List<User> h = uDao.findAll();
		if (h.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		return h;
	}
		

	/****************************
	 * Method                           :showUser
	 * Description                      :To get the user from the database
	 * @param user_id                   -To fetch the user from the database
	 *@returns User                     - returns user after fetching the database
	 * @throws UserNotFoundException    - It is raised when user does not exists
	 ****************************/
	@Override
	public User showUser(int user_id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> h=uDao.findById(user_id);
		if(h.isEmpty()) {
			throw new UserNotFoundException("Given UserId does not exist");
		}
		return h.get();
	}

	/****************************
	 * Method                           :removeUser
	 * Description                      :To remove the user to the database
	 * @param user_id                   - user to be removed from the database
	 * @returns void                    - returns void
	 * @throws UserNotFoundException    - It is raised when user does not exists
	 ****************************/
	@Override
	public User removeUser(int user_id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> op=uDao.findById(user_id);
		if(op.isPresent()) {
			uDao.deleteById(user_id);
			return op.get();
		}
		else throw new UserNotFoundException("User with given Id doesn't exist.");
	}

	/****************************
	 * Method                           :updateUser 
	 * Description                      :To update the user in the database
	 * @param user,userId               - user to be updated
	 * @returns User                    - returns user after adding the user to database 
	 * @throws UserNotFoundException    - It is raised when user not found
	 ****************************/
	@Override
	public User updateUser(int userId, User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> h = uDao.findById(userId);
		if (h.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		else
		uDao.save(user);
		return user;
	}

}