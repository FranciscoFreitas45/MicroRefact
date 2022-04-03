package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserInmaillistService {

@Autowired
 private UserDao userdao;


public void setMailUserid(Long userId,User mailUserid){
userdao.setMailUserid(userId,mailUserid);
}


public User getMailUserid(Long userId){
return userdao.getMailUserid(userId);
}


}