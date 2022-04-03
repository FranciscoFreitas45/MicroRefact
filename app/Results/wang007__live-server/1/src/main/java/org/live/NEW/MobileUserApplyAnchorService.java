package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.MobileUserRepository;
import org.live.live.entity.MobileUser;
@Service
public class MobileUserApplyAnchorService {

@Autowired
 private MobileUserRepository mobileuserrepository;


public MobileUser getUser(String id58IK){
return mobileuserrepository.getUser(id58IK);
}


public void setUser(String id58IK,MobileUser user){
mobileuserrepository.setUser(id58IK,user);
}


}