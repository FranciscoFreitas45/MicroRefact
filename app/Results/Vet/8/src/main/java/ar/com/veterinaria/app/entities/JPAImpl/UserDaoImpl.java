package ar.com.veterinaria.app.entities.JPAImpl;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.com.veterinaria.app.entities.dao.UserDao;
import ar.com.veterinaria.app.entities.exception.notFound.UserNotFoundException;
import ar.com.veterinaria.app.entities.helper.daoImpl.UserManagerDaoImplHelper;
import ar.com.veterinaria.app.entities.repository.UserRepository;
import ar.com.veterinaria.app.entities.user.User;
@Repository
public class UserDaoImpl implements UserDao{

 private  Logger logger;

@Autowired
 private  UserRepository userRepository;

public UserDaoImpl() {
    super();
}
@Override
public User add(User user){
    try {
        userRepository.save(user);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return user;
}


@Override
public boolean exist(User user){
    /*
		 * if (!ClinicalHistoryManagerDaoImplHelper.validate(user)) { return false; }
		 */
    return true;
}


@Override
public User findById(int id){
    try {
        if (!UserManagerDaoImplHelper.isDeleted(id)) {
            return userRepository.findUserById(id);
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    throw new UserNotFoundException(id);
}


@Override
public User update(int id,User user){
    /*
		 * if (ClinicalHistoryManagerDaoImplHelper.existId(id)) { if
		 * (!ClinicalHistoryManagerDaoImplHelper.isDeleted(id)) { userRepository
		 * .save(ClinicalHistoryManagerDaoImplHelper.updateClinicalHistory(id, user));
		 */
    return user;
/*
		 * }
		 * 
		 * } return null;
		 */
}


@Override
public List<Map<String,Object>> findAll(){
    // userRepository.findAll();
    return null;
}


@Override
public void remove(int id){
    // if (!ClinicalHistoryManagerDaoImplHelper.isDeleted(id)) {
    User user = userRepository.findUserById(id);
    user.setId(id);
    user.setDeleted(true);
    userRepository.save(user);
// } // falta validar id
}


}