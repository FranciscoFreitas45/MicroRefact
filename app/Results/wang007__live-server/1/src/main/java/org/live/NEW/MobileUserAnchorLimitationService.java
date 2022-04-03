package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.MobileUserRepository;
import org.live.live.entity.MobileUser;
@Service
public class MobileUserAnchorLimitationService {

@Autowired
 private MobileUserRepository mobileuserrepository;


public MobileUser getUser(String idL5CE){
return mobileuserrepository.getUser(idL5CE);
}


public void setUser(String idL5CE,MobileUser user){
mobileuserrepository.setUser(idL5CE,user);
}


}