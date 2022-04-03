package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.domain.Register;
@Service
public class RegisterComplicationManagementService {

@Autowired
 private RegisterDao registerdao;


public void setRegister(Long id,Register register){
registerdao.setRegister(id,register);
}


public Register getRegister(Long id){
return registerdao.getRegister(id);
}


}