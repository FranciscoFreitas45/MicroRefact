package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.OperationalDetail;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Component
public interface OperationalDetailService {


public OperationalDetail save(OperationalDetail operationalDetail)
;

public OperationalDetail findOne(Long id)
;

}