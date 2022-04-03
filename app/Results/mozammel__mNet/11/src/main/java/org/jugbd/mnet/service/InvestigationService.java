package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.Investigation;
import org.springframework.stereotype.Component;
@Component
public interface InvestigationService {


public Investigation save(Investigation investigation)
;

public Investigation findOne(Long id)
;

}