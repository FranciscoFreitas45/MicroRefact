package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.domain.Register;
@Service
public class RegisterPatientService {

@Autowired
 private RegisterDao registerdao;


public void setRegisters(Long id,Set<Register> registers){
registerdao.setRegisters(id,registers);
}


public Set<Register> getRegisters(Long id){
return registerdao.getRegisters(id);
}


}