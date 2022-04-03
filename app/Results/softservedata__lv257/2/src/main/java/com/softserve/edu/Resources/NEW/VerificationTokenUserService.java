package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.VerificationTokenDAOImpl;
import com.softserve.edu.Resources.entity.VerificationToken;
@Service
public class VerificationTokenUserService {

@Autowired
 private VerificationTokenDAOImpl verificationtokendaoimpl;


public VerificationToken getVerificationToken(Long id69IL){
return verificationtokendaoimpl.getVerificationToken(id69IL);
}


public User setVerificationToken(Long id69IL,VerificationToken verificationToken){
return verificationtokendaoimpl.setVerificationToken(id69IL,verificationToken);
}


}