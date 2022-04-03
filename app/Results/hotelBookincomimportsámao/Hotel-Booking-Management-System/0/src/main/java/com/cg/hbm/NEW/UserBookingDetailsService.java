package com.cg.hbm.NEW;
import org.springframework.stereotype.Service;
import com.cg.hbm.entites.User;
import com.cg.hbm.dao.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserBookingDetailsService {
@Autowired
 private IUserRepository iuserrepository;


public User getUser(int user_id){
return iuserrepository.getUser(user_id);
}


public void setUser(int user_id,User user){
iuserrepository.setUser(user_id,user);
}


}