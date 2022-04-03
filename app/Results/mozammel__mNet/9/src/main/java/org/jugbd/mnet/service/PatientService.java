package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.Patient;
import org.jugbd.mnet.web.controller.PatientSearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PatientService {


public Patient findOne(Long id)
;

public long count()
;

public Patient create(Patient patient)
;

public void update(Patient patient)
;

public Page<Patient> findAll(Pageable pageable)
;

public Page findPatientBySearchCmd(PatientSearchCmd searchCmd,Pageable pageable)
;

}