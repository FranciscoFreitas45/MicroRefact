package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.MobileUserRepository;
import org.live.live.entity.MobileUser;
@Service
public class MobileUserAnchorService {

@Autowired
 private MobileUserRepository mobileuserrepository;


public MobileUser getUser(String idQ2RW){
return mobileuserrepository.getUser(idQ2RW);
}


public void setUser(String idQ2RW,MobileUser user){
mobileuserrepository.setUser(idQ2RW,user);
}


}