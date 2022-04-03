package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.ChiefComplaint;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.springframework.stereotype.Service;
@Service
public interface ChiefComplaintService {


public ChiefComplaint save(ChiefComplaint chiefComplaint,RegistrationType registrationType)
;

public ChiefComplaint findOne(Long id)
;

}