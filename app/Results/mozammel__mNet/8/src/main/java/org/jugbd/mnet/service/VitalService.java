package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.Vital;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public interface VitalService {


public List<Vital> findByRegisterId(Long registerId,RegistrationType registrationType)
;

public Vital findOne(Long id)
;

public Vital saveByRegisterId(Vital vital,Long registerId,RegistrationType registrationType)
;

public Long delete(Long id,RegistrationType registrationType)
;

}