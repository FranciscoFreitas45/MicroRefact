package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.domain.Register;
@Service
public class RegisterInvestigationService {

@Autowired
 private RegisterDao registerdao;


public Register getRegister(Long id){
return registerdao.getRegister(id);
}


public void setRegister(Long id,Register register){
registerdao.setRegister(id,register);
}


}