package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserDirectorService {

@Autowired
 private UserDao userdao;


public void setMyuser(Long userId,User myuser){
userdao.setMyuser(userId,myuser);
}


public User getMyuser(Long userId){
return userdao.getMyuser(userId);
}


}