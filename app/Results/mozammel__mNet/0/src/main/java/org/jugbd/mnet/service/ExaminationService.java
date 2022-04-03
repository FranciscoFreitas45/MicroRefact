package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.Diagnosis;
import org.jugbd.mnet.domain.Examination;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.springframework.stereotype.Component;
@Component
public interface ExaminationService {


public Examination save(Examination examination,RegistrationType registrationType)
;

public Examination findOne(Long id)
;

}