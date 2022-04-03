package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.MedicalHistory;
import org.springframework.stereotype.Component;
@Component
public interface MedicalHistoryService {


public MedicalHistory save(MedicalHistory medicalHistory)
;

public MedicalHistory findOne(Long id)
;

}