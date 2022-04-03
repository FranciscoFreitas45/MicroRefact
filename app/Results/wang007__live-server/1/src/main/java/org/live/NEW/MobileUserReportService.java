package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.repository.MobileUserRepository;
import org.live.live.entity.MobileUser;
@Service
public class MobileUserReportService {

@Autowired
 private MobileUserRepository mobileuserrepository;


public MobileUser getUser(String idYUDA){
return mobileuserrepository.getUser(idYUDA);
}


public void setUser(String idYUDA,MobileUser user){
mobileuserrepository.setUser(idYUDA,user);
}


}