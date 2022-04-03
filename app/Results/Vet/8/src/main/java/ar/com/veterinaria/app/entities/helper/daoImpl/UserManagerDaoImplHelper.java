package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.exception.duplicate.UserDuplicatedException;
import ar.com.veterinaria.app.entities.exception.notFound.UserNotFoundException;
import ar.com.veterinaria.app.entities.repository.UserRepository;
import ar.com.veterinaria.app.entities.user.User;
@Service
@Transactional
public class UserManagerDaoImplHelper {

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  UserDaoImplHelper userDaoImplHelper;

@SuppressWarnings("static-access")
@Autowired
public UserManagerDaoImplHelper(UserDaoImplHelper userDaoImplHelper, UserRepository userRepository) {
    this.userDaoImplHelper = userDaoImplHelper;
    this.userRepository = userRepository;
}
public boolean isDeleted(Integer id){
    if (userDaoImplHelper.deleted(userRepository, id)) {
        throw new UserNotFoundException(id);
    }
    return false;
}


public User findUserByName(String user){
    User aUser = userDaoImplHelper.findByName(userRepository, user);
    if (aUser != null) {
        return aUser;
    }
    throw new UserNotFoundException(user);
}


public User updateUser(Integer id,User user){
    if (!userDaoImplHelper.deleted(userRepository, id)) {
        User aUser = userDaoImplHelper.update(userRepository, id, user);
        aUser.setId(id);
        aUser.setEmail(user.getEmail());
        aUser.setPassword(user.getPassword());
        aUser.setPasswordConfirmation(user.getPasswordConfirmation());
        return aUser;
    }
    throw new UserNotFoundException(id);
}


public boolean existId(Integer id){
    if (userDaoImplHelper.existId(userRepository, id)) {
        return true;
    }
    throw new UserNotFoundException(id);
}


public List<Map<String,Object>> findAll(){
    return userDaoImplHelper.findAll(userRepository);
}


public boolean validate(User user){
    if (userDaoImplHelper.isDuplicated(userRepository, user)) {
        throw new UserDuplicatedException(user);
    }
    return false;
}


}