package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserTasklistService {

@Autowired
 private UserDao userdao;


public User getUsersId(Long userId){
return userdao.getUsersId(userId);
}


public void setUsersId(Long userId,User usersId){
userdao.setUsersId(userId,usersId);
}


}