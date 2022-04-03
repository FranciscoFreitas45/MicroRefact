package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.Diagnosis;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.springframework.stereotype.Component;
@Component
public interface DiagnosisService {


public Diagnosis save(Diagnosis diagnosis,RegistrationType registrationType)
;

public Diagnosis findOne(Long id)
;

}