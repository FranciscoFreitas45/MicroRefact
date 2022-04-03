package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.MobileUserRepository;
import org.live.live.entity.MobileUser;
@Service
public class MobileUserAttentionService {

@Autowired
 private MobileUserRepository mobileuserrepository;


public MobileUser getUser(String id1YA2){
return mobileuserrepository.getUser(id1YA2);
}


public void setUser(String id1YA2,MobileUser user){
mobileuserrepository.setUser(id1YA2,user);
}


}