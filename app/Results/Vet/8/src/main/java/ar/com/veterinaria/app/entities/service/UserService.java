package ar.com.veterinaria.app.entities.service;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.dao.UserDao;
import ar.com.veterinaria.app.entities.helper.service.UserManagerServiceHelper;
import ar.com.veterinaria.app.entities.service.contract.UserContractService;
import ar.com.veterinaria.app.entities.user.User;
@Service
@Transactional
public class UserService implements UserContractService{

@Autowired
 private  UserDao userDAO;

public UserService() {
}
@Override
public User add(User t){
    if (t == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return userDAO.add(t);
}


@Override
public boolean exist(User user){
    if (user == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return userDAO.exist(user);
}


@Override
public boolean isValidInputData(User user){
    if (UserManagerServiceHelper.validate(user)) {
        return true;
    }
    return false;
}


@Override
public User findById(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0");
    }
    return userDAO.findById(id);
}


@Override
public User update(int id,User user){
    if (user == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    return userDAO.update(id, user);
}


@Override
public List<Map<String,Object>> findAll(){
    List<Map<String, Object>> result = userDAO.findAll();
    if (result.size() > 0) {
        return result;
    }
    return null;
}


@Override
public void remove(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    userDAO.remove(id);
}


}