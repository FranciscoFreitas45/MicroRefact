package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserUserLogService {

@Autowired
 private UserDao userdao;


public User getUser(Long userId){
return userdao.getUser(userId);
}


public void setUser(Long userId,User user){
userdao.setUser(userId,user);
}


}