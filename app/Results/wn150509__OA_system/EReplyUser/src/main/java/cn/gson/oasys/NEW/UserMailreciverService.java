package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserMailreciverService {

@Autowired
 private UserDao userdao;


public User getReciverId(Long userId){
return userdao.getReciverId(userId);
}


public void setReciverId(Long userId,User reciverId){
userdao.setReciverId(userId,reciverId);
}


}