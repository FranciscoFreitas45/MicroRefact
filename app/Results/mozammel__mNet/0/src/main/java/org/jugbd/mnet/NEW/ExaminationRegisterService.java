package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.ExaminationDao;
import org.jugbd.mnet.domain.Examination;
@Service
public class ExaminationRegisterService {

@Autowired
 private ExaminationDao examinationdao;


public Examination getExamination(Long id){
return examinationdao.getExamination(id);
}


public void setExamination(Long id,Examination examination){
examinationdao.setExamination(id,examination);
}


}