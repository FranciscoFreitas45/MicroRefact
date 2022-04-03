package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.ExaminationDao;
import org.jugbd.mnet.domain.Examination;
@Service
public class ExaminationOutdoorRegisterService {

@Autowired
 private ExaminationDao examinationdao;


public Examination getExamination(Long id){
return examinationdao.getExamination(id);
}


public OutdoorRegister setExamination(Long id,Examination examination){
return examinationdao.setExamination(id,examination);
}


}