package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserMailnumberService {

@Autowired
 private UserDao userdao;


public User getMailUserId(Long userId){
return userdao.getMailUserId(userId);
}


public void setMailUserId(Long userId,User mailUserId){
userdao.setMailUserId(userId,mailUserId);
}


}