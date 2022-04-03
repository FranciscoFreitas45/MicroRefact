package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.user.User;
@Service
public class UserNotepaperService {

@Autowired
 private UserDao userdao;


public User getUserId(Long userIdv2){
return userdao.getUserId(userIdv2);
}


public void setUserId(Long userIdv2,User userId){
userdao.setUserId(userIdv2,userId);
}


}